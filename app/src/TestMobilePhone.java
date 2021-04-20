package src;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TestMobilePhone {

	public static void main(String[] args) throws IOException {
		App[] application = new App[4];
		application[0] = new PhoneBook();
		ImageIcon pic = new ImageIcon("appPic.png");
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(390, 110, java.awt.Image.SCALE_SMOOTH);
		pic = new ImageIcon(modifiedpic);
 		String[] buttons = { "CONTACTS", "SMS", "DIARY", "MEDIA"};
 		int returnValue = JOptionPane.showOptionDialog(null, null,"PHONE",
 		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, pic, buttons,null);
 		//System.out.println(returnValue);
 		if(returnValue == 0) {
 			application[0].run();
 		}

	}

}
