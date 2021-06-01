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

public class BlackjackMenu extends BlackjackWindow{

	public BlackjackMenu(String title, BlackjackApp app) {
		super(title, app);
	}

	public void start() throws IOException {
		
		// background
		String IMAGE_PATH = "Pic/Menu.png";
		int WIDTH = 590, HEIGHT = 530;
	
        JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
        background.setBounds(0, 0, WIDTH, HEIGHT);
        

        // buttons
        int BUTTONS_COUNT = 3;
        JButton[] buttons = new JButton[BUTTONS_COUNT];

        // buttons layout
        int BUTTON_WIDTH = 345, BUTTON_HEIGHT = 60, BUTTON_PADDING = 35; 
        int INITIAL_X = 115, INITIAL_Y = 175	;
        int[][] BUTTONS_LOCATIONS = new int[BUTTONS_COUNT][2];
    	for (int i = 0; i < BUTTONS_COUNT; i++) {
    		// set x
    		BUTTONS_LOCATIONS[i][0] = INITIAL_X;
    		// set y
			BUTTONS_LOCATIONS[i][1] = INITIAL_Y + (BUTTON_HEIGHT + BUTTON_PADDING)*i;
    	}
        
    	for (int index = 0; index < BUTTONS_COUNT; index++) {
    		buttons[index] = new JButton();
        	buttons[index].addActionListener(getButtonEventListener(MENU.values()[index]));
        	buttons[index].setBounds(BUTTONS_LOCATIONS[index][0], BUTTONS_LOCATIONS[index][1], BUTTON_WIDTH, BUTTON_HEIGHT);
        	buttons[index].setOpaque(false);
        	buttons[index].setContentAreaFilled(false);
        	buttons[index].setBorderPainted(false);
        		        			
        	background.add(buttons[index]);
        }

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


	private enum MENU {
		START,
		STATISTICS,
		EXIT
	}

	private ActionListener getButtonEventListener(MENU action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){	       		
       			m_frame.setVisible(false);
	       		 if (action == MENU.START) {
					m_app.startWindow(APP_WINDOWS.TABLE);
	       		 }
	       		 else if (action == MENU.STATISTICS) {
	       			m_app.startWindow(APP_WINDOWS.STATISTICS);
	       		 }
	       		 else if (action == MENU.EXIT) {
	       			m_app.closeWindow(APP_WINDOWS.MENU);
	                App.phone.returnToPhone(); 
	       		 }	
	       	 }	       	 
       }; 
	}
	
}
