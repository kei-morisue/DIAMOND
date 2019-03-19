/** DIAMOND - Origami Diagram Editor*/
package diamond.controller.file;

import java.util.LinkedList;

import diamond.model.geom.element.cp.Cp;

public class DataSet {
    private LinkedList<Cp> cps;

    public DataSet() {

    }

    public DataSet(LinkedList<Cp> CreasePatterns) {
        cps = CreasePatterns;
    }

    public LinkedList<Cp> getCps() {
        return this.cps;
    }

    public void setCps(LinkedList<Cp> cps) {
        this.cps = cps;
    }

}
