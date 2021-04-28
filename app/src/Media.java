package src;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Media {

	protected String name;
	protected float length;


	public Media(String name, Float length) {
		this.name = name;
		this.length = length;
	}

	public abstract String play();

	@Override
	public String toString() {
		return "Media name: " + this.name +
			   "\nMedia lenght: " + this.length;	
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Media))
			return false;

		Media temp = (Media)obj;
		return this.name.equals(temp.name) && this.length == temp.length;
	}

	protected String getRandomEmoji() {

		String[] emojiArr = {
				"🤯",
				"🤠",
				"😎",
				"😋",
				"👾",
				"🙃",
				"😇",
				"🥳",
				"🤓",
				"😈",
				"🐱‍👤"
		};
		
		int randomNum = ThreadLocalRandom.current().nextInt(0, emojiArr.length);		
		return emojiArr[randomNum];

	}
}
