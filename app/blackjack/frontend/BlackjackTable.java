package blackjack.frontend;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPanel;

import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_WINDOWS;
import blackjack.backend.Card;
import src.App;

public class BlackjackTable extends BlackjackWindow{

	public BlackjackTable(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		super(title, width, height, backgroundImage, app);
	}

	private ArrayList<JLabel> m_labels = new ArrayList<JLabel>();
	
	public void start() throws IOException {

        // buttons
        int BUTTON_WIDTH = 70, BUTTON_HEIGHT = 70, BUTTON_X = 15, BUTTON_Y = 630;
        int INFO_PADD = 25;

        super.addButton(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(GAME_BUTTONS.RETURN));
        super.addButton(BUTTON_X + BUTTON_WIDTH + INFO_PADD, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(GAME_BUTTONS.INFO));

    	displayHitAndStandButtons();

    	refreshBackground();
    	
        fillTable();
        
        super.startFrame();
	}

	private void cleanTable() {
		m_frame.remove(m_background);
		for (JLabel label : m_labels) {
			label.setVisible(false);
			m_frame.remove(label);
		}
	}

	private void fillTable() {

		try {
			// TODO
			displayCards(new Card[4], false);
			displayCards(new Card[4], true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		refreshBackground();
	}
	
	private void refreshBackground() {
		m_frame.remove(m_background);
		m_frame.add(m_background);
	}

	private void displayCards(Card[] cards, Boolean isPlayer) throws IOException {
		
		int PLAYER_INITIAL_X = 200, DEALER_INITIAL_X = 200;
		int initial_x = isPlayer ? PLAYER_INITIAL_X : DEALER_INITIAL_X;
		
		int PLAYER_INITIAL_Y = 550, DEALER_INITIAL_Y = 330;
		int initial_y = isPlayer ? PLAYER_INITIAL_Y : DEALER_INITIAL_Y;
		
		int CARD_PADDING = 10;
		int CARD_WIDTH = 70, CARD_HEIGHT = 99;
		for(int i=0; i < cards.length; i++) {
			JLabel label = new JLabel(new ImageIcon(getScaledImage(ImageIO.read(new File("Pic/cards/2H.png")), CARD_WIDTH, CARD_HEIGHT)));
			label.setBounds(initial_x + (CARD_WIDTH + CARD_PADDING) * i, initial_y, CARD_WIDTH, CARD_HEIGHT);
			m_frame.add(label);
			m_labels.add(label);
		}
	}

	private void displayHitAndStandButtons() throws IOException {
		
		String hitPath = "Pic/HIT.png";
		String standPath = "Pic/STAND.png";
		
		int BUTTON_WIDTH = 100, BUTTON_HEIGHT = 100;
		int INITIAL_X = 280, INITIAL_Y = 440, PADD = 80;
		
		JLabel hit_label = new JLabel(new ImageIcon(getScaledImage(ImageIO.read(new File(hitPath)), BUTTON_WIDTH, BUTTON_HEIGHT)));
		hit_label.setBounds(INITIAL_X, INITIAL_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// HIT method
		hit_label.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	cleanTable();
		        // TODO: set and get the data from the backend
				// JSONObject response = m_app.sendMessageToBackend(null);
				fillTable();			
			
		    }  
		});

		m_frame.add(hit_label);
		
		
		JLabel stand_label = new JLabel(new ImageIcon(getScaledImage(ImageIO.read(new File(standPath)), BUTTON_WIDTH, BUTTON_HEIGHT)));
		stand_label.setBounds(INITIAL_X + BUTTON_WIDTH + PADD, INITIAL_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// STAND method
		stand_label.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		       // TODO: send json to backend
		    	m_app.startWindow(APP_WINDOWS.END_GAME);
		    	System.out.print(GAME_BUTTONS.STAND);
		    }  
		});

		m_frame.add(stand_label);
	}

	private enum GAME_BUTTONS {
		RETURN,
		INFO,
		HIT,
		STAND
	}

	private ActionListener getButtonEventListener(GAME_BUTTONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == GAME_BUTTONS.RETURN) {
	       			m_app.goToPreviousWindow();
	       		 }
	       		 else if (action == GAME_BUTTONS.INFO) {
	       			m_app.startWindow(APP_WINDOWS.INFO);
	       		 }
	       	 }
       }; 
	}

}
