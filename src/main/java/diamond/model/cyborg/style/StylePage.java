/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.style;

import java.awt.Color;

import diamond.view.ui.panel.option.Setter;

/**
 * @author Kei Morisue
 *
 */
public class StylePage {
    private byte row = 3;
    private byte col = 2;
    private Color bg = new Color(153, 204, 234);

    public StylePage() {
    }

    public void initialize() {
        row = 3;
        col = 2;
        bg = new Color(153, 204, 234);
    }

    public byte getCol() {
        return col;
    }

    @Deprecated
    public void setCol(byte col) {
        this.col = col;
    }

    public class ColSetter implements Setter<Byte> {
        @Override
        public void set(Byte b) {
            col = b;
        }
    }

    public byte getRow() {
        return row;
    }

    @Deprecated
    public void setRow(byte row) {
        this.row = row;
    }

    public class RowSetter implements Setter<Byte> {
        @Override
        public void set(Byte b) {
            row = b;
        }
    }

    public Color getBg() {
        return bg;
    }

    @Deprecated
    public void setBg(Color bg) {
        this.bg = bg;
    }

    public class BgSetter implements Setter<Color> {

        @Override
        public void set(Color color) {
            bg = color;
        }
    }
}
