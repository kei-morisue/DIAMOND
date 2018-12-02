package diamond.viewsetting.main;

import java.awt.event.KeyEvent;

import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.copypaste.CopyAndPasteAction;
import diamond.paint.core.PaintConfig;
import diamond.viewsetting.ViewScreenUpdater;
import diamond.viewsetting.ViewSettingDataBase;

public class ScreenUpdater extends ViewSettingDataBase implements ViewScreenUpdater {

	//-------------------------
	// singleton
	//-------------------------
	private static ScreenUpdater instance = null;

	private ScreenUpdater() {
	}


	public static ScreenUpdater getInstance(){
		if(instance == null){
			instance = new ScreenUpdater();
		}

		return instance;
	}
	//-------------------------


	/* (非 Javadoc)
	 * @see oripa.viewsetting.main.ViewScreenUpdater#updateScreen()
	 */
	@Override
	public void updateScreen(){
		setChanged();
		notifyObservers(REDRAW_REQUESTED);

	}

	
	public class KeyListener implements java.awt.event.KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.isControlDown()){
				updateIfCopyAndPaste(true);
			}
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				updateScreen();
				
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateIfCopyAndPaste(false);
		}

	}
	
	private void updateIfCopyAndPaste(boolean changeOrigin){
		GraphicMouseActionInterface action = PaintConfig.getMouseAction();

		if(action instanceof CopyAndPasteAction){
			CopyAndPasteAction casted = (CopyAndPasteAction) action;
			casted.changeAction(changeOrigin);

			updateScreen();
		}
		
	}

	/* (非 Javadoc)
	 * @see oripa.viewsetting.main.ViewScreenUpdater#getKeyListener()
	 */
	@Override
	public java.awt.event.KeyListener getKeyListener() {
		return new KeyListener();
	}

}
