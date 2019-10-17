/** DIAMOND - Origami Diagram Editor*/
package diamond.controller.file;

import java.util.Vector;

import diamond.model.geom.element.diagram.Diagram;

public class DataSet {
    private Vector<Diagram> diagrams;
    private StyleSet styleSet;

    public DataSet() {

    }

    public DataSet(Vector<Diagram> diagrams) {
        this.diagrams = diagrams;
        StyleSet style = new StyleSet();
        style.write();
        this.styleSet = style;
        for (Diagram diagram : diagrams) {
            diagram.getCp().saveOrder();
        }

    }

    public Vector<Diagram> getDiagrams() {
        return this.diagrams;
    }

    public void setDiagrams(Vector<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

    public StyleSet getStyleSet() {
        return styleSet;
    }

    @Deprecated

    public void setStyleSet(StyleSet styleSet) {
        this.styleSet = styleSet;
    }

}
