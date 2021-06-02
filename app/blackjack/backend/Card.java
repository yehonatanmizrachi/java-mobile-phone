package blackjack.backend;
import org.json.JSONException;
import org.json.JSONObject;

import blackjack.api.CARD_SHAPE;

public class Card {
	private int num;
	private CARD_SHAPE shape;
	private String pic;
	public Card(int num,int shape,String pic) {
		this.num = num;
		this.shape = CARD_SHAPE.values()[shape];
		this.pic = pic;
	}
	
	public JSONObject getCardInfo() throws JSONException{
		
		JSONObject obj = new JSONObject();
		obj.put("number", this.num);
		obj.put("shape", this.shape);
		obj.put("pic",this.pic);
		return obj;
	}
	
	// get the value of the card. 
	// AS = 1;
	public int Number() {
		
		return num;
	}

}
