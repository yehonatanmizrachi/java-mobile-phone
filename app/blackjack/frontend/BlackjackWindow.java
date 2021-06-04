package blackjack.frontend;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blackjack.BlackjackApp;
import src.App;

public abstract class BlackjackWindow {
	
	protected JFrame m_frame;
	protected int m_width, m_height;
	protected JLabel m_background;
	protected String m_titel;
	protected BlackjackApp m_app;

	public BlackjackWindow(String title, int width, int height, String backgroundImage, BlackjackApp app) {
		
		m_titel = title;

		m_width = width;
		m_height = height;

		// frame
		m_frame = new JFrame(title);
        m_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
            	m_app.clearAudio();
            	m_frame.setVisible(false);             
                App.phone.returnToPhone();
            }
        });
		
		m_frame.setSize(m_width, m_height);
        m_frame.setLocationRelativeTo(null);
        m_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        m_frame.setResizable(false);

		// background
		if (backgroundImage != null) {
			try {
//				Image image = getScaledImage(ImageIO.read(new File(backgroundImage)), m_width, m_height);
				m_background = new JLabel(new ImageIcon(ImageIO.read(new File(backgroundImage))));
				m_background.setBounds(0, 0, m_width, m_height);
				m_frame.add(m_background);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		m_app = app;
	}

	public abstract void start() throws IOException;

	protected JButton addButton(int x, int y, int width, int height, ActionListener action) {

		JButton button = new JButton();
		button.addActionListener(action);
		button.setBounds(x, y, width, height);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		m_background.add(button);

		return button;
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
