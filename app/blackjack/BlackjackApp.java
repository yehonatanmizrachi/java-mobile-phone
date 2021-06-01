package blackjack;


import java.io.IOException;

import blackjack.frontend.BlackjackMenu;
import src.App;


public class BlackjackApp implements App{

	private BlackjackMenu game = new BlackjackMenu();
	
	public void run() throws IOException {
		game.run();
	}

	@Override
	public String getAppContent() {
		return "BlackJack app is empty\n";
	}

}
