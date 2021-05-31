package src;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
import org.json.JSONObject;

public class MyIphone {

	private App[] applications = new App[APPS.values().length];
	private JFrame frame = new JFrame("My Iphone");
	
	public static void main(String[] args) throws IOException {
		MyIphone phone = new MyIphone();
		phone.runPhone();
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
	
	private void runPhone() throws IOException {

		// initialize the applications
		
		ContactsApp smsApp = new SmsApp();
		ContactsApp diaryApp = new DiaryApp();
		ContactsApp[] contactsApps = { smsApp, diaryApp };

		applications[APPS.PHONE_BOOK.ordinal()] = new PhoneBookApp(contactsApps);
		applications[APPS.SMS.ordinal()] = smsApp;
		applications[APPS.DIARY.ordinal()] = diaryApp;		
		applications[APPS.MEDIA.ordinal()] = new MediaApp();
		applications[APPS.BLACKJACK.ordinal()] = new BlackjackApp();

		applications[APPS.BLACKJACK.ordinal()].run();
		
		// run the screen GUI
		startPhoneMainScreen();
	}

	private void runApp(APPS app) throws IOException {
		
		if (app.equals(APPS.GET_CONTENT)) {
			printAppsContent();
			return;
		}
		
		applications[app.ordinal()].run();
	}

	private void startPhoneMainScreen() throws IOException {

		// frame
		String IMAGE_PATH = "Pic/IPHONE.png";
		int WIDTH = 340, HEIGHT = 740;  
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
        
        // buttons
        JButton[] buttons = new JButton[APPS.values().length];
        
        // buttons layout
        int BUTTON_WIDTH = 80, BUTTON_HEIGHT = 80, BUTTON_PADDING = 15; 
        int INITIAL_X = 35, INITIAL_Y = 400, COLS=3;
        int[][] BUTTONS_LOCATIONS = new int[APPS.values().length][2];
    	for (int i = 0; i < APPS.values().length; i++) {
    		// set x
    		BUTTONS_LOCATIONS[i][0] = INITIAL_X + (BUTTON_WIDTH + BUTTON_PADDING) * (i % COLS);
    		// set y
			BUTTONS_LOCATIONS[i][1] = INITIAL_Y + (BUTTON_WIDTH + BUTTON_PADDING)* (i / COLS);
    	}
    	
        for (APPS app : APPS.values()) {
        	int index = app.ordinal(); 
        	buttons[index] = new JButton();
        	buttons[index].addActionListener(getAppButtonEventListener(app));
        	buttons[index].setBounds(BUTTONS_LOCATIONS[index][0], BUTTONS_LOCATIONS[index][1], BUTTON_WIDTH, BUTTON_HEIGHT);
        	buttons[index].setOpaque(false);
        	buttons[index].setContentAreaFilled(false);
        	buttons[index].setBorderPainted(false);
        		        			
        	label.add(buttons[app.ordinal()]);
        }
        
        panel.add(label);
          
        frame.add(panel); 
        frame.setSize(WIDTH, HEIGHT);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
	}
	
	private void printAppsContent() {
		String allContent = "";
		for (App app : applications) {
			allContent = allContent + app.getAppContent() + "\n";
		}
		ToolsFuncs.PrintScroll(allContent);
	}

	private ActionListener getAppButtonEventListener(APPS app) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 try {
	       			 frame.setVisible(false);
	       			 runApp(app);
	       			 frame.setVisible(true);
	       		 } catch (Exception e) {
	       			 System.out.print("Error:" + e);
					}
	       	 }
       }; 
	}
	private enum APPS{
		PHONE_BOOK,
		SMS,
		DIARY,
		MEDIA,
		BLACKJACK,
		GET_CONTENT
	}
}
	
	