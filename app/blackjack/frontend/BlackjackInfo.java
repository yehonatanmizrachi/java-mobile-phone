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
import src.App;

public class BlackjackInfo {

	public BlackjackInfo(BlackjackMenu blackjackMenu, BlackjackTable blackjackTable) {
		m_blackjackMenu = blackjackMenu;
		m_blackjackTable = blackjackTable;
	}
	
	private BlackjackMenu m_blackjackMenu;
	private BlackjackTable m_blackjackTable;

	private JFrame frame = new JFrame("BlackJack - Info");	
	
	public void showInfo() throws IOException {

		// frame
		String IMAGE_PATH = "Pic/INFO.png";
		int WIDTH = 583, HEIGHT = 525;
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
        frame.add(background);

        // return button
        JButton returnButton = new JButton();
        int RETURN_BUTTON_WIDTH = 70, RETURN_BUTTON_HEIGHT = 70, RETURN_BUTTON_X = 25, RETURN_BUTTON_Y = 300;
        returnButton.addActionListener(getButtonEventListener(INFO_BUTTONS.RETURN));
        returnButton.setBounds(RETURN_BUTTON_X, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
//        returnButton.setOpaque(false);
//        returnButton.setContentAreaFilled(false);
//        returnButton.setBorderPainted(false);
        background.add(returnButton);
        
    	// home button
    	int INFO_PADD = 15;
        JButton infoButton = new JButton();
        infoButton.addActionListener(getButtonEventListener(INFO_BUTTONS.HOME));
        infoButton.setBounds(RETURN_BUTTON_X + RETURN_BUTTON_WIDTH + INFO_PADD, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
//        infoButton.setOpaque(false);
//        infoButton.setContentAreaFilled(false);
//        infoButton.setBorderPainted(false);
        background.add(infoButton);

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        frame.setResizable(false);
        frame.setVisible(true);
        
        

        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                frame.setVisible(false);             
                App.phone.returnToPhone();
            }
        });
	}

	private ActionListener getButtonEventListener(INFO_BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 System.out.print(action);
	       		 if (action == INFO_BUTTONS.RETURN) {
	       			frame.setVisible(false);
	       			m_blackjackTable.continueGame();
	       		 }
	       		 else if (action == INFO_BUTTONS.HOME) {
	       			frame.setVisible(false);    
	                m_blackjackMenu.returnToMenu(); 
	       		 }
	       	 }
       }; 
	}
	
	private enum INFO_BUTTONS {
		RETURN,
		HOME
	}
	
}
