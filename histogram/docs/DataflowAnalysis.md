# Dataflow Analysis for dijkstra Method

## Target Function
**Method:** `dijkstra(String src, String des, boolean nan)`  
**Location:** `Graph_M.java`, lines 200-265

## Variable List

| Variable | Type | Scope | Description |
|----------|------|-------|-------------|
| val | int | Method | Return value storing shortest distance/time |
| ans | ArrayList<String> | Method | List of visited vertices (not used in return) |
| map | HashMap<String, DijkstraPair> | Method | Maps vertex names to DijkstraPair objects |
| heap | Heap<DijkstraPair> | Method | Priority queue for Dijkstra algorithm |
| key | String | For loop (208-223) | Current vertex key in initialization loop |
| np | DijkstraPair | For loop (208-223) | New pair created for each vertex |
| rp | DijkstraPair | While loop (226-263) | Removed pair from heap |
| v | Vertex | While loop (226-263) | Current vertex being processed |
| nbr | String | For loop (241-262) | Neighbor vertex name |
| oc | int | For loop (241-262) | Old cost of neighbor |
| k | Vertex | For loop (241-262) | Vertex for edge weight lookup |
| nc | int | For loop (241-262) | New calculated cost |
| gp | DijkstraPair | For loop (241-262) | Pair retrieved from map for update |
| src | String | Parameter | Source vertex name |
| des | String | Parameter | Destination vertex name |
| nan | boolean | Parameter | Flag: true for time calculation, false for distance |

## Definition Points (D)

| Variable | Line(s) | Definition Type | Context |
|----------|---------|-----------------|---------|
| val | 202 | Initialization | int val = 0; |
| ans | 203 | Initialization | ArrayList<String> ans = new ArrayList<>(); |
| map | 204 | Initialization | HashMap<String, DijkstraPair> map = new HashMap<>(); |
| heap | 206 | Initialization | Heap<DijkstraPair> heap = new Heap<>(); |
| key | 208 | Loop variable | for (String key : vtces.keySet()) |
| np | 210 | Object creation | DijkstraPair np = new DijkstraPair(); |
| np.vname | 211 | Assignment | np.vname = key; |
| np.cost | 213 | Assignment | np.cost = Integer.MAX_VALUE; |
| np.cost | 217 | Re-definition | np.cost = 0; (if key.equals(src)) |
| np.psf | 218 | Assignment | np.psf = key; (if key.equals(src)) |
| rp | 228 | Assignment | DijkstraPair rp = heap.remove(); |
| val | 232 | Re-definition | val = rp.cost; (if rp.vname.equals(des)) |
| v | 240 | Assignment | Vertex v = vtces.get(rp.vname); |
| nbr | 241 | Loop variable | for (String nbr : v.nbrs.keySet()) |
| oc | 245 | Assignment | int oc = map.get(nbr).cost; |
| k | 246 | Assignment | Vertex k = vtces.get(rp.vname); |
| nc | 247 | Declaration | int nc; |
| nc | 249 | Assignment | nc = rp.cost + 120 + 40*k.nbrs.get(nbr); (if nan) |
| nc | 251 | Assignment | nc = rp.cost + k.nbrs.get(nbr); (if !nan) |
| gp | 255 | Assignment | DijkstraPair gp = map.get(nbr); |
| gp.psf | 256 | Assignment | gp.psf = rp.psf + nbr; |
| gp.cost | 257 | Assignment | gp.cost = nc; |

## Use Points (U)

### Computation Uses (c-use)

