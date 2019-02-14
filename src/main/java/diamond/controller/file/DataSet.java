/** DIAMOND - Origami Diagram Editor*/
package diamond.controller.file;

import java.util.Set;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.palette.cp.CreasePattern;

public class DataSet {
    private Set<OriLine> lines;

    public DataSet() {

    }

    public DataSet(CreasePattern CreasePattern) {
        lines = CreasePattern.getLines();
    }

    public Set<OriLine> getLines() {
        return this.lines;
    }

    public void setLines(Set<OriLine> lines) {
        this.lines = lines;
    }

}
