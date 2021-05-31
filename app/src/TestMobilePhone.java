package src;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import blackjack.BlackjackApp;
import diary.DiaryApp;
import media.MediaApp;
import phoneBook.PhoneBookApp;
import sms.SmsApp;

public class TestMobilePhone {
	
	public enum APPS{
		PHONE_BOOK,
		SMS,
		DIARY,
		MEDIA,
		BLACKJACK
	}

	public static void main(String[] args) throws IOException {	 
		runPhone();
		/*
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
 		*/
	}
	
	private static void runPhone() throws IOException {

		// initialize the applications
		App[] application = new App[APPS.values().length];
		ContactsApp smsApp = new SmsApp();
		ContactsApp diaryApp = new DiaryApp();
		ContactsApp[] contactsApps = { smsApp, diaryApp };

		application[APPS.PHONE_BOOK.ordinal()] = new PhoneBookApp(contactsApps);
		application[APPS.SMS.ordinal()] = smsApp;
		application[APPS.DIARY.ordinal()] = diaryApp;		
		application[APPS.MEDIA.ordinal()] = new MediaApp();
		application[APPS.BLACKJACK.ordinal()] = new BlackjackApp();

		application[APPS.BLACKJACK.ordinal()].run();
		
		// run the screen GUI
		startPhoneMainScreen();
	}


	private static void startPhoneMainScreen() {
		JFrame frame = new JFrame("JFrame Example");  
        JPanel panel = new JPanel();  
        panel.setLayout(new FlowLayout());  
        JLabel label = new JLabel("JFrame By Example");  
        JButton button = new JButton();  
        button.setText("Button");  
        panel.add(label);  
        panel.add(button);  
        frame.add(panel);  
        frame.setSize(200, 300);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
	}
}
	
	