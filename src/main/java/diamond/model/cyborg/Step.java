/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.ArrayList;

import diamond.model.cyborg.geom.Face;
import diamond.model.cyborg.symbol.AbstractSymbol;

/**
 * @author Kei Morisue
 *
 */
public class Step {
    private ArrayList<Face> faces;
    private ArrayList<AbstractSymbol> symbols;
    private AbstractOperation operation;

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
}
