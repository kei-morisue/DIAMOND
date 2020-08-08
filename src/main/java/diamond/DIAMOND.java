package diamond;

import diamond.controller.Context;
import diamond.controller.action.paint.PaintActionAxiom1;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.DiagramBuilder;
import diamond.view.ui.frame.MainFrame;

public class DIAMOND {

    public static void main(String[] args) {
        Diagram diagram = DiagramBuilder.craneBase();//TODO
        Context context = new Context(diagram);
        context.setPaintAction(new PaintActionAxiom1(context));//TODO
        new MainFrame(context);
    }
}
