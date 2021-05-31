package blackjack;

import src.ToolsFuncs;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;  
import src.App;


public class BlackjackApp implements App{

	private JFrame frame = new JFrame("BlackJack");
	private static Object lock = new Object();

	public void run() throws IOException {
		startMenu();
	}

	@Override
	public String getAppContent() {
		return "BlackJack app is empty\n";
	}

	
	private void startMenu() throws IOException {
		// frame
		String IMAGE_PATH = "Pic/IPHONE.png";
		int WIDTH = 340, HEIGHT = 740;
		JPanel panel = new JPanel();
		
        JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(IMAGE_PATH))));
        label.setBounds(0, 0, 1000, 1000);
        JLabel label_test = new JLabel(new ImageIcon(ImageIO.read(new File("Pic/BYE.png"))));
        label_test.setBounds(0, 0, 1000, 1000);
        
        frame.add(label);
        frame.add(label_test);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
        	
        frame.add(panel);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                frame.setVisible(false);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                App.phone.returnToPhone();
            }
        });
	}



}
