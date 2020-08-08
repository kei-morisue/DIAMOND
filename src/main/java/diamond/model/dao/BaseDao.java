/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Kei Morisue
 *
 */
public abstract class BaseDao<T> {

    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        Class<?> clazz = this.getClass();
        Type type = clazz.getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) type;
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        return (Class<T>) actualTypeArguments[0].getClass();
    }
}
