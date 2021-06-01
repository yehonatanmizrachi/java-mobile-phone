package blackjack.backend;
import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.CARD_COLOR;
import blackjack.api.CARD_SHAPE;

public class Card {
	private int num;
	private CARD_COLOR color;
	private CARD_SHAPE shape;
	
	public Card(int num,int color,int shape) {
		this.num = num;
		
		this.color = CARD_COLOR.values()[color];
		this.shape = CARD_SHAPE.values()[shape];
	}
	
	public JSONObject getCardInfo() throws JSONException{
		
		JSONObject obj = new JSONObject();
		obj.put("number", this.num);
		obj.put("color", this.color);
		obj.put("shape", this.shape);
		return obj;
	}
	
	// get the value of the card. 
	// AS = -1;
	public int getNumber() {
		
		return num;
	}

}
