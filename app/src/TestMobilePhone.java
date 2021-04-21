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
		App[] application = new App[4];
		application[0] = new PhoneBook();
		ImageIcon pic = new ImageIcon("appPic.png");
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(390, 110, java.awt.Image.SCALE_SMOOTH);
		pic = new ImageIcon(modifiedpic);
 		String[] buttons = { "CONTACTS", "SMS", "DIARY", "MEDIA"};
 		int appChosen = JOptionPane.showOptionDialog(null, null,"PHONE",
 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, pic, buttons,null);
 		
 		// 0 == CONTACTS , 1 == SMS, 2 == DIARY, 3 == MEDIA , -1 == EXIT
 		
 		while(appChosen != -1) {
 			
 			if(appChosen == APP.CONTACTS.ordinal()) {
 				application[0].run();
 			}
 			
 			if(appChosen == APP.SMS.ordinal()) {
 				application[1].run();
 			}
 			
 			if(appChosen == APP.DIARY.ordinal()) {
 				application[2].run();
 			}
 			
 			if(appChosen == APP.MEDIA.ordinal()) {
 				application[3].run();
 			}
	 		
 		}

	}

}
