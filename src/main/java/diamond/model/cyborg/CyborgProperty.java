/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class CyborgProperty {
    public boolean isPointed = false;
    public boolean isPicked = false;

    private Symbol sign = null;

    public Symbol getSign() {
        return this.sign;
    }

    public void setSign(Symbol sign) {
        this.sign = sign;
    }

    public boolean isPointed() {
        return isPointed || isPicked;
    }

}
