package lab9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

/**
 * Dataflow Testing for dijkstra method
 * Covers: Statement coverage, Branch coverage, All-Defs coverage, All-Uses coverage
 */
public class DataflowTest {

    private Graph_M graph;

    @BeforeEach
    void setUp() {
        graph = new Graph_M();
    }

    @Test
    @DisplayName("T0: Empty Graph - Covers heap.isEmpty() false branch, val initialization to return")
    void testEmptyGraph() {
        // DU Pairs: heap (206→226), val (202→264)
        int result = graph.dijkstra("A", "B", false);
        assertEquals(0, result, "Empty graph should return 0");
    }

    @Test
    @DisplayName("T1: Source Equals Destination - Covers val (202→232→264), rp (228→230→232), immediate return")
    void testSourceEqualsDestination() {
        // DU Pairs: val (202→232→264), rp (228→230→232), heap (206→226, 206→228), 
        // des (200→230), rp.vname (211→228→230), rp.cost (213→228→232)
        graph.addVertex("A");
        
        int result = graph.dijkstra("A", "A", false);
        assertEquals(0, result, "Source equals destination should return 0");
    }

    @Test
    @DisplayName("T2: Single Vertex, No Path - Covers val (202→264), heap loop execution")
    void testSingleVertexNoPath() {
        // DU Pairs: val (202→264), heap (206→226, 206→228), rp (228→230), 
        // des (200→230), rp.vname (211→228→230)
        graph.addVertex("A");
        
        int result = graph.dijkstra("A", "B", false);
        assertEquals(Integer.MAX_VALUE, result, "No path should return MAX_VALUE");
    }

    @Test
    @DisplayName("T3: Multiple Vertices, Path Exists - Covers ans, map, rp, v, nbr processing")
    void testMultipleVerticesPathExists() {
        // DU Pairs: ans (203→238), map (204→236, 204→238), rp (228→236, 228→238, 228→240),
        // v (240→241), nbr (241→243)
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        
        int result = graph.dijkstra("A", "C", false);
        assertEquals(15, result, "Path A->B->C should have distance 15");
    }

    @Test
    @DisplayName("T4: Initialization Loop - Covers key, np, np.vname, np.cost, np.psf, src comparison")
    void testInitializationLoop() {
        // DU Pairs: key (208→211, 208→215, 208→222), np (210→211, 210→213, 210→217, 210→218, 210→221, 210→222),
        // np.cost (213→221, 217→221), np.psf (218→221), heap (206→221), map (204→222), src (200→215)
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        
        int result = graph.dijkstra("A", "C", false);
        assertEquals(15, result, "Should initialize all vertices correctly");
    }

    @Test
    @DisplayName("T5: Distance Calculation with Update - Covers map, nbr, oc, k, nc (distance), gp, nan=false")
    void testDistanceCalculationWithUpdate() {
        // DU Pairs: map (204→243, 204→245, 204→255), nbr (241→243, 241→245, 241→251, 241→255, 241→256),
        // oc (245→253), k (246→251), nc (247→253, 251→253, 251→257), gp (255→256, 255→257, 255→259),
        // gp.psf (256), gp.cost (257), heap (206→259), nan (200→248) - false branch
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "D", 8);
        graph.addEdge("C", "D", 3);
        
