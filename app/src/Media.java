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
		return "name: " + this.name + ", " +
			   "lenght: " + this.length;	
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Media))
			return false;

		Media temp = (Media)obj;
		return this.name.equals(temp.name) && this.length == temp.length;
	}

}
