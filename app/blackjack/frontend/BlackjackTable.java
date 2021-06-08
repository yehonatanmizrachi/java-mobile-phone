package blackjack.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_SOUNDS;
import blackjack.BlackjackApp.APP_WINDOWS;
import blackjack.api.COMMAND;
import blackjack.api.GAME_STATUS;

public class BlackjackTable extends BlackjackWindow{

	public BlackjackTable(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		super(title, width, height, backgroundImage, app);
		m_cardsLabels = new ArrayList<JLabel>();
		init();
	}

	private ArrayList<JLabel> m_cardsLabels;
	private JLabel m_hitLabel = null;
	private JLabel m_standLabel = null;

	public void start() throws IOException {
		
		// send message to backend
		JSONObject response = null;
		try {
			JSONObject request = new JSONObject();
			request.put("command", COMMAND.START_GAME);
			response = m_app.sendMessageToBackend(request);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		cleanTable();

    	m_frame.setVisible(true);

		// play shuffling sound 🎧
        m_app.playAudio(APP_SOUNDS.SHUFFLE);
        
    	displayHitAndStandButtons();
    	
        fillTable(response);
        
        checkGameStatus();
	}


	private void init() {

        // buttons
        int BUTTON_WIDTH = 70, BUTTON_HEIGHT = 70, BUTTON_X = 15, BUTTON_Y = 630;
        int INFO_PADD = 25;

        super.addButton(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(GAME_BUTTONS.RETURN));
        super.addButton(BUTTON_X + BUTTON_WIDTH + INFO_PADD, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(GAME_BUTTONS.INFO));
        
        try {
			initHitAndStandButtons();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cleanTable() {
		m_frame.remove(m_background);
		for (JLabel label : m_cardsLabels) {
			label.setVisible(false);
			m_frame.remove(label);
		}
		
		m_cardsLabels.clear();
	}

	private void fillTable(JSONObject response) {

		try {
			// TODO
			displayCards(response, false);
			displayCards(response, true);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}

		refreshFrame();
	}
	
	private void refreshFrame() {
		m_frame.remove(m_background);
		m_frame.add(m_background);
		m_background.setVisible(false);
		m_background.setVisible(true);
	}

	private void displayCards(JSONObject response, Boolean isPlayer) throws IOException, JSONException {
		
		int PLAYER_INITIAL_X = 200, DEALER_INITIAL_X = 200;
		int initial_x = isPlayer ? PLAYER_INITIAL_X : DEALER_INITIAL_X;
			
		int PLAYER_INITIAL_Y = 550, DEALER_INITIAL_Y = 330;
		int initial_y = isPlayer ? PLAYER_INITIAL_Y : DEALER_INITIAL_Y;
		
		int CARD_PADDING = 10;
		int CARD_WIDTH = 70, CARD_HEIGHT = 99;
		
		String JSONKey = isPlayer ? "player" : "dealer";
		JSONObject playerObj = (JSONObject)response.get(JSONKey);
		JSONArray cards = (JSONArray)(playerObj.get("cards")); 

		for(int i = 0; i < cards.length(); i++) {
			JSONObject card = (JSONObject)((JSONObject)cards.get(i)).get("cardInfo");
			JLabel label = new JLabel(new ImageIcon(getScaledImage(ImageIO.read(new File((String)card.get("pic"))), CARD_WIDTH, CARD_HEIGHT)));
			label.setBounds(initial_x + (CARD_WIDTH + CARD_PADDING) * i, initial_y, CARD_WIDTH, CARD_HEIGHT);
			m_cardsLabels.add(label);
			m_frame.add(label);
		}
	}

	private void initHitAndStandButtons() throws IOException {
		
		String hitPath = "pics/HIT.png";
		String standPath = "pics/STAND.png";
		
		int BUTTON_WIDTH = 100, BUTTON_HEIGHT = 100;
		int INITIAL_X = 280, INITIAL_Y = 440, PADD = 80;
		
		m_hitLabel = new JLabel(new ImageIcon(getScaledImage(ImageIO.read(new File(hitPath)), BUTTON_WIDTH, BUTTON_HEIGHT)));
		m_hitLabel.setBounds(INITIAL_X, INITIAL_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// HIT method
		m_hitLabel.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	cleanTable();
		    	JSONObject request = new JSONObject();
		    	try {
					request.put("command", COMMAND.HIT);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

		    	JSONObject response = m_app.sendMessageToBackend(request);
		    	
	    		m_app.playAudio(APP_SOUNDS.CARD);
		    	fillTable(response);

		    	checkGameStatus();
		    }  
		});


		m_standLabel = new JLabel(new ImageIcon(getScaledImage(ImageIO.read(new File(standPath)), BUTTON_WIDTH, BUTTON_HEIGHT)));
		m_standLabel.setBounds(INITIAL_X + BUTTON_WIDTH + PADD, INITIAL_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		// STAND method
		m_standLabel.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	
		    	
		    	hideHitAndStandButtons();

		    	JSONObject request = new JSONObject();
		    	try {
					request.put("command", COMMAND.STAND);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

		    	JSONObject response = m_app.sendMessageToBackend(request);
		    	

		    	Timer timer = new Timer();
		    	timer.schedule(new TimerTask() {
		    		  @Override
		    		  public void run() {
		    			cleanTable();
		    			fillTable(response);

		    			GAME_STATUS status = m_app.getGameStatus();

		    			if (status == GAME_STATUS.DEALER_WINS || status == GAME_STATUS.PLAYER_WINS) {
		    				endGame();
		    			}

						if (m_app.getGameStatus() == GAME_STATUS.DEALER_TURN) {
							m_app.playAudio(APP_SOUNDS.CARD);
							mouseClicked(e);
						}

		    		  }
		    		}, 2*1000);
		    }  
		});

	}
	
	private void displayHitAndStandButtons() throws IOException {

		if (m_hitLabel != null) {
			m_frame.remove(m_hitLabel);
		}
		
		if (m_standLabel != null) {
			m_frame.remove(m_standLabel);
		}
		
		
		m_frame.add(m_hitLabel);
		m_frame.add(m_standLabel);
		refreshFrame();
	}

	private void endGame() {
		hideHitAndStandButtons();

		float timeout = 2.5f;
		Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
    		  @Override
    		  public void run() {
    			  m_frame.setVisible(false);
    			  m_app.startWindow(APP_WINDOWS.END_GAME);
    		  }
		}, (int)timeout*1000);
	}

	private void checkGameStatus() {
		GAME_STATUS status = m_app.getGameStatus();
		if (status == GAME_STATUS.DEALER_WINS || status == GAME_STATUS.PLAYER_WINS) {
			endGame();
		}
	}

	private void hideHitAndStandButtons() {
		m_frame.remove(m_hitLabel);
		m_frame.remove(m_standLabel);
		refreshFrame();
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
