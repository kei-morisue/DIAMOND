/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;

/**
 * @author Kei Morisue
 *
 */
class EnumPersistenceDelegate extends PersistenceDelegate {
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        return oldInstance == newInstance;
    }

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        @SuppressWarnings("rawtypes")
        Enum e = (Enum) oldInstance;
        return new Expression(e, e.getClass(), "valueOf",
                new Object[] { e.name() });
    }
}