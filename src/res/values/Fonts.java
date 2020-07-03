package res.values;

import java.awt.Font;

public enum Fonts {
    MALGUN(new Font("맑은 고딕", Font.PLAIN, 16));

    public final Font font;

    Fonts(Font font) {
        this.font = font;
    }
}
