package blackjack;

import java.io.IOException;

public class DemoGameApp {
	public static void main(String[] args) throws IOException {	
		User u = new User(1);
		System.out.println(u.getMoney());
		System.out.println(u.getWins());
	}
}
