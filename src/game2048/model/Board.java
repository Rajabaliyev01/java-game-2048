package game2048.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 4x4 o'yin doskasi (Board).
 * Kataklar holatini saqlash, siljitish va birlashtirish logikasi.
 */
public class Board {
    public static final int SIZE = 4;
    private final Tile[][] grid = new Tile[SIZE][SIZE];
    private final Random random = new Random();
    private int score = 0;

    public Board() {
        reset();
    }

    public void reset() {
        score = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = new Tile(0);
            }
        }
        addRandomTile();
        addRandomTile();
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }

    public int getScore() {
        return score;
    }

    // Bo'sh joylarga tasodifan 2 (90% ehtimol) yoki 4 (10%) chiqarish
    public boolean addRandomTile() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c].isEmpty()) {
                    emptyCells.add(new int[]{r, c});
                }
            }
        }
        if (emptyCells.isEmpty()) return false;

        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        int val = (random.nextDouble() < 0.9) ? 2 : 4;
        grid[cell[0]][cell[1]] = new Tile(val);
        return true;
    }

    /**
     * Berilgan yo'nalish bo'yicha harakatlantirish.
     * @return agar doskada biror o'zgarish ro'y bersa true qaytaradi.
     */
    public boolean move(Direction direction) {
        boolean changed = false;
        switch (direction) {
            case LEFT -> {
                for (int r = 0; r < SIZE; r++) {
                    int[] row = getRowValues(r);
                    int[] merged = slideAndMerge(row);
                    if (applyRowValues(r, merged)) changed = true;
                }
            }
            case RIGHT -> {
                for (int r = 0; r < SIZE; r++) {
                    int[] row = reverse(getRowValues(r));
                    int[] merged = slideAndMerge(row);
                    if (applyRowValues(r, reverse(merged))) changed = true;
                }
            }
            case UP -> {
                for (int c = 0; c < SIZE; c++) {
                    int[] col = getColValues(c);
                    int[] merged = slideAndMerge(col);
                    if (applyColValues(c, merged)) changed = true;
                }
            }
            case DOWN -> {
                for (int c = 0; c < SIZE; c++) {
                    int[] col = reverse(getColValues(c));
                    int[] merged = slideAndMerge(col);
                    if (applyColValues(c, reverse(merged))) changed = true;
                }
            }
        }

        if (changed) {
            addRandomTile();
        }
        return changed;
    }

    // 1D massivni siljitish va bir xil qo'shnilarni qo'shish (2048 ning markaziy algoritmi)
    private int[] slideAndMerge(int[] line) {
        List<Integer> nonZero = new ArrayList<>();
        for (int val : line) {
            if (val != 0) nonZero.add(val);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nonZero.size(); i++) {
            if (i < nonZero.size() - 1 && nonZero.get(i).equals(nonZero.get(i + 1))) {
                int combined = nonZero.get(i) * 2;
                result.add(combined);
                score += combined;
                i++; // Birlashtirilgan ikkinchi elementni o'tkazib yuborish
            } else {
                result.add(nonZero.get(i));
            }
        }

        int[] finalLine = new int[SIZE];
        for (int i = 0; i < result.size(); i++) {
            finalLine[i] = result.get(i);
        }
        return finalLine;
    }

    private int[] getRowValues(int r) {
        int[] arr = new int[SIZE];
        for (int c = 0; c < SIZE; c++) arr[c] = grid[r][c].getValue();
        return arr;
    }

    private boolean applyRowValues(int r, int[] newVals) {
        boolean changed = false;
        for (int c = 0; c < SIZE; c++) {
            if (grid[r][c].getValue() != newVals[c]) {
                changed = true;
                grid[r][c] = new Tile(newVals[c]);
            }
        }
        return changed;
    }

    private int[] getColValues(int c) {
        int[] arr = new int[SIZE];
        for (int r = 0; r < SIZE; r++) arr[r] = grid[r][c].getValue();
        return arr;
    }

    private boolean applyColValues(int c, int[] newVals) {
        boolean changed = false;
        for (int r = 0; r < SIZE; r++) {
            if (grid[r][c].getValue() != newVals[r]) {
                changed = true;
                grid[r][c] = new Tile(newVals[r]);
            }
        }
        return changed;
    }

    private int[] reverse(int[] arr) {
        int[] rev = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rev[i] = arr[arr.length - 1 - i];
        }
        return rev;
    }

    // 2048 ga erishilganmi?
    public boolean hasWon() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c].getValue() >= 2048) return true;
            }
        }
        return false;
    }

    // Harakat qilish uchun imkoniyat bormi?
    public boolean isGameOver() {
        // Bo'sh joy bo'lsa o'yin tugamagan
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c].isEmpty()) return false;
            }
        }
        // Yonma-yon bir xil kataklar bo'lsa hali birlashtirish mumkin
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int val = grid[r][c].getValue();
                if (c < SIZE - 1 && val == grid[r][c + 1].getValue()) return false;
                if (r < SIZE - 1 && val == grid[r + 1][c].getValue()) return false;
            }
        }
        return true;
    }
}
