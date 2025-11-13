# Test Strategy for Dataflow Testing

## Target Function
**Method:** `dijkstra(String src, String des, boolean nan)`  
**Location:** `Graph_M.java`, lines 200-265

## Coverage Goals

### 1. Statement Coverage
Every executable statement must be executed at least once.

**Approach:**
- Test with empty graph (0 vertices)
- Test with single vertex (1 vertex)
- Test with multiple vertices (≥2 vertices)
- Test with direct path (source = destination)
- Test with path requiring multiple iterations
- Test both branches of nan flag (true/false)

**Test Cases:**
- T0: Empty graph - covers initialization and early exit
- T1: Single vertex, source = destination - covers direct return
- T2: Single vertex, source ≠ destination - covers loop execution
- T3: Multiple vertices, path exists - covers full algorithm
- T4: Multiple vertices, no path exists - covers complete traversal
- T5: Distance calculation (nan = false) - covers distance branch
- T6: Time calculation (nan = true) - covers time branch

### 2. Branch Coverage
Every branch (true/false) of each decision must be taken at least once.

**Branches to Cover:**

| Line | Condition | True Branch | False Branch |
|------|-----------|-------------|--------------|
| 215 | key.equals(src) | T4 | T4 (other keys) |
| 226 | !heap.isEmpty() | T1, T2, T3, T4, T5, T6 | T0 |
| 230 | rp.vname.equals(des) | T1 | T2, T3, T4, T5, T6 |
| 243 | map.containsKey(nbr) | T5, T6 | T7 |
| 248 | nan | T6 | T5 |
| 253 | nc < oc | T5, T6 | T8 |

**Test Cases:**
- T0: Empty graph - false branch of heap.isEmpty()
- T1: Source = destination - true branch of rp.vname.equals(des)
- T2: Single vertex, no destination - false branch of rp.vname.equals(des)
- T3: Multiple vertices, path exists - covers both branches of rp.vname.equals(des)
- T4: Initialization loop - covers both branches of key.equals(src)
- T5: Distance calculation with update - covers true branch of nc < oc, false branch of nan
- T6: Time calculation with update - covers true branch of nc < oc, true branch of nan
- T7: Neighbor not in map - covers false branch of map.containsKey(nbr)
- T8: New cost not better - covers false branch of nc < oc

### 3. All-Defs Coverage
Every definition must be covered by at least one use.

**Strategy:**
- Ensure each variable definition is reached
- Ensure each definition has at least one use covered
- Test both initialization and redefinition paths

**Test Cases:**
- T4: Covers all definitions in initialization loop (np, np.vname, np.cost, np.psf)
- T1: Covers definition of val at line 232
- T3: Covers definitions in main loop (rp, v, nbr, oc, k, nc, gp)
- T5: Covers definition of nc at line 251 (distance)
- T6: Covers definition of nc at line 249 (time)

### 4. All-Uses Coverage
Every definition-use pair must be covered by at least one test.

**Strategy:**
- Cover all 57 DU pairs identified in DataflowAnalysis.md
- Ensure both c-use and p-use are covered
- Cover all paths from definitions to uses

**Test Cases Mapping:**

| Test ID | DU Pairs Covered | Description |
|---------|------------------|-------------|
| T0 | heap (226), val (264) | Empty graph, no execution |
| T1 | val (202→232→264), rp (228→230→232), heap (226, 228), des (230), rp.vname (230), rp.cost (232) | Source = destination, immediate return |
| T2 | val (202→264), heap (226, 228), rp (228→230), des (230), rp.vname (230) | Single vertex, no path |
| T3 | ans (203→238), map (204→236, 204→238), rp (228→236, 228→238, 228→240), v (240→241), nbr (241→243) | Multiple vertices, path exists, neighbor processing |
| T4 | key (208→211, 208→215, 208→222), np (210→211, 210→213, 210→217, 210→218, 210→221, 210→222), np.cost (213→221, 217→221), np.psf (218→221), heap (206→221), map (204→222), src (215) | Initialization loop, source vertex setup |
| T5 | map (204→243, 204→245, 204→255), nbr (241→243, 241→245, 241→251, 241→255, 241→256), oc (245→253), k (246→251), nc (247→253, 251→253, 251→257), gp (255→256, 255→257, 255→259), gp.psf (256), gp.cost (257), heap (206→259), nan (248) | Distance calculation, cost update |
| T6 | rp.cost (213→228→249), nbr (241→249), k (246→249), nc (247→253, 249→253, 249→257), nan (248) | Time calculation, cost update |
| T7 | map (204→243), nbr (241→243) | Neighbor not in map (false branch) |
| T8 | nc (251→253), oc (245→253) | New cost not better (false branch) |

### 5. Loop Testing

**Strategy:** Test loops with 0, 1, and ≥2 iterations

#### Loop 1: For loop (lines 208-223) - Vertex Initialization
- **0 iterations:** Empty graph (T0)
- **1 iteration:** Single vertex graph (T1, T2)
- **≥2 iterations:** Multiple vertex graph (T3, T4, T5, T6)

