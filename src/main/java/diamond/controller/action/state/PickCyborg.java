/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import diamond.controller.Context;
import diamond.controller.mouse.PickerCyborg;
import diamond.controller.mouse.PointerCyborg;
import diamond.model.cyborg.geom.Cyborg;

/**
 * @author Kei Morisue
 *
 */
public class PickCyborg<C extends Cyborg> extends AbstractPaintState {
    private Context context;
    private Class<C> type;

    public PickCyborg(Context context, Class<C> type) {
        this.context = context;
        this.type = type;
    }

    @Override
    protected void undo() {
        getPicker().pop();
        getPointer().initialize();
    }

    @Override
    protected void executeAction() {
        getPicker().add(getPointer().get());
    }

    @Override
    protected boolean tryAction() {
        return getPointer().add(context);
    }

    protected PickerCyborg<C> getPicker() {
        return context.getPicker(type);
    }

    protected PointerCyborg<C> getPointer() {
        return context.getPointer(type);
    }

    @Override
    void initialize() {
    }

    @Override
    public AbstractState onMove() {
        getPointer().add(context);
        return this;
    }

    @Override
    public AbstractState onRelease() {
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + "<"
                + type.getSimpleName()
                + ">";
    }
}
