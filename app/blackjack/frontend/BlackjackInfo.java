package blackjack.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import blackjack.BlackjackApp;
import blackjack.BlackjackApp.APP_WINDOWS;

public class BlackjackInfo extends BlackjackWindow{

	public BlackjackInfo(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		super(title, width, height, backgroundImage, app);
		init();
	}
	
	public void start() throws IOException {	
        m_frame.setVisible(true);
	}


	private void init() {

		int BUTTON_WIDTH = 40, BUTTON_HEIGHT = 40, BUTTON_X = 33, BUTTON_Y = 425, PADD = 18;

        super.addButton(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(BUTTONS_ACTIONS.RETURN));
        super.addButton(BUTTON_X + BUTTON_WIDTH + PADD, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, getButtonEventListener(BUTTONS_ACTIONS.HOME));
	}

	private enum BUTTONS_ACTIONS {
		RETURN,
		HOME
	}

	private ActionListener getButtonEventListener(BUTTONS_ACTIONS action) {
		return new ActionListener(){
	       	 public void actionPerformed(ActionEvent evt){
	       		 m_frame.setVisible(false);
	       		 if (action == BUTTONS_ACTIONS.RETURN) {
	       			m_app.goToPreviousWindow();
	       		 }
	       		 else if (action == BUTTONS_ACTIONS.HOME) {
	       			m_app.cleanWindowStack();
	       			m_app.startWindow(APP_WINDOWS.MENU);
	       		 }
	       	 }
       }; 
	}
	
}
