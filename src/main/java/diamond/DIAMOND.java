package diamond;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import diamond.view.ui.MainFrame;

public class DIAMOND {

	public static void main(String[] args) {
		setDarkmode();
		MainFrame mainFrame = new MainFrame();
		mainFrame.setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
		mainFrame.setLocation(900, 500);
	}

	private static void setDarkmode() {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
		}
	}

}
