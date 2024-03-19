package org.tfoc;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private static final int FRESH_ORANGE = 1;
    private static final int ROTTEN_ORANGE = 2;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int rotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int freshOranges = 0;

        //Saco listado de coordenadas podridas y contador de frescas
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ROTTEN_ORANGE) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == FRESH_ORANGE) {
                    freshOranges++;
                }
            }
        }

        int minutes = 0;

        //Si hay naranjas frescas
        if (freshOranges != 0) {
            //Mientras queden naranjas podridas sin revisar
            while (!queue.isEmpty()) {
                int size = queue.size();
                boolean hasRotten = false;

                for (int i = 0; i < size; i++) {
                    int[] current = queue.poll();
                    //Para cada naranja compruebo todas las direcciones posibles
                    for (int[] dir : DIRECTIONS) {
                        int x = current[0] + dir[0];
                        int y = current[1] + dir[1];

                        if (Boolean.TRUE.equals(checkDirection(x, y, rows, cols, grid))) {

                            //Pudro la naranja
                            grid[x][y] = ROTTEN_ORANGE;

                            //Añado la nueva naranja podrida a la cola
                            queue.offer(new int[]{x, y});

                            //Queda una naranja fresca menos
                            freshOranges--;

                            //Sigue habiendo naranjas podridas (continuo)
                            hasRotten = true;
                        }
                    }
                }

                //Una vez analizado las primeras naranjas podridas, si se han pudrido mas, añado un minuto y continuo.
                if (hasRotten) {
                    minutes++;
                }
            }
        }

        return freshOranges == 0 ? minutes : -1;
    }


    /**
     * Compruebo si la direccion es posible y si tiene naranja fresca
     *
     */
    private Boolean checkDirection(int x, int y, int rows, int cols, int[][] grid) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == FRESH_ORANGE;
    }
}
