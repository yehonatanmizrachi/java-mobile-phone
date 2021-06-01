package blackjack.frontend;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blackjack.BlackjackApp;
import blackjack.backend.GameManager;


public class BlackjackStatistics extends BlackjackWindow{

	public BlackjackStatistics(String title, BlackjackApp app) {
		super(title, app);
	}

	public void start() throws IOException {
		
		// frame
		String IMAGE_PATH = "Pic/STATISTICS.png";
		int WIDTH = 583, HEIGHT = 525;
	
		JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
        m_frame.add(background);
        
        m_frame.setSize(WIDTH, HEIGHT);
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        m_frame.setResizable(false);
        m_frame.setVisible(true);
	}
	
	private enum INFO_BUTTONS {
		RETURN,
		HOME
	}

}
