package src;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class MediaApp implements App{

	private ArrayList<Media> mediaList = new ArrayList<Media>();

	public void run() {
		
		String errorMessage = "Invalid input. Please try again.";
		String message;
		int input = 0;

		while (input != MENU.EXIT.ordinal()) {
			ImageIcon musicPic = ToolsFuncs.getMainPicture("Pic/MUSIC.png",80,80);
			String result = (String) JOptionPane.showInputDialog(null,this.getMenu(),"MEDIA",JOptionPane.INFORMATION_MESSAGE, musicPic, null,null);
			input = this.parseIntResult(result) - 1;

			if (input == MENU.ADD.ordinal()) {

				// get media type
				message = "         Please choose the media type:";
				String[] buttons = { "VIDEO", "MUSIC" };
				int chosenMedia = JOptionPane.showOptionDialog(null, message,"ADD MEDIA",
	 			 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);
				String type = chosenMedia == MEDIA_TYPE.VIDEO.ordinal() ? "video" : "song";

				// get media name
				message = "Please enter the " + type + " name";
				result = JOptionPane.showInputDialog(message);
				if (result == null) {
					continue;
				}
				if (result.equals("")) {
					ToolsFuncs.printError(errorMessage);
					continue;
				}
				String name = result;

				// get media length
				message = "Please Enter the " + type + " length";
				result = JOptionPane.showInputDialog(message);
				if (result == null) {
					continue;
				}
				float length = this.parseFloatResult(result);
				if(length <= 0) {
					ToolsFuncs.printError(errorMessage);
					continue;
				}

				// add
				this.addMedia(name, length, chosenMedia);
				message = "The " + type + " \"" + name + "\" has been added successfully";
				JOptionPane.showMessageDialog(null, message);
			}
			else if (input == MENU.PLAY_BY_NAME.ordinal()) {

				message = "Please enter the media name:";
				result = JOptionPane.showInputDialog(message);
				if (result == null) {
					continue;
				}
				
				String playingMedia = this.playMediaByName(result);
				if (playingMedia == null) {
					message = "The file \"" + result + "\" doesn't exist!";
				} 
				else {
					message = playingMedia;
				}

				JOptionPane.showMessageDialog(null, message);

			}
			else if (input == MENU.PLAY_ALL.ordinal()) {

				String playingMedia = this.playAll();
				if (playingMedia == null) {
					message = "The media list is empty. There is nothing to play :(";
				}
				else {
					message = playingMedia;
				}

				ToolsFuncs.PrintScroll(message);
			}
			else if (input == MENU.EXIT.ordinal()) {
				return;
			}
			else {
				ToolsFuncs.printError(errorMessage);
			}
		}
	}

	@Override
	public String getAppContent() {
		String result = "Media app content:\n";
		
		if (this.mediaList.size() == 0) {
			return result + "The media list is empty :(\n";
		}
		
		int i=0;
		for (Media media: this.mediaList) {
			result += (i+1) + ": " + media.toString() + "\n";
			i++;
		}

		return result;
	}

	private void addMedia(String name, float length, int type) {
		if (type == MEDIA_TYPE.VIDEO.ordinal()) {
			Media temp = new MusicMedia(name, length);
			mediaList.add(temp);
		}
		else {
			Media temp = new VideoMedia(name, length);
			mediaList.add(temp);
		}
	}

	private String playMediaByName(String name) {
		for(Media temp : this.mediaList) {
			if (temp.name.equals(name)) {
				return temp.play();
			}
		}
		
		return null;
	}


	private String playAll() {
		if (this.mediaList.size() == 0) {
			return null;
		}
		
		String output = "";
		for(Media temp : this.mediaList) {
			output += temp.play() + "\n";
		}

		return output;
	}


	private int parseIntResult(String result) {
		
		if (result == null) {
			return MENU.EXIT.ordinal() + 1;
		}

		int parsedResult;
		try {
			parsedResult = Integer.parseInt(result);		
			return parsedResult;
		}
		catch (Exception e) {
			return MENU.ERROR.ordinal() + 1;
		}
	}

	private float parseFloatResult(String result) {
		float parsedResult;
		try {
			parsedResult = Float.parseFloat(result);		
			return parsedResult;
		}
		catch (Exception e) {
			return -1;
		}
	}

	private String getMenu() {
		return "Welcome to the media app!\n\nMenu:\n" + 
			   "1: Add media\n" +	
			   "2: Play media by name\n" +
			   "3: Play all the media list(video and music)\n" + 
			   "4: Exit\n\n" + 
			   "Enjoy :)";
	}


	private enum MENU{
		ADD,
		PLAY_BY_NAME,
		PLAY_ALL,
		EXIT,
		ERROR
	}

	private enum MEDIA_TYPE{
		VIDEO,
		MUSIC;
	}

}
