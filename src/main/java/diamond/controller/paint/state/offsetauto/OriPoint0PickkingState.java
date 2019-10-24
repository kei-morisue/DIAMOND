/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offsetauto;

import java.awt.geom.Point2D.Double;
import java.util.LinkedList;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.state.OriVertexPickingState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.Rectangle;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class OriPoint0PickkingState extends OriVertexPickingState {
    static final double norm = 0.02;

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);

    }

    @Override
    protected void undoAction(Context context) {
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setOffset(.0, .0);
        }
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        OriPoint p = pickedElements.getOriPoints().get(0);
        Palette palette = context.getPalette();
        OriModel oriModel = palette.getOriModel();
        Set<OriVertex> vertices = oriModel.getVertices();
        for (OriVertex vertex : vertices) {
            if (DistanceUtil.distance(p, vertex) < Constants.EPS) {
                Double offset = vertex.getOffset();
                if (Point2DUtil.norm(offset) < Constants.EPS) {
                    double scale = getScale(oriModel, vertex);
                    Double center = getCenter(oriModel);
                    autoOffset(center, vertex, scale);
                    continue;
                } else {
                    vertex.setOffset(.0, .0);
                    continue;
                }
            }
        }
        paintScreenContext.initialize();
    }

    private double getScale(OriModel oriModel, OriVertex vertex) {
        LinkedList<OriFace> faces = oriModel.getFaces();
        LinkedList<OriHalfEdge> halfEdges = vertex.getHalfEdges();
        int average = 0;
        int size = 0;
        for (OriHalfEdge he : halfEdges) {
            OriFace face = he.getFace();
            int index = faces.indexOf(face);

            if (index != -1) {
                average = average + index;
                ++size;
            }
        }
        average = average / size;
        return -average * 2 / faces.size() + 1;
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getOriModel().fold();
    }

    @Override
    public void setCandate(Context context) {
        // TODO 自動生成されたメソッド・スタブ
        super.setCandate(context);
    }

    private void autoOffset(Double center, OriVertex vertex, double scale) {
        Double v = vertex.getFoldedPosition();
        double x = v.getX() - center.x;
        double y = v.getY() - center.y;
        double l = scale * norm;
        vertex.setOffset(x * l, y * l);
    }

    private Double getCenter(OriModel oriModel) {
        Rectangle rectangle = null;
        for (OriFace face : oriModel.getFaces()) {
            Rectangle clipped = new Rectangle(face.getHalfEdges());
            if (rectangle == null) {
                rectangle = clipped;
            } else {
                rectangle.merge(clipped);
            }
        }
        Double center = rectangle.getCenter();
        return center;
    }
}
