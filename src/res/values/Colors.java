package res.values;

import java.awt.Color;

public enum Colors {
    EXPORT(new Color(0x42a5f5)),
    IMPORT(new Color(0xef5350));

    public final Color color;

    Colors(Color color) {
        this.color = color;
    }
}
