package src;

import java.io.IOException;

// interface App
public interface App {
	
	// method that run the application. Implemented by the application classes.
	public void run() throws IOException;	
	
	// method that return string with all the application content.
	// Implemented by the application classes.
	public String getAppContent();

}
