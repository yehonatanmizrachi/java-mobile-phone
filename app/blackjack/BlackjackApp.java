package blackjack;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.COMMAND;
import blackjack.api.GAME_STATUS;
import blackjack.backend.GameManager;
import blackjack.frontend.BlackjackEndGame;
import blackjack.frontend.BlackjackInfo;
import blackjack.frontend.BlackjackMenu;
import blackjack.frontend.BlackjackStatistics;
import blackjack.frontend.BlackjackTable;
import blackjack.frontend.BlackjackWindow;
import javazoom.jl.player.Player;
import src.App;


public class BlackjackApp implements App {

	@Override
	public void run() throws IOException {
		startWindow(APP_WINDOWS.MENU);
	}

	@Override
	public String getAppContent() {
		String content = "BlackJack app content:\n";
		try {
			JSONObject request = new JSONObject();
			request.put("command", COMMAND.STATS);
			
			// get statistics from the backend
			JSONObject response = sendMessageToBackend(request);
			int wins, totalGames;
			double money;
			wins = (int)response.get("wins");
			totalGames = (int)response.get("totalGames");
			money = (double)response.get("money");
			
			content += "winnings: " + wins + "/" + totalGames + "\n";
			content += "money: " + money + "\n";
		} catch (JSONException e) {
			content += "BlackJack app is empty\n";
			e.printStackTrace();
		}
	
		return content;
	}

	private GameManager m_backend;
	private GAME_STATUS m_gameStatus;
	
	private BlackjackWindow[] m_windows;
	private Stack<BlackjackWindow> m_windowsStack;
	
	private Thread m_sound = null;

	public BlackjackApp() {
		
		// backend
		m_backend = new GameManager();
		
		// frontend
		final int MENU = APP_WINDOWS.MENU.ordinal();
		final int TABLE = APP_WINDOWS.TABLE.ordinal();
		final int INFO = APP_WINDOWS.INFO.ordinal();
		final int STATS = APP_WINDOWS.STATISTICS.ordinal();
		final int END_GAME = APP_WINDOWS.END_GAME.ordinal();

		m_windows = new BlackjackWindow[]{
				new BlackjackMenu(APPS_TITLES[MENU], APPS_SIZES[MENU][0], APPS_SIZES[MENU][1], APPS_BACKGROUDS[MENU], this),
				new BlackjackTable(APPS_TITLES[TABLE], APPS_SIZES[TABLE][0], APPS_SIZES[TABLE][1], APPS_BACKGROUDS[TABLE], this),
				new BlackjackInfo(APPS_TITLES[INFO], APPS_SIZES[INFO][0], APPS_SIZES[INFO][1], APPS_BACKGROUDS[INFO], this),
				new BlackjackStatistics(APPS_TITLES[STATS], APPS_SIZES[STATS][0], APPS_SIZES[STATS][1], APPS_BACKGROUDS[STATS], this),
				new BlackjackEndGame(APPS_TITLES[END_GAME], APPS_SIZES[END_GAME][0], APPS_SIZES[END_GAME][1], APPS_BACKGROUDS[END_GAME], this),
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
	
	public enum APP_SOUNDS {
		MENU("sounds/menu.mp3"),
		SHUFFLE("sounds/shuffling.mp3"),
		CARD("sounds/card.mp3"),
		WIN("sounds/win.mp3"),
		LOSE("sounds/lose.mp3"),
		TIE("sounds/tie.mp3");

		private String path;

		APP_SOUNDS(String path) {
			this.path = path;
		}
		
		public String getPath() {
			return path;
		}
	}

	public void playAudio(APP_SOUNDS sound) {

		clearAudio();
		
		m_sound = new Thread() {
			public void run() {
		        try{
		
		            FileInputStream fs = new FileInputStream(sound.getPath());
		            Player playMP3 = new Player(fs);
		
	            	playMP3.play();
			    }  
			    catch(Exception e){
			        System.out.println(e);
			    }
			}
		};
		
		m_sound.start();
	}
	
	public void clearAudio() {
		if (m_sound != null) {
			m_sound.stop();
			m_sound = null;
		}

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
			"pics/Menu.png",
			"pics/Table1.png",
			"pics/INFO.png",
			"pics/STATISTICS.png",
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
