/** DIAMOND - Origami Diagram Editor*/
package diamond.controller.file;

import java.util.LinkedList;

import diamond.model.palette.cp.CreasePattern;

public class DataSet {
    private LinkedList<CreasePattern> cps;

    public DataSet() {

    }

    public DataSet(LinkedList<CreasePattern> CreasePatterns) {
        cps = CreasePatterns;
    }

    public LinkedList<CreasePattern> getCps() {
        return this.cps;
    }

    public void setCps(LinkedList<CreasePattern> cps) {
        this.cps = cps;
    }

}
