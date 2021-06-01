package blackjack.backend;

import java.io.IOException;

import org.json.JSONException;

public class DemoGameApp {

	public static void main(String[] args) throws IOException, JSONException {	
		GameManager g = new GameManager();
		System.out.println(g.buildJson());
		g.startGame();
		System.out.println(g.buildJson());
		

		
	}
}
