package diamond;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.DiagramBuilder;
import diamond.view.ui.frame.MainFrame;

public class DIAMOND {

    public static void main(String[] args) {
        Diagram diagram = DiagramBuilder.craneBase();//TODO
        Context context = new Context(diagram);
        new MainFrame(context);
    }
}
