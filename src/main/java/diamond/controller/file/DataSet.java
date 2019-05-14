/** DIAMOND - Origami Diagram Editor*/
package diamond.controller.file;

import java.util.LinkedList;

import diamond.model.geom.element.diagram.Diagram;

public class DataSet {
    private LinkedList<Diagram> diagrams;
    private StyleSet styleSet;

    public DataSet() {

    }

    public DataSet(LinkedList<Diagram> diagrams) {
        this.diagrams = diagrams;
        StyleSet style = new StyleSet();
        style.write();
        this.styleSet = style;
    }

    public LinkedList<Diagram> getDiagrams() {
        return this.diagrams;
    }

    public void setDiagrams(LinkedList<Diagram> diagrams) {
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
