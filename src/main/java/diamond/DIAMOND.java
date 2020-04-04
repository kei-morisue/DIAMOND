package diamond;

import diamond.controller.Context;
import diamond.view.ui.frame.MainFrame;

public class DIAMOND {

    public static void main(String[] args) {
        Context context = new Context();
        MainFrame mainFrame = new MainFrame(context);
    }
}
