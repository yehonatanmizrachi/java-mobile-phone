package src;

public class MusicMedia extends Media{

	public MusicMedia(String name, Float lenght) {
		super(name, lenght);
	}

	@Override
	public String play() {
		return "The song \"" + this.name + "\" " + 
				"is now playing for " + this.length + " minutes... ♪♪♪" +
				super.getRandomEmoji();
	}

	@Override
	public String toString() {
		return super.toString() + "\nMedia type: Music";
	}

}
