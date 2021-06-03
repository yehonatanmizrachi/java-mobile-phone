package blackjack;


import java.io.IOException;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.GAME_STATUS;
import blackjack.backend.GameManager;
import blackjack.frontend.BlackjackEndGame;
import blackjack.frontend.BlackjackInfo;
import blackjack.frontend.BlackjackMenu;
import blackjack.frontend.BlackjackStatistics;
import blackjack.frontend.BlackjackTable;
import blackjack.frontend.BlackjackWindow;
import src.App;


public class BlackjackApp implements App {

	public void run() throws IOException {
		startWindow(APP_WINDOWS.MENU);
	}

	@Override
	public String getAppContent() {
		return "BlackJack app is empty\n";
	}

	private GameManager m_backend;
	private GAME_STATUS m_gameStatus;
	
	private BlackjackWindow[] m_windows;
	private Stack<BlackjackWindow> m_windowsStack;

	public BlackjackApp() {
		
		m_backend = new GameManager();
		
		m_windows = new BlackjackWindow[]{
				new BlackjackMenu(APPS_TITLES[APP_WINDOWS.MENU.ordinal()], APPS_SIZES[APP_WINDOWS.MENU.ordinal()][0], APPS_SIZES[APP_WINDOWS.MENU.ordinal()][1], APPS_BACKGROUDS[APP_WINDOWS.MENU.ordinal()], this),
				new BlackjackTable(APPS_TITLES[APP_WINDOWS.TABLE.ordinal()], APPS_SIZES[APP_WINDOWS.TABLE.ordinal()][0], APPS_SIZES[APP_WINDOWS.TABLE.ordinal()][1], APPS_BACKGROUDS[APP_WINDOWS.TABLE.ordinal()], this),
				new BlackjackInfo(APPS_TITLES[APP_WINDOWS.INFO.ordinal()], APPS_SIZES[APP_WINDOWS.INFO.ordinal()][0], APPS_SIZES[APP_WINDOWS.INFO.ordinal()][1], APPS_BACKGROUDS[APP_WINDOWS.INFO.ordinal()], this),
				new BlackjackStatistics(APPS_TITLES[APP_WINDOWS.STATISTICS.ordinal()], APPS_SIZES[APP_WINDOWS.STATISTICS.ordinal()][0], APPS_SIZES[APP_WINDOWS.STATISTICS.ordinal()][1], APPS_BACKGROUDS[APP_WINDOWS.STATISTICS.ordinal()], this),
				new BlackjackEndGame(APPS_TITLES[APP_WINDOWS.END_GAME.ordinal()], APPS_SIZES[APP_WINDOWS.END_GAME.ordinal()][0], APPS_SIZES[APP_WINDOWS.END_GAME.ordinal()][1], APPS_BACKGROUDS[APP_WINDOWS.END_GAME.ordinal()], this),
		};
		
		m_windowsStack = new Stack<BlackjackWindow>();
	}
	
	public GAME_STATUS getGameStatus() {
		return m_gameStatus;
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
			m_gameStatus = (GAME_STATUS)response.get("status");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

	public enum APP_WINDOWS {
		MENU,
		TABLE,
		INFO,
		STATISTICS,
		END_GAME
	}

	// [width, height]
	private int[][] APPS_SIZES = {
			{590, 530},
			{910, 757},
			{583, 525},
			{583, 525},
			{583, 275}
	};
	
	private String[] APPS_BACKGROUDS = {
			"Pic/Menu.png",
			"Pic/Table1.png",
			"Pic/INFO.png",
			"Pic/STATISTICS.png",
			null
	};

	private String[] APPS_TITLES = {
			"BlackJack - Menu",
			"BlackJack - Table",
			"BlackJack - Info",
			"BlackJack - Statistics",
			"BlackJack - End Game"
	};
}
