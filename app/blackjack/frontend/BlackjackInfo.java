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

public class BlackjackInfo extends BlackjackWindow{

	public BlackjackInfo(String title, BlackjackApp app) {
		super(title, app);
	}
	
	public void start() throws IOException {

		// frame
		String IMAGE_PATH = "Pic/INFO.png";
		int WIDTH = 583, HEIGHT = 525;
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
        m_frame.add(background);

        // return button
        JButton returnButton = new JButton();
        int RETURN_BUTTON_WIDTH = 40, RETURN_BUTTON_HEIGHT = 40, RETURN_BUTTON_X = 33, RETURN_BUTTON_Y = 425;
        int INFO_PADD = 18;

        returnButton.addActionListener(getButtonEventListener(INFO_BUTTONS.RETURN));
        returnButton.setBounds(RETURN_BUTTON_X, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        background.add(returnButton);
        
    	// home button
        JButton infoButton = new JButton();
        infoButton.addActionListener(getButtonEventListener(INFO_BUTTONS.HOME));
        infoButton.setBounds(RETURN_BUTTON_X + RETURN_BUTTON_WIDTH + INFO_PADD, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        infoButton.setOpaque(false);
        infoButton.setContentAreaFilled(false);
        infoButton.setBorderPainted(false);
        background.add(infoButton);

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

	private ActionListener getButtonEventListener(INFO_BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == INFO_BUTTONS.RETURN) {
	       			m_app.startWindow(APP_WINDOWS.TABLE);
	       		 }
	       		 else if (action == INFO_BUTTONS.HOME) {
	       			m_app.startWindow(APP_WINDOWS.MENU);
	       		 }
	       	 }
       }; 
	}
	
	private enum INFO_BUTTONS {
		RETURN,
		HOME
	}
	
}
