package media;

public class VideoMedia extends Media{

	public VideoMedia(String name, Float lenght) {
		super(name, lenght);
	}
	
	@Override
	public String play() {
		return "The video \"" + this.name + "\" " + 
				"is now playing for " + this.length + " minutes...";
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Type: video";
	}

}