| Variable | Line(s) | Use Type | Context |
|----------|---------|----------|---------|
| vtces | 208 | c-use | vtces.keySet() - iteration |
| key | 211 | c-use | np.vname = key; |
| src | 215 | p-use | key.equals(src) - predicate |
| key | 215 | p-use | key.equals(src) - predicate |
| key | 222 | c-use | map.put(key, np); |
| heap | 221 | c-use | heap.add(np); |
| heap | 226 | p-use | !heap.isEmpty() - predicate |
| heap | 228 | c-use | heap.remove() |
| rp | 230 | p-use | rp.vname.equals(des) - predicate |
| rp.vname | 230 | c-use | rp.vname.equals(des) |
| des | 230 | p-use | rp.vname.equals(des) - predicate |
| rp | 232 | c-use | val = rp.cost; |
| rp.cost | 232 | c-use | val = rp.cost; |
| rp.vname | 236 | c-use | map.remove(rp.vname); |
| rp.vname | 238 | c-use | ans.add(rp.vname); |
| rp.vname | 240 | c-use | vtces.get(rp.vname) |
| v | 241 | c-use | v.nbrs.keySet() |
| nbr | 243 | p-use | map.containsKey(nbr) - predicate |
| map | 243 | c-use | map.containsKey(nbr) |
| nbr | 245 | c-use | map.get(nbr).cost |
| map | 245 | c-use | map.get(nbr).cost |
| rp.vname | 246 | c-use | vtces.get(rp.vname) |
| rp.cost | 249 | c-use | nc = rp.cost + 120 + 40*k.nbrs.get(nbr) |
| k | 249 | c-use | k.nbrs.get(nbr) |
| nbr | 249 | c-use | k.nbrs.get(nbr) |
| rp.cost | 251 | c-use | nc = rp.cost + k.nbrs.get(nbr) |
| k | 251 | c-use | k.nbrs.get(nbr) |
| nbr | 251 | c-use | k.nbrs.get(nbr) |
| nc | 253 | p-use | nc < oc - predicate |
| oc | 253 | p-use | nc < oc - predicate |
| nbr | 255 | c-use | map.get(nbr) |
| map | 255 | c-use | map.get(nbr) |
| rp.psf | 256 | c-use | gp.psf = rp.psf + nbr; |
| nbr | 256 | c-use | gp.psf = rp.psf + nbr; |
| nc | 257 | c-use | gp.cost = nc; |
| gp | 259 | c-use | heap.updatePriority(gp); |
| heap | 259 | c-use | heap.updatePriority(gp); |
| val | 264 | c-use | return val; |

### Predicate Uses (p-use)

| Variable | Line(s) | Branch | Context |
|----------|---------|--------|---------|
| src | 215 | True/False | if (key.equals(src)) |
| key | 215 | True/False | if (key.equals(src)) |
| heap | 226 | True/False | while (!heap.isEmpty()) |
| rp.vname | 230 | True/False | if(rp.vname.equals(des)) |
| des | 230 | True/False | if(rp.vname.equals(des)) |
| map | 243 | True/False | if (map.containsKey(nbr)) |
| nbr | 243 | True/False | if (map.containsKey(nbr)) |
| nan | 248 | True/False | if(nan) |
| nc | 253 | True/False | if (nc < oc) |
| oc | 253 | True/False | if (nc < oc) |

## Def-Clear Paths

A def-clear path is a path from a definition to a use where the variable is not redefined along the path.

### Key Def-Clear Paths:

1. **val (D:202 → U:232)**: Path N1→N2→N3→...→N9→N10→N11→N12
   - Def-clear: No redefinition until line 232

2. **val (D:202 → U:264)**: Path N1→N2→N3→...→N9→N28→N29
   - Def-clear if destination not found

3. **np.cost (D:213 → U:221)**: Path N4→N5→N6→N7
   - Def-clear if key != src

4. **np.cost (D:217 → U:221)**: Path N6→N7
   - Def-clear if key == src

5. **rp (D:228 → U:230)**: Path N10→N11
   - Def-clear: Direct path

6. **nc (D:249 → U:253)**: Path N22→N24
   - Def-clear: Direct path

7. **nc (D:251 → U:253)**: Path N23→N24
   - Def-clear: Direct path

8. **nc (D:249 → U:257)**: Path N22→N24→N25→N26
   - Def-clear: No redefinition

9. **nc (D:251 → U:257)**: Path N23→N24→N25→N26
   - Def-clear: No redefinition

## DU Chains (Definition-Use Chains)

