package blackjack;


import java.io.IOException;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.backend.GameManager;
import blackjack.frontend.BlackjackInfo;
import blackjack.frontend.BlackjackMenu;
import blackjack.frontend.BlackjackStatistics;
import blackjack.frontend.BlackjackTable;
import blackjack.frontend.BlackjackWindow;
import src.App;


public class BlackjackApp implements App {

	private GameManager m_backend = new GameManager();
	
	private BlackjackWindow[] m_windows = {
		new BlackjackMenu("BlackJack - Menu", this),
		new BlackjackTable("BlackJack - Table",this),
		new BlackjackInfo("BlackJack - Info", this),
		new BlackjackStatistics("BlackJack - Statistics", this)
	};
	
	private Stack<BlackjackWindow> m_windowsStack = new Stack<BlackjackWindow>();

	public void run() throws IOException {
		startWindow(APP_WINDOWS.MENU);
	}

	@Override
	public String getAppContent() {
		return "BlackJack app is empty\n";
	}
	
	public void startWindow(APP_WINDOWS window) {
		try {
			m_windowsStack.push(m_windows[window.ordinal()]);
			m_windows[window.ordinal()].start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeWindow(APP_WINDOWS window) {
		m_windowsStack.pop();
	}
	
	public void goToPreviousWindow() {
		m_windowsStack.pop();
		BlackjackWindow window = m_windowsStack.peek();
		try {
			window.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cleanWindowStack() {
		while(!m_windowsStack.isEmpty()) {
			m_windowsStack.pop();
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
