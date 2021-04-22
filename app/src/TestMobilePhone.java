package src;

import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TestMobilePhone {
	
	public enum APPS{
		PHONE_BOOK,
		SMS,
		DIARY,
		MEDIA
	}

	public static void main(String[] args) throws IOException {

		// initialize the applications
		App[] application = new App[APPS.values().length];
		
		ContactsApp smsApp = new SmsApp();
		ContactsApp diaryApp = new DiaryApp();
		ContactsApp[] contactsApps = { smsApp, diaryApp };

		application[APPS.PHONE_BOOK.ordinal()] = new PhoneBookApp(contactsApps);
		application[APPS.SMS.ordinal()] = smsApp;
		application[APPS.DIARY.ordinal()] = diaryApp;		
		application[APPS.MEDIA.ordinal()] = new MediaApp();

		// prepare the main screen elements
		ImageIcon mainPicture = TestMobilePhone.getMainPicture();
 		String[] buttons = { "CONTACTS", "SMS", "DIARY", "MEDIA"};

 		int appChosen = 0;
 		while(appChosen != -1) {
 			// display the phone main screen
 			appChosen = JOptionPane.showOptionDialog(null, null,"PHONE",
 			 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, mainPicture, buttons,null);
 			// run
 			if (appChosen != -1) {
 				application[appChosen].run();
 			}
 		}

 		JOptionPane.showMessageDialog(null, "Bye Bye ✪ ω ✪");

	}

	private static ImageIcon getMainPicture() {
		ImageIcon pic = new ImageIcon("appPic.png");
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(390, 110, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(modifiedpic);
	}

}
