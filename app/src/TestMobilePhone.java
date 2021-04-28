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
		ImageIcon mainPicture = TestMobilePhone.getMainPicture("appPic.png",390,110);
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

 		JOptionPane.showMessageDialog(null, "Bye Bye ☺");

	}

	private static ImageIcon getMainPicture(String picture,int x,int y) {
		ImageIcon pic = new ImageIcon(picture);
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(modifiedpic);
	}
	
	public static int SetStartingMenu(String message, int invalid_num, int cancel_num)
	{
		String s = JOptionPane.showInputDialog(message);
		int input = 0;
		
		if(s != null) {
			try {
				input = Integer.parseInt(s);
			}
			// invalid input
			catch(Exception e){
				input = invalid_num;
			}
		}
		// cancel
		else {input = cancel_num;}
		return input;
	}
}
