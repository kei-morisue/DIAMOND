/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import diamond.controller.Context;
import diamond.controller.mouse.PickerCyborg;
import diamond.controller.mouse.PointerCyborg;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class PickCyborg<C extends Cyborg> extends AbstractPaintState {
    @SuppressWarnings("unchecked")
    private Class<C> type = (Class<C>) Vertex.class;//TODO
    private Context context;

    public PickCyborg(Context context) {
        this.context = context;
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

    private PickerCyborg<C> getPicker() {
        return context.getPicker().get(type);
    }

    private PointerCyborg<C> getPointer() {
        return context.getPointer().get(type);
    }

    @Override
    void initialize() {
    }

    @Override
    public void onMove() {
        getPointer().add(context);
    }

}
