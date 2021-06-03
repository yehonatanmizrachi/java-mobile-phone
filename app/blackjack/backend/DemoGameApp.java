package blackjack.backend;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.COMMAND;

public class DemoGameApp {

	
	public static void main(String[] args) throws IOException, JSONException {	
		JSONObject obj = new JSONObject();
		GameManager g = new GameManager();
	//	System.out.println(g.buildJson());
		
		// INITIAL GAME
	//	obj.put("command", COMMAND.START_GAME);
	//	g.sendCommand(obj);
	//	System.out.println(g.buildJson());
		// PLAYER TURN
	//	obj.put("command", COMMAND.HIT);
	//	g.sendCommand(obj);
	//	System.out.println(g.buildJson());
		
		System.out.println(g.getStatistics());
		// DEALER TURN
		/*
		obj.put("command", COMMAND.STAND);
		g.sendCommand(obj);
		System.out.println(g.buildJson());
		obj.put("command", COMMAND.STAND);
		g.sendCommand(obj);
		System.out.println(g.buildJson());
		obj.put("command", COMMAND.STAND);
		g.sendCommand(obj);
		System.out.println(g.buildJson());
		obj.put("command", COMMAND.STAND);
		g.sendCommand(obj);
		System.out.println(g.buildJson());
		obj.put("command", COMMAND.STAND);
		g.sendCommand(obj);
		System.out.println(g.buildJson());
		// EXIT GAME
		obj.put("command", COMMAND.EXIT);
		g.sendCommand(obj);
		System.out.println(g.buildJson());
		*/
		
	}
}
