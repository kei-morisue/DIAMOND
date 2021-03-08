/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

/**
 * @author Kei Morisue
 *
 */
public abstract class Vertex<T> implements D0<T> {
    private T x;
    private T y;

    public Vertex(T x, T y) {
        this.x = x;
        this.y = y;
    }

}
