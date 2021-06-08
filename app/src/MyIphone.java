package src;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private JLabel clock;

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
		String IMAGE_PATH = "pics/IPHONE2.png";
		int WIDTH = 310, HEIGHT = 640;  
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        Image a = getScaledImage(ImageIO.read(new File(IMAGE_PATH)), WIDTH - 20, HEIGHT - 40);
        JLabel label = new JLabel(new ImageIcon(a));
        
        // buttons
        JButton[] buttons = new JButton[APPS.values().length];
        
        // buttons layout
        int BUTTON_WIDTH = 53, BUTTON_HEIGHT = 53, BUTTON_PADDING = 23; 
        int INITIAL_X = 43, INITIAL_Y = 360, COLS=3;
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
	        	buttons[index].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        	buttons[index].setToolTipText("Enti ten 100");
        		label.add(buttons[app.ordinal()]);
        	}
        	else {
        		google = true;
        	}
        	
        }
        
        //google button
        JButton google_button = new JButton();
        google_button.addActionListener(getAppButtonEventListener(APPS.GOOGLE));
        google_button.setBounds(27,275,235,25);
        google_button.setOpaque(false);
        google_button.setContentAreaFilled(false);
        google_button.setBorderPainted(false);
        google_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        google_button.setToolTipText("Click on me!");
        
        int FONT_SIZE = 30;
        String FONT_NAME = "Sans Serif";

        //time label
        clock = new JLabel();
        clock.setForeground(Color.white);
        clock.setBounds(30,237,200,50);
        clock.setFont(new Font(FONT_NAME, Font.ITALIC | Font.BOLD, FONT_SIZE));
        
        
        // clock thread
        Thread clockThread = new Thread() {
        	@Override
			public void run() {
		        try{
		        	while(true) {
		        		String timeStamp = new SimpleDateFormat("yyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
		        		timeStamp = timeStamp.split("_",2)[1];
			            clock.setText(timeStamp);
			            
			            int timeInterval = 1000;
			            Thread.sleep(timeInterval);
		        	}
			    }  
			    catch(Exception e){
			        System.out.println(e);
			    }
			}
		};

		clockThread.start();

        label.add(google_button);
        panel.add(label);
        frame.add(clock);

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
	
	protected Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
	
	