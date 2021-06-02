package blackjack.frontend;

import java.io.IOException;

import javax.swing.JFrame;

import blackjack.BlackjackApp;

public abstract class BlackjackWindow {
	
	protected JFrame m_frame;
	protected BlackjackApp m_app;
	protected int m_width, m_height;
	protected String m_backgroundImage;

	public BlackjackWindow(String title, int width, int height, BlackjackApp app) {
		m_frame = new JFrame(title);
		m_width = width;
		m_height = height;
		m_app = app;
	}

	public abstract void start() throws IOException;

}
