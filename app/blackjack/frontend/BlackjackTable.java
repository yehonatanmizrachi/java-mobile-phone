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

	public BlackjackTable(String title, BlackjackApp app) {
		super(title, app);
	}

	private ArrayList<JLabel> m_labels = new ArrayList<JLabel>();
	private JLabel m_background;
	
	
	public void start() throws IOException {

		// background
		String IMAGE_PATH = "Pic/Table1.png";
		int WIDTH = 910, HEIGHT = 757;
	
		m_background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		m_background.setBounds(0, 0, WIDTH, HEIGHT);
        

        // return button
        JButton returnButton = new JButton();
        int RETURN_BUTTON_WIDTH = 70, RETURN_BUTTON_HEIGHT = 70, RETURN_BUTTON_X = 25, RETURN_BUTTON_Y = 630;
        returnButton.addActionListener(getButtonEventListener(GAME_BUTTONS.RETURN));
        returnButton.setBounds(RETURN_BUTTON_X, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);
    	m_background.add(returnButton);
        
    	// info button
    	int INFO_PADD = 15;
        JButton infoButton = new JButton();
        infoButton.addActionListener(getButtonEventListener(GAME_BUTTONS.INFO));
        infoButton.setBounds(RETURN_BUTTON_X + RETURN_BUTTON_WIDTH + INFO_PADD, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT);
        infoButton.setOpaque(false);
        infoButton.setContentAreaFilled(false);
        infoButton.setBorderPainted(false);
    	m_background.add(infoButton);

    	displayHitAndStandButtons();

    	refreshBackground();
    	
        m_frame.setSize(WIDTH, HEIGHT);
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        m_frame.setResizable(false);
        m_frame.setVisible(true);
        
        fillTable();
        
        m_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                m_frame.setVisible(false);             
                App.phone.returnToPhone();
            }
        });
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

	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

}
