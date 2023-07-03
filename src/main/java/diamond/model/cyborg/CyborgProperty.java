/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.io.Serializable;

import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class CyborgProperty implements Serializable{
    public boolean isPointed = false;
    public boolean isPicked = false;

    private Symbol sign = null;

    public Symbol getSign() {
        return this.sign;
    }

    public void setSign(Symbol sign) {
        this.sign = sign;
    }

    public boolean isColored() {
        return isPointed || isPicked;
    }

}
