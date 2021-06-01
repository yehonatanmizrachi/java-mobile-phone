package blackjack.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_WINDOWS;


public class BlackjackStatistics extends BlackjackWindow{

	public BlackjackStatistics(String title, BlackjackApp app) {
		super(title, app);
	}

	public void start() throws IOException {
		
		// frame
		String IMAGE_PATH = "Pic/STATISTICS.png";
		int WIDTH = 583, HEIGHT = 525;
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
        m_frame.add(background);
        
        m_frame.setSize(WIDTH, HEIGHT);
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        m_frame.setResizable(false);
        m_frame.setVisible(true);
        
        // info button
        JButton returnButton = new JButton();
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

        returnButton.addActionListener(getButtonEventListener(STATISTICS_BUTTONS.RETURN));
        returnButton.setBounds(RETURN_BUTTON_X + RETURN_BUTTON_WIDTH + INFO_PADD, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        background.add(returnButton);
        
	}
	
	private ActionListener getButtonEventListener(STATISTICS_BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == STATISTICS_BUTTONS.INFO) {
	       			m_app.startWindow(APP_WINDOWS.INFO);
	       		 }
	       		 else if (action == STATISTICS_BUTTONS.RETURN) {
	       			m_app.startWindow(APP_WINDOWS.MENU);
	       		 }
	       	 }
       }; 
	}
	
	private enum STATISTICS_BUTTONS {
		INFO,
		RETURN
	}

}
