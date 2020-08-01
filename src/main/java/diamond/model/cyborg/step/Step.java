/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.step;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import diamond.model.cyborg.AbstractOperation;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.symbol.AbstractSymbol;

/**
 * @author Kei Morisue
 *
 */
public class Step {
    private ArrayList<Face> faces = new ArrayList<Face>();
    private ArrayList<AbstractSymbol> symbols = new ArrayList<AbstractSymbol>();
    private AbstractOperation operation;
    private AffineTransform transform = new AffineTransform();

    public Step() {
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    @Deprecated

    public void setFaces(ArrayList<Face> faces) {
        this.faces = faces;
    }

    public ArrayList<AbstractSymbol> getSymbols() {
        return symbols;
    }

    @Deprecated

    public void setSymbols(ArrayList<AbstractSymbol> symbols) {
        this.symbols = symbols;
    }

    public AbstractOperation getOperation() {
        return operation;
    }

    @Deprecated

    public void setOperation(AbstractOperation operation) {
        this.operation = operation;
    }

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }
}
