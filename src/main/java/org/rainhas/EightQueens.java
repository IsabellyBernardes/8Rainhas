package org.rainhas;
import java.util.ArrayDeque;
import java.util.Deque;

public class EightQueens {

    static int nodeCount = 0;

    /**
     * Verifica se a posição (row, col) é segura dado o estado atual.
     * queens[col] representa a linha ocupada naquela coluna (-1 = vazia).
     */
    static boolean isSafe(int[] queens, int row, int col) {
        for (int c = 0; c < col; c++) {
            if (queens[c] == -1) continue;
            int r = queens[c];
            if (r == row || Math.abs(r - row) == Math.abs(c - col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Busca em Profundidade (DFS) iterativa para resolver o problema das 8 rainhas.
     * O estado é um array int[8] indicando a posição da rainha em cada coluna.
     */
    static int[] dfs(int[] initialQueens) {
        nodeCount = 0;

        // Determina a primeira coluna livre
        int startCol = 0;
        for (int c = 0; c < 8; c++) {
            if (initialQueens[c] != -1) startCol = c + 1;
            else break;
        }

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(initialQueens.clone());
        nodeCount++;

        while (!stack.isEmpty()) {
            int[] state = stack.pop();

            // Encontra a próxima coluna vazia
            int col = -1;
            for (int c = 0; c < 8; c++) {
                if (state[c] == -1) { col = c; break; }
            }

            if (col == -1) return state;

            for (int row = 0; row < 8; row++) {
                if (isSafe(state, row, col)) {
                    int[] newState = state.clone();
                    newState[col] = row;
                    stack.push(newState);
                    nodeCount++;
                }
            }
        }
        return null;
    }

    static void printBoard(int[] queens) {
        System.out.println("  ┌─────────────────┐");
        for (int row = 0; row < 8; row++) {
            System.out.print((row + 1) + " │");
            for (int col = 0; col < 8; col++) {
                System.out.print(queens[col] == row ? " Q" : " .");
            }
            System.out.println(" │");
        }
        System.out.println("  └─────────────────┘");
        System.out.println("    a b c d e f g h");
    }

    static void solve(String label, int[] initialQueens) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CASO: " + label);
        System.out.println("=".repeat(50));
        System.out.println("Tabuleiro inicial:");
        printBoard(initialQueens);

        long start = System.nanoTime();
        int[] solution = dfs(initialQueens);
        long elapsed = System.nanoTime() - start;

        System.out.println("\nNós criados: " + nodeCount);
        System.out.printf("Tempo: %.3f ms%n", elapsed / 1_000_000.0);

        if (solution != null) {
            System.out.println("\nSolução encontrada:");
            printBoard(solution);
            System.out.print("Posições (coluna -> linha): ");
            for (int c = 0; c < 8; c++) {
                System.out.print((char)('a' + c) + "" + (solution[c] + 1) + " ");
            }
            System.out.println();
        } else {
            System.out.println("Nenhuma solução encontrada.");
        }
    }
}