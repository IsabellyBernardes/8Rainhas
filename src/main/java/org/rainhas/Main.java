package org.rainhas;

import static org.rainhas.EightQueens.solve;

public class Main {
    public static void main(String[] args) {
        // -1 significa coluna vazia

        // Caso 1: Tabuleiro vazio
        int[] board1 = {-1, -1, -1, -1, -1, -1, -1, -1};
        solve("Tabuleiro Vazio", board1);

        // Caso 2: 1 rainha - coluna 'a', linha 1 (queens[0] = 0  => linha 1)
        int[] board2 = {0, -1, -1, -1, -1, -1, -1, -1};
        solve("1 Rainha: a1", board2);

        // Caso 3: 2 rainhas - a1 e c5 (não se atacam)
        // queens[0]=0 (linha1), queens[2]=4 (linha5) — diferença de linha=4, diff coluna=2 => não é diagonal
        int[] board3 = {0, -1, 4, -1, -1, -1, -1, -1};
        solve("2 Rainhas: a1, c5", board3);

        // Caso 4: 3 rainhas - a1, c5, e2
        // queens[0]=0, queens[2]=4, queens[4]=1
        // a1->c5: Δrow=4, Δcol=2 ✓  a1->e2: Δrow=1, Δcol=4 ✓  c5->e2: Δrow=3, Δcol=2 ✓
        int[] board4 = {0, -1, 4, -1, 1, -1, -1, -1};
        solve("3 Rainhas: a1, c5, e2", board4);
    }
}