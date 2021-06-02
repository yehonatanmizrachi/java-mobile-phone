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

		// background
		String IMAGE_PATH = "Pic/INFO.png";
		int WIDTH = 583, HEIGHT = 525;
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, WIDTH, HEIGHT);

		// buttons
		int BUTTON_WIDTH = 40, BUTTON_HEIGHT = 40, BUTTON_X = 33, BUTTON_Y = 425, PADD = 18;
        
		// return button
        JButton returnButton = new JButton();
        returnButton.addActionListener(getButtonEventListener(INFO_BUTTONS.RETURN));
        returnButton.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
        
    	// home button
        JButton infoButton = new JButton();
        infoButton.addActionListener(getButtonEventListener(INFO_BUTTONS.HOME));
        infoButton.setBounds(BUTTON_X + BUTTON_WIDTH + PADD, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        infoButton.setOpaque(false);
        infoButton.setContentAreaFilled(false);
        infoButton.setBorderPainted(false);
        
        
        // LIFO
        background.add(returnButton);
        background.add(infoButton);
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

	private enum INFO_BUTTONS {
		RETURN,
		HOME
	}

	private ActionListener getButtonEventListener(INFO_BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == INFO_BUTTONS.RETURN) {
	       			m_app.goToPreviousWindow();
	       		 }
	       		 else if (action == INFO_BUTTONS.HOME) {
	       			m_app.cleanWindowStack();
	       			m_app.startWindow(APP_WINDOWS.MENU);
	       		 }
	       	 }
       }; 
	}
	
}
