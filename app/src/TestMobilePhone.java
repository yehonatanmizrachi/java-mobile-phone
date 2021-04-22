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
		MEDIA;
	}

	public static void main(String[] args) throws IOException {

		// initialize the applications
		App[] application = new App[APPS.values().length];

		application[APPS.PHONE_BOOK.ordinal()] = new PhoneBookApp();
		// application[APPS.SMS.ordinal()] = new SmsApp();
		// application[APPS.DIARY.ordinal()] = new DiaryApp();
		application[APPS.MEDIA.ordinal()] = new MediaApp();

		// prepare the main screen elements
		ImageIcon mainPicture = TestMobilePhone.getMainPicture();
 		String[] buttons = { "CONTACTS", "SMS", "DIARY", "MEDIA"};

 		int appChosen = 0;
 		while(appChosen != -1) {

 			// display the phone main screen
 			appChosen = JOptionPane.showOptionDialog(null, null,"PHONE",
 			 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, mainPicture, buttons,null);

			application[appChosen].run();

 		}

	}

	private static ImageIcon getMainPicture() {
		ImageIcon pic = new ImageIcon("appPic.png");
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(390, 110, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(modifiedpic);
	}

}