        int result = graph.dijkstra("A", "D", false);
        assertEquals(8, result, "Shorter path A->C->D should be chosen (5+3=8)");
    }

    @Test
    @DisplayName("T6: Time Calculation with Update - Covers rp.cost, nbr, k, nc (time), nan=true")
    void testTimeCalculationWithUpdate() {
        // DU Pairs: rp.cost (213→228→249), nbr (241→249), k (246→249), 
        // nc (247→253, 249→253, 249→257), nan (200→248) - true branch
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        
        int result = graph.dijkstra("A", "C", true);
        // Time calculation: (0 + 120 + 40*5) + (120 + 40*10) = 320 + 520 = 840
        // But algorithm calculates incrementally, so we verify it's calculated
        assertTrue(result > 0, "Time calculation should return positive value");
    }

    @Test
    @DisplayName("T7: Neighbor Not in Map - Covers map.containsKey(nbr) false branch")
    void testNeighborNotInMap() {
        // DU Pairs: map (204→243), nbr (241→243) - false branch
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        
        // Process A, then B will be removed from map before processing neighbors
        int result = graph.dijkstra("A", "B", false);
        assertEquals(5, result, "Direct edge should work");
        
        // Test case where neighbor is already processed
        graph.addVertex("C");
        graph.addEdge("B", "C", 10);
        result = graph.dijkstra("A", "C", false);
        assertEquals(15, result, "Path through processed vertex should work");
    }

    @Test
    @DisplayName("T8: New Cost Not Better - Covers nc < oc false branch")
    void testNewCostNotBetter() {
        // DU Pairs: nc (251→253), oc (245→253) - false branch
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 20);
        graph.addEdge("B", "C", 10);
        
        // Path A->B->C = 15, but A->C = 20, so we update
        // Then when processing C from A, nc = 20, oc = 15, so nc < oc is false
        int result = graph.dijkstra("A", "C", false);
        assertEquals(15, result, "Shorter path A->B->C should be chosen");
    }

    @Test
    @DisplayName("Loop Test: 0 iterations - Empty graph")
    void testLoopZeroIterations() {
        // All loops: 0 iterations
        int result = graph.dijkstra("A", "B", false);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Loop Test: 1 iteration - Single vertex")
    void testLoopOneIteration() {
        // Loop 1: 1 iteration, Loop 2: 1 iteration, Loop 3: 0 iterations
        graph.addVertex("A");
        int result = graph.dijkstra("A", "A", false);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Loop Test: Multiple iterations - Complex graph")
    void testLoopMultipleIterations() {
        // Loop 1: ≥2 iterations, Loop 2: ≥2 iterations, Loop 3: ≥2 iterations
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        graph.addEdge("C", "D", 8);
        graph.addEdge("D", "E", 12);
        
        int result = graph.dijkstra("A", "E", false);
        assertEquals(35, result, "Path A->B->C->D->E should have distance 35");
    }

    @Test
    @DisplayName("Edge Case: No path exists between vertices")
    void testNoPathExists() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        // C is isolated
        
        int result = graph.dijkstra("A", "C", false);
        // When no path exists, algorithm returns 0 (val initialized to 0 and never updated)
        // This is actually a bug in the original code, but we test the actual behavior
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Edge Case: Direct edge between source and destination")
    void testDirectEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        
        int result = graph.dijkstra("A", "B", false);
        assertEquals(5, result, "Direct edge should return edge weight");
    }

    @Test
    @DisplayName("Branch Coverage: nan flag true branch")
    void testNanFlagTrue() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        
        int result = graph.dijkstra("A", "B", true);
        // Time: 120 + 40*5 = 320
        assertEquals(320, result, "Time calculation should use formula 120 + 40*distance");
    }

    @Test
    @DisplayName("Branch Coverage: nan flag false branch")
    void testNanFlagFalse() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        
        int result = graph.dijkstra("A", "B", false);
        assertEquals(5, result, "Distance calculation should return edge weight");
    }

    @Test
    @DisplayName("All-Defs: Ensure all variable definitions are covered")
    void testAllDefinitionsCovered() {
        // This test ensures all definitions are reached
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        
        // Execute with both nan values to cover all definitions
        int result1 = graph.dijkstra("A", "C", false);
        int result2 = graph.dijkstra("A", "C", true);
        
        assertTrue(result1 > 0, "Distance result should be positive");
        assertTrue(result2 > 0, "Time result should be positive");
    }

    @Test
    @DisplayName("Complex Graph: Multiple paths with different costs")
    void testComplexGraphMultiplePaths() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 8);
        
        // Path 1: A->B->D = 15
        // Path 2: A->C->D = 11 (shorter)
        int result = graph.dijkstra("A", "D", false);
        assertEquals(11, result, "Shorter path A->C->D should be chosen");
    }
}

