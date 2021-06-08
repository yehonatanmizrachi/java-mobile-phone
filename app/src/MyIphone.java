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
import javax.swing.JPanel;

import Web.googleApp;
import blackjack.BlackjackApp;
import diary.DiaryApp;
import media.MediaApp;
import phoneBook.PhoneBookApp;
import sms.SmsApp;

public class MyIphone {

	private App[] applications = new App[APPS.values().length-1];
	private JFrame frame = new JFrame("My Iphone");
	
	public static void main(String[] args) throws IOException {
		MyIphone phone = App.phone;
		phone.runPhone();
	}
	 
	public void returnToPhone() {
		frame.setVisible(true);
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
		applications[APPS.GOOGLE.ordinal()] = new googleApp();
		
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
		String IMAGE_PATH = "pics/IPHONE.png";
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
    	
    	boolean google = false;
        for (APPS app : APPS.values()) {
        	if(app != APPS.GOOGLE) {
	        	int index = app.ordinal();
	        	int locationIndex = google ? index - 1 : index;
	        	buttons[index] = new JButton();
	        	buttons[index].addActionListener(getAppButtonEventListener(app));
	        	buttons[index].setBounds(BUTTONS_LOCATIONS[locationIndex][0], BUTTONS_LOCATIONS[locationIndex][1], BUTTON_WIDTH, BUTTON_HEIGHT);
	        	buttons[index].setOpaque(false);
	        	buttons[index].setContentAreaFilled(false);
	        	buttons[index].setBorderPainted(false);
      	        			
        		label.add(buttons[app.ordinal()]);
        	}
        	else {
        		google = true;
        	}
        	
        }
        
        //google button
        JButton google_button = new JButton();
        google_button.addActionListener(getAppButtonEventListener(APPS.GOOGLE));
        google_button.setBounds(0,322,340,30);
        google_button.setOpaque(false);
        google_button.setContentAreaFilled(false);
        google_button.setBorderPainted(false);
        
        label.add(google_button);
        panel.add(label);

        frame.add(panel); 
        frame.setSize(WIDTH, HEIGHT);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setResizable(false);
        frame.setVisible(true);
	}
	
	private void printAppsContent() {
		String allContent = "";
		for (App app : applications) {
			allContent = allContent + app.getAppContent() + "\n";
		}
		ToolsFuncs.PrintScroll(allContent);
		App.phone.returnToPhone();
	}

	private ActionListener getAppButtonEventListener(APPS app) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 try {
	       			 frame.setVisible(false);
	       			 runApp(app);
	       		 } catch (Exception e) {
	       			 System.out.print("Error:" + e);
					}
	       	 }
       }; 
	}

	private enum APPS{
		SMS,
		PHONE_BOOK,
		MEDIA,
		BLACKJACK,
		DIARY,
		GOOGLE,
		GET_CONTENT
	}
}
	
	