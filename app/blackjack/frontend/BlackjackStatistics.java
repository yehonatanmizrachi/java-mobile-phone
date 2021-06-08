package blackjack.frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_WINDOWS;
import blackjack.api.COMMAND;


public class BlackjackStatistics extends BlackjackWindow{
	
	private JLabel m_winningsLabel;
	private JLabel m_moneyLabel;

	public BlackjackStatistics(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		super(title, width, height, backgroundImage, app);
		init();
	}

	public void start() throws IOException {
		
		try {

			JSONObject request = new JSONObject();
			request.put("command", COMMAND.STATS);
			
			JSONObject response = m_app.sendMessageToBackend(request);
			
			int wins, totalGames;
			double money;
			wins = (int)response.get("wins");
			totalGames = (int)response.get("totalGames");
			money = (double)response.get("money");
			
			m_winningsLabel.setText(wins + "/" + totalGames);
	        m_moneyLabel.setText("$" + money);

		} catch (JSONException e) {
			e.printStackTrace();
		}

        m_frame.setVisible(true);

	}
	
	private void init() {
        
		// labels
        int LABEL_WIDTH = 300, LABEL_HEIGHT = 50, LABEL_X = 270, LABEL_Y = 213, PADD = 93, MONEY_OFFSET = -25;
        int FONT_SIZE = 45;
        String FONT_NAME = "Sans Serif";

        // winnings label
        m_winningsLabel = new JLabel();
        m_winningsLabel.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        m_winningsLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        m_background.add(m_winningsLabel);

        // money label
        m_moneyLabel = new JLabel();
        m_moneyLabel.setBounds(LABEL_X + MONEY_OFFSET, LABEL_Y + PADD, LABEL_WIDTH, LABEL_HEIGHT);
        m_moneyLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        m_background.add(m_moneyLabel);
        
        // buttons
        int RETURN_BUTTON_WIDTH = 40, RETURN_BUTTON_HEIGHT = 40, RETURN_BUTTON_X = 33, RETURN_BUTTON_Y = 425;
        int INFO_PADD = 18;
        
        super.addButton(RETURN_BUTTON_X, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT, getButtonEventListener(STATISTICS_BUTTONS.INFO));
        super.addButton(RETURN_BUTTON_X + RETURN_BUTTON_WIDTH + INFO_PADD, RETURN_BUTTON_Y, RETURN_BUTTON_WIDTH, RETURN_BUTTON_HEIGHT, getButtonEventListener(STATISTICS_BUTTONS.RETURN));
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
