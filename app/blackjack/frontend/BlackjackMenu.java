package blackjack.frontend;

import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blackjack.backend.GameManager;
import blackjack.backend.Card;
import src.App;

public class BlackjackMenu {

	private GameManager m_backend = new GameManager();
	private BlackjackTable m_table = new BlackjackTable(this, m_backend);
	private BlackjackInfo m_info = new BlackjackInfo(this, m_table);
	private JFrame frame = new JFrame("BlackJack - Menu");

	public void run() throws IOException {
		startMenu();
	}

	public void startMenu() throws IOException {
		
		// frame
		String IMAGE_PATH = "Pic/Menu.png";
		int WIDTH = 590, HEIGHT = 530;
	
        JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
        label.setBounds(0, 0, WIDTH, HEIGHT);
        frame.add(label);

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
        		        			
        	label.add(buttons[index]);
        }
        
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                frame.setVisible(false);             
                App.phone.returnToPhone();
            }
        });
	}

	public void returnToMenu() {
		frame.setVisible(true);
	}

	
	public void startInfo() {
		try {
			m_info.showInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ActionListener getButtonEventListener(MENU action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 System.out.print(action);
	       		 if (action == MENU.START) {
	       			frame.setVisible(false);
   					try {
						m_table.startGame();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       		 }
	       		 else if (action == MENU.EXIT) {
	       			frame.setVisible(false);             
	                App.phone.returnToPhone(); 
	       		 }
	       	 }
       }; 
	}

	private enum MENU {
		START,
		STATISTICS,
		EXIT
	}
	
}
