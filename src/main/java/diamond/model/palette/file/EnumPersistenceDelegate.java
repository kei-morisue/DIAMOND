/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.file;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;

/**
 * @author long_
 *
 */
class EnumPersistenceDelegate extends PersistenceDelegate {
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        return oldInstance == newInstance;
    }

    protected Expression instantiate(Object oldInstance, Encoder out) {
        Enum e = (Enum) oldInstance;
        return new Expression(e, e.getClass(), "valueOf",
                new Object[] { e.name() });
    }
}