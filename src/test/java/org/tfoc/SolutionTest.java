package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SolutionTest {


    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Test
    void testSolution() {

        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        int result = solution.rotting(grid1);

        assertEquals(4, result);

        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };

        result = solution.rotting(grid2);

        assertEquals(-1, result);

        int[][] grid3 = {{0, 2}};

        result = solution.rotting(grid3);

        assertEquals(0, result);

    }

}