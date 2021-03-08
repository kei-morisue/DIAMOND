/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

/**
 * @author Kei Morisue
 *
 */
public class Folder {
    //    private ArrayList<Face> faces;
    //
    //    public Folder(Step step) {
    //        this.faces = step.getFaces();
    //        fold(step);
    //    }
    //
    //    public void fold(Step step) {
    //        Face base = step.getBase();
    //        base.setMirror(new MirrorLazy());
    //        setMirror(base, step);
    //    }
    //
    //    public void setMirror(Face base, Step step) {
    //        AbstractMirror mirror = base.getMirror();
    //        int i = faces.indexOf(base);
    //        for (SegmentEdge edge : step.getEdges()) {
    //            Face pair = edge.getPair(base);
    //            if (pair == null) {
    //                continue;
    //            }
    //            if (pair.getMirror() == null) {
    //                int j = faces.indexOf(pair);
    //                edge.setType((i - j) < 0 ^ base.isFlip());
    //                pair.setMirror(new MirrorComposit(edge, mirror));
    //                setMirror(pair, step);
    //            }
    //        }
    //    }
}
