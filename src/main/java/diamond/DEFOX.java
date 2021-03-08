package diamond;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.Diagram;
import diamond.view.ui.frame.MainFrame;

public class DEFOX {

    public static void main(String[] args) {
        Context context = new Context(new Diagram());//TODO
        new MainFrame(context);
    }
}
