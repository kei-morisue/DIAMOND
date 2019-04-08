/** DIAMOND - Origami Diagram Editor*/
package diamond.controller.file;

import java.util.LinkedList;

import diamond.model.geom.element.diagram.Diagram;

public class DataSet {
    private LinkedList<Diagram> diagrams;

    public DataSet() {

    }

    public DataSet(LinkedList<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

    public LinkedList<Diagram> getDiagrams() {
        return this.diagrams;
    }

    public void setDiagrams(LinkedList<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

}
