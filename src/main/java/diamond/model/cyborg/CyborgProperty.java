/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import diamond.model.Sign;

/**
 * @author Kei Morisue
 *
 */
public class CyborgProperty {
    public boolean isPointed = false;
    private Sign sign = null;

    public Sign getSign() {
        return this.sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }
}
