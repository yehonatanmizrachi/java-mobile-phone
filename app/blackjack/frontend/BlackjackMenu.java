package blackjack.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_SOUNDS;
import blackjack.BlackjackApp.APP_WINDOWS;
import src.App;

public class BlackjackMenu extends BlackjackWindow{

	public BlackjackMenu(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		super(title, width, height, backgroundImage, app);
		init();
	}

	public void start() throws IOException {    	
    	
		m_frame.setVisible(true);
		// play music 🎧
        m_app.playAudio(APP_SOUNDS.MENU);
	}

	private void init() {

        // buttons
        int BUTTONS_COUNT = 3;

        // buttons layout
        int BUTTON_WIDTH = 345, BUTTON_HEIGHT = 60, BUTTON_PADDING = 35; 
        int INITIAL_X = 115, INITIAL_Y = 175	;
        int[][] BUTTONS_LOCATIONS = new int[BUTTONS_COUNT][2];
    	for (int i = 0; i < BUTTONS_COUNT; i++) {
    		// set x
    		BUTTONS_LOCATIONS[i][0] = INITIAL_X;
    		// set y
			BUTTONS_LOCATIONS[i][1] = INITIAL_Y + (BUTTON_HEIGHT + BUTTON_PADDING)*i;
    	}
        
    	for (int index = 0; index < BUTTONS_COUNT; index++) {
    		super.addButton(BUTTONS_LOCATIONS[index][0], BUTTONS_LOCATIONS[index][1], BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(MENU.values()[index]));
        }
	}

	private enum MENU {
		START,
		STATISTICS,
		EXIT
	}

	private ActionListener getButtonEventListener(MENU action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){	       		
       			m_frame.setVisible(false);
       			m_app.clearAudio();
	       		 if (action == MENU.START) {
					m_app.startWindow(APP_WINDOWS.TABLE);
	       		 }
	       		 else if (action == MENU.STATISTICS) {
	       			m_app.startWindow(APP_WINDOWS.STATISTICS);
	       		 }
	       		 else if (action == MENU.EXIT) {
	       			m_app.closeWindow(APP_WINDOWS.MENU);
	                App.phone.returnToPhone(); 
	       		 }	
	       	 }	       	 
       }; 
	}
	
}
