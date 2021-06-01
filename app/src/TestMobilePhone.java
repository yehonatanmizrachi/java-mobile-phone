package src;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import diary.DiaryApp;
import media.MediaApp;
import phoneBook.PhoneBookApp;
import sms.SmsApp;

public class TestMobilePhone {
	
	public enum APPS{
		PHONE_BOOK,
		SMS,
		DIARY,
		MEDIA
	}
/*
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
		ImageIcon mainPicture = ToolsFuncs.getMainPicture("Pic/mainPic.png",450,110);
 		String[] buttons = { "CONTACTS", "SMS", "DIARY", "MEDIA","Print All"};
 		int PrintAll = 4;
 		int appChosen = 0;
 		while(appChosen != -1) {
 			// display the phone main screen
 			appChosen = JOptionPane.showOptionDialog(null, null,"PHONE",
 			 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, mainPicture, buttons,null);
 			// run
 			if (appChosen != -1) {
 				if(appChosen == PrintAll) {
 					String allContent = "";
 					for (App app : application) {
 						allContent = allContent + app.getAppContent() + "\n";
 					}
 					ToolsFuncs.PrintScroll(allContent);
 					continue;
 				}
 				application[appChosen].run();
 			}
 		}
 		mainPicture = ToolsFuncs.getMainPicture("Pic/BYE.png",300,80);
 		JOptionPane.showMessageDialog(null, null, null, JOptionPane.INFORMATION_MESSAGE, mainPicture);
 		
	}
	*/
}
	
	