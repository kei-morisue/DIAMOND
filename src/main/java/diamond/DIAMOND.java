package diamond;

import diamond.view.ui.MainFrame;

public class DIAMOND {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.screen2.zoom(1.0);
		mainFrame.setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
		mainFrame.setLocation(900, 500);
	}

}
