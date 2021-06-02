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

	public BlackjackEndGame(String title, int width, int height, BlackjackApp app) {
		super(title, width, height, app);
	}
	
	public void start() throws IOException {

		// background
		Boolean playerWon = true;
		String IMAGE_PATH = playerWon ? "Pic/WIN.png" : "Pic/LOOSE.png";
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, m_width, m_height);

		// buttons
		int BUTTON_WIDTH = 40, BUTTON_HEIGHT = 40, BUTTON_X = 33, BUTTON_Y = 425, PADD = 18;
        
		// play again button
        JButton playAgain = new JButton();
        playAgain.addActionListener(getButtonEventListener(BUTTONS.PLAY_AGAIN));
        playAgain.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        playAgain.setOpaque(false);
        playAgain.setContentAreaFilled(false);
        playAgain.setBorderPainted(false);
        
    	// home button
        JButton homeButton = new JButton();
        homeButton.addActionListener(getButtonEventListener(BUTTONS.HOME));
        homeButton.setBounds(BUTTON_X + BUTTON_WIDTH + PADD, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        
        
        // LIFO
        background.add(playAgain);
        background.add(homeButton);
        m_frame.add(background);


        m_frame.setSize(m_width, m_height);
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        m_frame.setResizable(false);
        m_frame.setVisible(true);
        
        m_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
            	m_frame.setVisible(false);             
                App.phone.returnToPhone();
            }
        });
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
