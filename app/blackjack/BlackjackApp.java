package blackjack;


import java.io.IOException;

import blackjack.frontend.BlackjackGUI;
import src.App;


public class BlackjackApp implements App{

	private BlackjackGUI game = new BlackjackGUI();
	
	public void run() throws IOException {
		game.startGame();
	}

	@Override
	public String getAppContent() {
		return "BlackJack app is empty\n";
	}

}
