package blackjack.frontend;

import java.io.IOException;

import javax.swing.JFrame;

import blackjack.BlackjackApp;

public abstract class BlackjackWindow {
	
	protected JFrame m_frame;
	protected BlackjackApp m_app;

	public BlackjackWindow(String title, BlackjackApp app) {
		m_frame = new JFrame(title);
		m_app = app;
	}

	public abstract void start() throws IOException;

}
