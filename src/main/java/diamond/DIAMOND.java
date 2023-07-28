package diamond;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import diamond.view.ui.frame.MainFrame;

public class DIAMOND {

	public static void main(
			String[] args) {
		setUILaf();
		new MainFrame();
	}

	private static void setUILaf() {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