| # | Variable | Def Line | Use Line | Use Type | Def-Clear Path | Description |
|---|----------|----------|----------|----------|----------------|-------------|
| 1 | val | 202 | 232 | c-use | N1→N2→...→N10→N11→N12 | Initialize to 0, set when destination found |
| 2 | val | 202 | 264 | c-use | N1→N2→...→N28→N29 | Initialize to 0, return if destination not found |
| 3 | val | 232 | 264 | c-use | N12→N29 | Set when found, return immediately |
| 4 | ans | 203 | 238 | c-use | N1→N2→...→N13→N14 | Initialize, add visited vertex |
| 5 | map | 204 | 222 | c-use | N1→N2→...→N7 | Initialize, put vertex pair |
| 6 | map | 204 | 236 | c-use | N1→N2→...→N13 | Initialize, remove processed vertex |
| 7 | map | 204 | 243 | p-use | N1→N2→...→N17 | Initialize, check if neighbor in map |
| 8 | map | 204 | 245 | c-use | N1→N2→...→N18 | Initialize, get neighbor cost |
| 9 | map | 204 | 255 | c-use | N1→N2→...→N25 | Initialize, get neighbor pair |
| 10 | heap | 206 | 221 | c-use | N2→N3→...→N7 | Initialize, add pair |
| 11 | heap | 206 | 226 | p-use | N2→N3→...→N9 | Initialize, check if empty |
| 12 | heap | 206 | 228 | c-use | N2→N3→...→N10 | Initialize, remove pair |
| 13 | heap | 206 | 259 | c-use | N2→N3→...→N26 | Initialize, update priority |
| 14 | key | 208 | 211 | c-use | N3→N4 | Loop variable, assign to np.vname |
| 15 | key | 208 | 215 | p-use | N3→N4→N5 | Loop variable, compare with src |
| 16 | key | 208 | 222 | c-use | N3→N4→...→N7 | Loop variable, put in map |
| 17 | np | 210 | 211 | c-use | N4→N4 | Create, set vname |
| 18 | np | 210 | 213 | c-use | N4→N4 | Create, set cost |
| 19 | np | 210 | 217 | c-use | N4→N5→N6 | Create, re-set cost if src |
| 20 | np | 210 | 218 | c-use | N4→N5→N6 | Create, set psf if src |
| 21 | np | 210 | 221 | c-use | N4→N5→...→N7 | Create, add to heap |
| 22 | np | 210 | 222 | c-use | N4→N5→...→N7 | Create, put in map |
| 23 | np.cost | 213 | 221 | c-use | N4→N5→N7 | Set to MAX_VALUE, add to heap |
| 24 | np.cost | 217 | 221 | c-use | N6→N7 | Set to 0, add to heap |
| 25 | np.psf | 218 | 221 | c-use | N6→N7 | Set to key, add to heap |
| 26 | rp | 228 | 230 | p-use | N10→N11 | Remove from heap, check destination |
| 27 | rp | 228 | 232 | c-use | N10→N11→N12 | Remove from heap, get cost |
| 28 | rp | 228 | 236 | c-use | N10→N11→N13 | Remove from heap, remove from map |
| 29 | rp | 228 | 238 | c-use | N10→N11→N13→N14 | Remove from heap, add to ans |
| 30 | rp | 228 | 240 | c-use | N10→N11→N13→N14→N15 | Remove from heap, get vertex |
| 31 | rp.vname | 211→228 | 230 | p-use | N4→...→N10→N11 | Set via np, check destination |
| 32 | rp.cost | 213→228 | 232 | c-use | N4→...→N10→N11→N12 | Set via np, assign to val |
| 33 | rp.cost | 213→228 | 249 | c-use | N4→...→N10→N22 | Set via np, calculate new cost (time) |
| 34 | rp.cost | 213→228 | 251 | c-use | N4→...→N10→N23 | Set via np, calculate new cost (distance) |
| 35 | v | 240 | 241 | c-use | N15→N16 | Get vertex, iterate neighbors |
| 36 | nbr | 241 | 243 | p-use | N16→N17 | Loop variable, check in map |
| 37 | nbr | 241 | 245 | c-use | N16→N17→N18 | Loop variable, get cost |
| 38 | nbr | 241 | 249 | c-use | N16→N17→...→N22 | Loop variable, calculate cost (time) |
| 39 | nbr | 241 | 251 | c-use | N16→N17→...→N23 | Loop variable, calculate cost (distance) |
| 40 | nbr | 241 | 255 | c-use | N16→N17→...→N24→N25 | Loop variable, get pair |
| 41 | nbr | 241 | 256 | c-use | N16→N17→...→N25 | Loop variable, update psf |
| 42 | oc | 245 | 253 | p-use | N18→N19→...→N24 | Get old cost, compare with new |
| 43 | k | 246 | 249 | c-use | N19→N20→...→N22 | Get vertex, calculate cost (time) |
| 44 | k | 246 | 251 | c-use | N19→N20→...→N23 | Get vertex, calculate cost (distance) |
| 45 | nc | 247 | 253 | p-use | N20→N21→...→N24 | Declare, compare with oc |
| 46 | nc | 249 | 253 | p-use | N22→N24 | Calculate (time), compare |
| 47 | nc | 251 | 253 | p-use | N23→N24 | Calculate (distance), compare |
| 48 | nc | 249 | 257 | c-use | N22→N24→N25→N26 | Calculate (time), update cost |
| 49 | nc | 251 | 257 | c-use | N23→N24→N25→N26 | Calculate (distance), update cost |
| 50 | gp | 255 | 256 | c-use | N25→N25 | Get pair, update psf |
| 51 | gp | 255 | 257 | c-use | N25→N25 | Get pair, update cost |
| 52 | gp | 255 | 259 | c-use | N25→N26 | Get pair, update priority |
| 53 | gp.psf | 218→256 | 256 | c-use | N6→...→N25 | Set via np/rp, concatenate with nbr |
| 54 | gp.cost | 213→257 | 257 | c-use | N4→...→N25→N26 | Set via np, update with nc |
| 55 | nan | 200 | 248 | p-use | N1→...→N21 | Parameter, choose calculation method |
| 56 | src | 200 | 215 | p-use | N1→...→N5 | Parameter, check if current key is source |
| 57 | des | 200 | 230 | p-use | N1→...→N11 | Parameter, check if reached destination |

