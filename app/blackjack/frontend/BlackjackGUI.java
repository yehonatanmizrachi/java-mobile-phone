package blackjack.frontend;

import java.io.IOException;

import blackjack.backend.GameManager;

public class BlackjackGUI {

	private GameManager backend = new GameManager();
	private BlackjackMenu menu = new BlackjackMenu();

	public void startGame() throws IOException {
		menu.startMenu();
	}

}
