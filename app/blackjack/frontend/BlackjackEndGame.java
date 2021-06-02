package blackjack.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_WINDOWS;
import src.App;

public class BlackjackEndGame extends BlackjackWindow{

	public BlackjackEndGame(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		super(title, width, height, backgroundImage, app);
	}
	
	public void start() throws IOException {

		// background
		m_app.getGameStatus();
		Boolean playerWon = true;
		String IMAGE_PATH = playerWon ? "Pic/YOUWON.png" : "Pic/GAMEOVER.png";
	
		m_background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		m_background.setBounds(0, 0, m_width, m_height);

		// buttons
		int BUTTON_WIDTH = 100, BUTTON_HEIGHT = 100, BUTTON_X = 130, BUTTON_Y = 110, PADD = 127;

		super.addButton(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(BUTTONS.PLAY_AGAIN));
        super.addButton(BUTTON_X + BUTTON_WIDTH + PADD, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(BUTTONS.HOME));

        super.startFrame();
	}

	private enum BUTTONS {
		PLAY_AGAIN,
		HOME
	}

	private ActionListener getButtonEventListener(BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == BUTTONS.PLAY_AGAIN) {
	       			// TODO: send start game to backend
	       			m_app.goToPreviousWindow();
	       		 }
	       		 else if (action == BUTTONS.HOME) {
	       			m_app.cleanWindowStack();
	       			m_app.startWindow(APP_WINDOWS.MENU);
	       		 }
	       	 }
       }; 
	}
	
}
