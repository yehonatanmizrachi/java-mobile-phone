package src;

import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TestMobilePhone {
	
	enum APP{
		CONTACTS,SMS,DIARY,MEDIA
	}

	public static void main(String[] args) throws IOException {
		
		// Create application array
		App[] application = new App[4];
		int appChosen = 0;
		application[0] = new PhoneBook();
		application[1] = new SMS();
		// application[2] = new DIARY();
		// application[3] = new MEDIA();
		
		// Initialization of the main GUI
		ImageIcon pic = new ImageIcon("appPic.png");
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(390, 110, java.awt.Image.SCALE_SMOOTH);
		pic = new ImageIcon(modifiedpic);
 		String[] buttons = { "CONTACTS", "SMS", "DIARY", "MEDIA"};
 		
 		// Loop of the applications
 		while(appChosen != -1) {
 			
 			appChosen = JOptionPane.showOptionDialog(null, null,"PHONE",
 			 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, pic, buttons,null);
 			
 			if(appChosen == APP.CONTACTS.ordinal()) {
 				App[] apps = new App[2];
 				apps[0] = application[1];
 				apps[1] = application[2];
 				application[0].run(apps);
 			}
 			
 			if(appChosen == APP.SMS.ordinal()) {
 				application[1].run(null);
 			}
 			
 			if(appChosen == APP.DIARY.ordinal()) {
 				//application[2].run(null);
 			}
 			
 			if(appChosen == APP.MEDIA.ordinal()) {
 				//application[3].run(null);
 			}
 			
 		}

	}

}
