package blackjack.frontend;

import java.awt.Font;
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

import org.json.JSONObject;

import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_WINDOWS;
import src.App;


public class BlackjackStatistics extends BlackjackWindow{

	public BlackjackStatistics(String title, BlackjackApp app) {
		super(title, app);
	}

	public void start() throws IOException {
		
		// TODO: get the data from the backend
		// JSONObject response = m_app.sendMessageToBackend(null);

		int wins = 10, totalGames = 20;
		float winnings = wins / (float)totalGames * 100;

		int money = 20000;

		// background
		String IMAGE_PATH = "Pic/STATISTICS.png";
		int WIDTH = 583, HEIGHT = 525;
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
        
        
        /// labels
        int LABEL_WIDTH = 300, LABEL_HEIGHT = 50, LABEL_X = 270, LABEL_Y = 213, PADD = 93, MONEY_OFFSET = -25;
        int FONT_SIZE = 45;
        String FONT_NAME = "Sans Serif";

        // winnings label
        JLabel winningsLabel = new JLabel(winnings + "%");
        winningsLabel.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        winningsLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));

        // money label
        JLabel moneyLabel = new JLabel("$" + money);
        moneyLabel.setBounds(LABEL_X + MONEY_OFFSET, LABEL_Y + PADD, LABEL_WIDTH, LABEL_HEIGHT);
        moneyLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        
        
        // buttons
        int RETURN_BUTTON_WIDTH = 40, RETURN_BUTTON_HEIGHT = 40, RETURN_BUTTON_X = 33, RETURN_BUTTON_Y = 425;
        int INFO_PADD = 18;

        // return button
        JButton infoButton = new JButton();
        infoButton.addActionListener(getButtonEventListener(STATISTICS_BUTTONS.INFO));
        infoButton.setBounds(RETURN_BUTTON_X, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        infoButton.setOpaque(false);
        infoButton.setContentAreaFilled(false);
        infoButton.setBorderPainted(false);
        background.add(infoButton);

        // info button
        JButton returnButton = new JButton();
        returnButton.addActionListener(getButtonEventListener(STATISTICS_BUTTONS.RETURN));
        returnButton.setBounds(RETURN_BUTTON_X + RETURN_BUTTON_WIDTH + INFO_PADD, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        background.add(returnButton);
        
        // LIFO
        m_frame.add(moneyLabel);
        m_frame.add(winningsLabel);
        m_frame.add(background);
        
        
        m_frame.setSize(WIDTH, HEIGHT);
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
	
	private enum STATISTICS_BUTTONS {
		INFO,
		RETURN
	}

	private ActionListener getButtonEventListener(STATISTICS_BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == STATISTICS_BUTTONS.INFO) {
	       			m_app.startWindow(APP_WINDOWS.INFO);
	       		 }
	       		 else if (action == STATISTICS_BUTTONS.RETURN) {
	       			m_app.goToPreviousWindow();
	       		 }
	       	 }
       }; 
	}
}
