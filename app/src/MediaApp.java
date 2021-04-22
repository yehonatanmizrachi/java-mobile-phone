package src;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MediaApp implements App{

	private ArrayList<Media> mediaList;

	public void run() {
		JOptionPane.showInputDialog("Hello");
	}

	public void addMedia(String name, Float length) {
		Media temp = new MusicMedia(name, length);
		mediaList.add(temp);
	}
	
	public void playMediaByName(String name) {
		for(Media temp : this.mediaList) {
			if (temp.name.equals(name)) {
				temp.play();
				return;
			}
		}
	}

	public String menu() {
		return "";
	}
}
