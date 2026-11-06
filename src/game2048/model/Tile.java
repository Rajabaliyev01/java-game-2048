package game2048.model;

import java.awt.Color;

/**
 * Har bir katakchani (Tile) ifodalovchi sinf.
 * OOP prinsipi: Encapsulation (Inkapsulyatsiya) - ma'lumotlar va uning stili bir joyda.
 */
public class Tile {
    private final int value;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    // Katakchaning qiymatiga qarab fon rangini tanlash
    public Color getBackgroundColor() {
        return switch (value) {
            case 2    -> new Color(0xeee4da);
            case 4    -> new Color(0xede0c8);
            case 8    -> new Color(0xf2b179);
            case 16   -> new Color(0xf59563);
            case 32   -> new Color(0xf67c5f);
            case 64   -> new Color(0xf65e3b);
            case 128  -> new Color(0xedcf72);
            case 256  -> new Color(0xedcc61);
            case 512  -> new Color(0xedc850);
            case 1024 -> new Color(0xedc53f);
            case 2048 -> new Color(0xedc22e);
            default   -> value > 2048 ? new Color(0x3c3a32) : new Color(0xcdc1b4);
        };
    }

    // Raqam rangi (2 va 4 to'q rangda, kattalari oq rangda)
    public Color getTextColor() {
        return value <= 4 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }
}
