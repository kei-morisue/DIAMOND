/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.action.AddVertexAction;
import diamond.controller.paint.action.Axiom1Action;
import diamond.controller.paint.action.Axiom2Action;
import diamond.controller.paint.action.Axiom3Action;
import diamond.controller.paint.action.Axiom4Action;
import diamond.controller.paint.action.BaseFaceAction;
import diamond.controller.paint.action.DeleteLineAction;
import diamond.controller.paint.action.DeleteVertexAction;
import diamond.controller.paint.action.FaceOrderingAction;
import diamond.controller.paint.action.FlipLineTypeAction;
import diamond.controller.paint.action.MirrorAction;
import diamond.controller.paint.action.ModifyContourAction;
import diamond.controller.paint.action.OffsetAction;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.controller.paint.action.SelectAllVertexAction;
import diamond.controller.paint.action.SelectLineAction;
import diamond.controller.paint.action.SelectVertexAction;
import diamond.controller.paint.action.SettleUnsettleLineAction;
import diamond.controller.paint.action.SymmetricLineAction;
import diamond.controller.paint.action.UnfoldLineAction;
import diamond.controller.paint.context.PaintContext;
import diamond.view.resource.IconSetter;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class PaintActionButton extends JRadioButton implements ActionListener {
    private PaintContext paintContext;
    private PaintActionInterface paintAction;

    public PaintActionButton(LABEL label, PaintContext context) {
        this.paintContext = context;
        switch (label) {
        case AXIOM1:
            setIcons("axiom1");
            this.paintAction = new Axiom1Action();
            break;
        case AXIOM2:
            setIcons("axiom2");
            this.paintAction = new Axiom2Action();
            break;
        case AXIOM3:
            setIcons("axiom3");
            this.paintAction = new Axiom3Action();
            break;
        case AXIOM4:
            setIcons("axiom4");
            this.paintAction = new Axiom4Action();
            break;
        case SYMMETRIC:
            setIcons("symmetric");
            this.paintAction = new SymmetricLineAction();
            break;
        case MIRROR:
            setIcons("mirror");
            this.paintAction = new MirrorAction();
            break;
        case FLIP_LINE_TYPE:
            setIcons("flip");
            this.paintAction = new FlipLineTypeAction();
            break;
        case FOLD_UNFOLD:
            setIcons("fold_unfold");
            this.paintAction = new UnfoldLineAction();
            break;
        case SETTLE_UNSETTLE:
            setIcons("settle_unsettle");
            this.paintAction = new SettleUnsettleLineAction();
            break;
        case DELETE_LINE:
            setIcons("delete");
            this.paintAction = new DeleteLineAction();
            break;
        case CONTOUR:
            setIcons("contour");
            this.paintAction = new ModifyContourAction();
            break;
        case SELECT_LINE:
            setIcons("select_l");
            this.paintAction = new SelectLineAction();
            break;
        case ADD_VERTEX:
            setIcons("add_v");
            this.paintAction = new AddVertexAction();
            break;
        case DELETE_VERTEX:
            setIcons("delete_v");
            this.paintAction = new DeleteVertexAction();
            break;
        case BASE_FACE:
            setIcons("base_face");
            this.paintAction = new BaseFaceAction();
            break;
        case FACE_TOP:
            setIcons("face_top");
            this.paintAction = new FaceOrderingAction();
            break;
        case FACE_BOTTOM:
            setIcons("face_bottom");
            this.paintAction = new FaceOrderingAction();
            break;
        case SELECT_VERTEX:
            setIcons("select_v");
            this.paintAction = new SelectVertexAction();
            break;
        case SELECT_ALL_VERTEX:
            setIcons("select_all_v");
            this.paintAction = new SelectAllVertexAction();
            break;
        case OFFSET:
            setIcons("offset");
            this.paintAction = new OffsetAction();
            break;
        default:
            break;
        }
        addActionListener(this);
    }

    private void setIcons(String iconBaseName) {
        IconSetter.set(this, iconBaseName + ".gif");
        IconSetter.setSelected(this, iconBaseName + "_p.gif");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            paintContext.initialize();
            paintContext.setPaintAction(paintAction);
        }
    }
}