## DU Table

| # | Variable | Def Line | Use Line | Use Type | Def-Clear Path | Test ID | Coverage |
|---|----------|----------|----------|----------|----------------|---------|----------|
| 1 | val | 202 | 232 | c-use | N1→N2→...→N10→N11→N12 | T1 | ✓ |
| 2 | val | 202 | 264 | c-use | N1→N2→...→N28→N29 | T2 | ✓ |
| 3 | val | 232 | 264 | c-use | N12→N29 | T1 | ✓ |
| 4 | ans | 203 | 238 | c-use | N1→N2→...→N13→N14 | T3 | ✓ |
| 5 | map | 204 | 222 | c-use | N1→N2→...→N7 | T4 | ✓ |
| 6 | map | 204 | 236 | c-use | N1→N2→...→N13 | T3 | ✓ |
| 7 | map | 204 | 243 | p-use | N1→N2→...→N17 | T5, T6 | ✓ |
| 8 | map | 204 | 245 | c-use | N1→N2→...→N18 | T5 | ✓ |
| 9 | map | 204 | 255 | c-use | N1→N2→...→N25 | T5 | ✓ |
| 10 | heap | 206 | 221 | c-use | N2→N3→...→N7 | T4 | ✓ |
| 11 | heap | 206 | 226 | p-use | N2→N3→...→N9 | T1, T2, T3 | ✓ |
| 12 | heap | 206 | 228 | c-use | N2→N3→...→N10 | T1, T2, T3 | ✓ |
| 13 | heap | 206 | 259 | c-use | N2→N3→...→N26 | T5 | ✓ |
| 14 | key | 208 | 211 | c-use | N3→N4 | T4 | ✓ |
| 15 | key | 208 | 215 | p-use | N3→N4→N5 | T4 | ✓ |
| 16 | key | 208 | 222 | c-use | N3→N4→...→N7 | T4 | ✓ |
| 17 | np | 210 | 211 | c-use | N4→N4 | T4 | ✓ |
| 18 | np | 210 | 213 | c-use | N4→N4 | T4 | ✓ |
| 19 | np | 210 | 217 | c-use | N4→N5→N6 | T4 | ✓ |
| 20 | np | 210 | 218 | c-use | N4→N5→N6 | T4 | ✓ |
| 21 | np | 210 | 221 | c-use | N4→N5→...→N7 | T4 | ✓ |
| 22 | np | 210 | 222 | c-use | N4→N5→...→N7 | T4 | ✓ |
| 23 | np.cost | 213 | 221 | c-use | N4→N5→N7 | T4 | ✓ |
| 24 | np.cost | 217 | 221 | c-use | N6→N7 | T4 | ✓ |
| 25 | np.psf | 218 | 221 | c-use | N6→N7 | T4 | ✓ |
| 26 | rp | 228 | 230 | p-use | N10→N11 | T1, T2, T3 | ✓ |
| 27 | rp | 228 | 232 | c-use | N10→N11→N12 | T1 | ✓ |
| 28 | rp | 228 | 236 | c-use | N10→N11→N13 | T3 | ✓ |
| 29 | rp | 228 | 238 | c-use | N10→N11→N13→N14 | T3 | ✓ |
| 30 | rp | 228 | 240 | c-use | N10→N11→N13→N14→N15 | T3, T5 | ✓ |
| 31 | rp.vname | 211→228 | 230 | p-use | N4→...→N10→N11 | T1, T2, T3 | ✓ |
| 32 | rp.cost | 213→228 | 232 | c-use | N4→...→N10→N11→N12 | T1 | ✓ |
| 33 | rp.cost | 213→228 | 249 | c-use | N4→...→N10→N22 | T6 | ✓ |
| 34 | rp.cost | 213→228 | 251 | c-use | N4→...→N10→N23 | T5 | ✓ |
| 35 | v | 240 | 241 | c-use | N15→N16 | T3, T5, T6 | ✓ |
| 36 | nbr | 241 | 243 | p-use | N16→N17 | T5, T6, T7 | ✓ |
| 37 | nbr | 241 | 245 | c-use | N16→N17→N18 | T5 | ✓ |
| 38 | nbr | 241 | 249 | c-use | N16→N17→...→N22 | T6 | ✓ |
| 39 | nbr | 241 | 251 | c-use | N16→N17→...→N23 | T5 | ✓ |
| 40 | nbr | 241 | 255 | c-use | N16→N17→...→N24→N25 | T5 | ✓ |
| 41 | nbr | 241 | 256 | c-use | N16→N17→...→N25 | T5 | ✓ |
| 42 | oc | 245 | 253 | p-use | N18→N19→...→N24 | T5 | ✓ |
| 43 | k | 246 | 249 | c-use | N19→N20→...→N22 | T6 | ✓ |
| 44 | k | 246 | 251 | c-use | N19→N20→...→N23 | T5 | ✓ |
| 45 | nc | 247 | 253 | p-use | N20→N21→...→N24 | T5, T6 | ✓ |
| 46 | nc | 249 | 253 | p-use | N22→N24 | T6 | ✓ |
| 47 | nc | 251 | 253 | p-use | N23→N24 | T5 | ✓ |
| 48 | nc | 249 | 257 | c-use | N22→N24→N25→N26 | T6 | ✓ |
| 49 | nc | 251 | 257 | c-use | N23→N24→N25→N26 | T5 | ✓ |
| 50 | gp | 255 | 256 | c-use | N25→N25 | T5 | ✓ |
| 51 | gp | 255 | 257 | c-use | N25→N25 | T5 | ✓ |
| 52 | gp | 255 | 259 | c-use | N25→N26 | T5 | ✓ |
| 53 | gp.psf | 218→256 | 256 | c-use | N6→...→N25 | T5 | ✓ |
| 54 | gp.cost | 213→257 | 257 | c-use | N4→...→N25→N26 | T5 | ✓ |
| 55 | nan | 200 | 248 | p-use | N1→...→N21 | T5, T6 | ✓ |
| 56 | src | 200 | 215 | p-use | N1→...→N5 | T4 | ✓ |
| 57 | des | 200 | 230 | p-use | N1→...→N11 | T1, T2, T3 | ✓ |

**Total DU Pairs:** 57  
**Coverage Status:** All pairs covered by test suite

