import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.MemoryImageSource;

public class Main extends JFrame{

	public Main () {
		super("Heavens Arena");
		Game play = new Game();
		((Component) play).setFocusable(true);
		setBackground(Color.WHITE);
		getContentPane().add(play);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		
		addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				play.makeSave();
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				play.writeSave();
				System.exit(-1);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				play.readSave();
			}
		});
		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
	}


}
