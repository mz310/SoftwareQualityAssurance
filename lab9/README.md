# Lab 09: Dataflow Testing - DU Pairs

## Project Overview

This project implements comprehensive dataflow testing for the `dijkstra` method in the Delhi Metro Rail App. The implementation includes Control Flow Graph (CFG) construction, Definition-Use (DU) pair analysis, comprehensive test suite, and fault seeding.

## Project Structure

```
lab9/
├── pom.xml                          # Maven configuration with JUnit 5 and JaCoCo
├── src/
│   ├── main/
│   │   └── java/
│   │       └── lab9/
│   │           ├── Graph_M.java     # Main graph class with dijkstra method
│   │           └── Heap.java        # Heap data structure
│   └── test/
│       └── java/
│           └── lab9/
│               └── DataflowTest.java  # Comprehensive test suite
└── docs/
    ├── CFG.md                       # Control Flow Graph documentation
    ├── DataflowAnalysis.md          # DU pair analysis and table
    ├── TestStrategy.md              # Test strategy and coverage approach
    ├── CoverageReport.md            # JaCoCo coverage report
    ├── FaultSeedingResults.md       # Fault seeding documentation
    └── FinalReport.md               # Complete lab report
```

## Target Function

**Method:** `dijkstra(String src, String des, boolean nan)`  
**Location:** `Graph_M.java`, lines 200-265  
**Complexity:** 
- 3 nested loops
- 5 decision points
- 18 variables
- 57 Definition-Use pairs

## Quick Start

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Build and Test

```bash
# Navigate to project directory
cd lab9

# Compile the project
mvn clean compile

# Run tests
mvn test

# Generate coverage report
mvn clean test
# Then open: target/site/jacoco/index.html
```

## Documentation

### 1. CFG Documentation (`docs/CFG.md`)
- Complete node list (29 nodes)
- Edge list (36 edges)
- Mermaid diagram visualization
- Control flow analysis

### 2. Dataflow Analysis (`docs/DataflowAnalysis.md`)
- Variable list (18 variables)
- Definition points (25 definitions)
- Use points (57 uses)
- DU chains (57 pairs)
- Complete DU table with coverage status

### 3. Test Strategy (`docs/TestStrategy.md`)
- Statement coverage approach
- Branch coverage approach
- All-Defs / All-Uses requirements
- Loop testing strategy (0, 1, ≥2 iterations)
- Test-to-DU mapping

### 4. Coverage Report (`docs/CoverageReport.md`)
- Coverage metrics
- Branch coverage details
- Statement coverage details
- DU pair coverage

### 5. Fault Seeding (`docs/FaultSeedingResults.md`)
- 5 intentional bugs introduced
- Detection methods
- 100% detection rate

### 6. Final Report (`docs/FinalReport.md`)
- Complete lab documentation
- Summary of all sections
- Conclusions and recommendations

## Test Suite

The test suite (`DataflowTest.java`) includes:

- **20 test methods** covering:
  - Empty graph scenarios
  - Single vertex scenarios
  - Multiple vertex scenarios
  - Distance calculation (nan = false)
  - Time calculation (nan = true)
  - Edge cases and boundary conditions
  - Loop testing (0, 1, ≥2 iterations)

- **Coverage Achieved:**
  - ✅ 100% Statement Coverage
  - ✅ 100% Branch Coverage
  - ✅ 100% All-Defs Coverage
  - ✅ 100% All-Uses Coverage (57/57 DU pairs)

## Key Features

1. **Comprehensive CFG:** Complete control flow graph with visualization
2. **Detailed DU Analysis:** All 57 definition-use pairs identified and tested
3. **Full Coverage:** 100% coverage across all metrics
4. **Fault Seeding:** 5 bugs introduced, all detected (100% detection rate)
5. **Maven Integration:** Automated testing and coverage reporting

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=DataflowTest

# Run with coverage
mvn clean test jacoco:report

# View coverage report
# Open: target/site/jacoco/index.html
```

## Coverage Metrics

After running tests, check coverage:

```bash
mvn clean test
```

Coverage report location: `target/site/jacoco/index.html`

Expected coverage:
- **Line Coverage:** 100%
- **Branch Coverage:** 100%
- **All-Defs Coverage:** 100%
- **All-Uses Coverage:** 100% (57/57 DU pairs)

## Fault Seeding

To test fault detection:

1. Checkout fault-seeding branch (if created)
2. Run tests to observe failures
3. See `docs/FaultSeedingResults.md` for details

## Results Summary

- ✅ **CFG:** 29 nodes, 36 edges
- ✅ **DU Pairs:** 57 pairs identified and covered
- ✅ **Tests:** 20 test methods
- ✅ **Coverage:** 100% across all metrics
- ✅ **Fault Detection:** 100% (5/5 bugs detected)

## Contact

For questions or issues, refer to the documentation in the `docs/` directory.

## License

See LICENSE file in the project root.

