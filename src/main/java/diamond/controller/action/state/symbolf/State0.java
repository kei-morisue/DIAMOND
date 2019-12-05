/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symbolf;

import diamond.controller.Context;
import diamond.controller.action.state.FacePickingState;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FacePickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = State0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State0.class;
    }

    @Override
    protected void aftermath(Context context) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