#### Loop 2: While loop (lines 226-263) - Main Algorithm
- **0 iterations:** Empty heap (T0)
- **1 iteration:** Source = destination (T1)
- **≥2 iterations:** Multiple iterations needed (T2, T3, T4, T5, T6)

#### Loop 3: For loop (lines 241-262) - Neighbor Processing
- **0 iterations:** Vertex with no neighbors (T2)
- **1 iteration:** Vertex with one neighbor (T3)
- **≥2 iterations:** Vertex with multiple neighbors (T5, T6)

**Test Cases:**
- T0: All loops 0 iterations
- T1: Loop 1: 1 iteration, Loop 2: 1 iteration, Loop 3: 0 iterations
- T2: Loop 1: 1 iteration, Loop 2: ≥2 iterations, Loop 3: 0 iterations
- T3: Loop 1: ≥2 iterations, Loop 2: ≥2 iterations, Loop 3: 1 iteration
- T5: Loop 1: ≥2 iterations, Loop 2: ≥2 iterations, Loop 3: ≥2 iterations

### 6. Test-to-DU Mapping

Each test case is designed to cover specific DU pairs:

**T0 - Empty Graph Test:**
- Covers: heap (206→226), val (202→264)
- Purpose: Test initialization and early exit

**T1 - Source Equals Destination:**
- Covers: val (202→232→264), rp (228→230→232), heap (206→226, 206→228), des (200→230), rp.vname (211→228→230), rp.cost (213→228→232)
- Purpose: Test immediate return when source = destination

**T2 - Single Vertex, No Path:**
- Covers: val (202→264), heap (206→226, 206→228), rp (228→230), des (200→230), rp.vname (211→228→230)
- Purpose: Test loop execution with no destination found

**T3 - Multiple Vertices, Path Exists:**
- Covers: ans (203→238), map (204→236, 204→238), rp (228→236, 228→238, 228→240), v (240→241), nbr (241→243)
- Purpose: Test full algorithm execution with path found

**T4 - Initialization Loop:**
- Covers: All initialization DU pairs (key, np, np.vname, np.cost, np.psf, heap, map, src)
- Purpose: Test vertex initialization loop with source vertex identification

**T5 - Distance Calculation with Update:**
- Covers: map (204→243, 204→245, 204→255), nbr (241→243, 241→245, 241→251, 241→255, 241→256), oc (245→253), k (246→251), nc (247→253, 251→253, 251→257), gp (255→256, 255→257, 255→259), gp.psf (256), gp.cost (257), heap (206→259), nan (200→248)
- Purpose: Test distance calculation branch and cost update

**T6 - Time Calculation with Update:**
- Covers: rp.cost (213→228→249), nbr (241→249), k (246→249), nc (247→253, 249→253, 249→257), nan (200→248)
- Purpose: Test time calculation branch and cost update

**T7 - Neighbor Not in Map:**
- Covers: map (204→243), nbr (241→243) - false branch
- Purpose: Test branch when neighbor already processed

**T8 - New Cost Not Better:**
- Covers: nc (251→253), oc (245→253) - false branch
- Purpose: Test branch when new cost is not better than old cost

## Test Data Requirements

### Graph Configurations:

1. **Empty Graph:**
   - Vertices: 0
   - Edges: 0

2. **Single Vertex Graph:**
   - Vertices: {"A"}
   - Edges: 0

3. **Two Vertex Graph:**
   - Vertices: {"A", "B"}
   - Edges: A-B (weight: 5)

4. **Multi-Vertex Graph (Path Exists):**
   - Vertices: {"A", "B", "C", "D"}
   - Edges: A-B (10), B-C (5), C-D (8)
   - Path: A → B → C → D

5. **Multi-Vertex Graph (No Path):**
   - Vertices: {"A", "B", "C", "D"}
   - Edges: A-B (10), C-D (8)
   - No path: A to D

6. **Multi-Vertex Graph (Multiple Neighbors):**
   - Vertices: {"A", "B", "C", "D"}
   - Edges: A-B (5), A-C (10), B-D (8), C-D (3)
   - Path: A → C → D (shorter)

## Expected Coverage Metrics

- **Statement Coverage:** 100%
- **Branch Coverage:** 100%
- **All-Defs Coverage:** 100%
- **All-Uses Coverage:** 100% (all 57 DU pairs)
- **Loop Coverage:** All loops tested with 0, 1, ≥2 iterations

## Test Execution Order

1. T0: Empty graph test
2. T1: Source = destination test
3. T2: Single vertex, no path test
4. T4: Initialization loop test
5. T3: Multiple vertices, path exists test
6. T5: Distance calculation test
7. T6: Time calculation test
8. T7: Neighbor not in map test
9. T8: New cost not better test

This order ensures:
- Basic functionality first
- Incremental complexity
- Edge cases covered
- All branches exercised

