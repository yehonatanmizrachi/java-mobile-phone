package blackjack;


import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.backend.GameManager;
import blackjack.frontend.BlackjackInfo;
import blackjack.frontend.BlackjackMenu;
import blackjack.frontend.BlackjackStatistics;
import blackjack.frontend.BlackjackTable;
import blackjack.frontend.BlackjackWindow;
import src.App;


public class BlackjackApp implements App{

	private GameManager m_backend = new GameManager();
	
	private BlackjackWindow[] m_windows = {
		new BlackjackMenu("BlackJack - Menu", this),
		new BlackjackTable("BlackJack - Table",this),
		new BlackjackInfo("BlackJack - Info", this),
		new BlackjackStatistics("BlackJack - Statistics", this)
	};
	
	public void run() throws IOException {
		startWindow(APP_WINDOWS.MENU);
	}

	@Override
	public String getAppContent() {
		return "BlackJack app is empty\n";
	}
	
	public void startWindow(APP_WINDOWS window) {
		try {
			m_windows[window.ordinal()].start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JSONObject sendMessageToBackend(JSONObject json) {
		JSONObject response = null;
		try {
			response = m_backend.sendCommand(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

	public enum APP_WINDOWS {
		MENU,
		TABLE,
		INFO,
		STATISTICS
	}
}
