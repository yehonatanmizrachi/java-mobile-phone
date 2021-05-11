package src;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public abstract class ToolsFuncs {
	// takes a string message and prints it in a scrolled window bar.
	public static void PrintScroll(String message) {
		JTextArea textArea = new JTextArea(message, 15,  30);
	    JScrollPane sp = new JScrollPane(textArea);
	    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    JOptionPane.showMessageDialog(null, sp);
	}
	
	// build image icon
	public static ImageIcon getMainPicture(String picture,int x,int y) {
		ImageIcon pic = new ImageIcon(picture);
		Image pic2 = pic.getImage();
		Image modifiedpic = pic2.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(modifiedpic);
	}
	
	/* set window of multiple options and wait for user's input and check if the
	   input is invalid or cancel or valid.
	*/
	public static int SetStartingMenu(String message, int invalid_num, int cancel_num,String app)
	{
		String toPic = "Pic/"+app +".png";
		ImageIcon pic = ToolsFuncs.getMainPicture(toPic,80,80);
		String s = (String) JOptionPane.showInputDialog(null,message,app,JOptionPane.INFORMATION_MESSAGE, pic, null,null );
		int input = 0;
		
		if(s != null) {
			try {
				input = Integer.parseInt(s);
			}
			// invalid input
			catch(Exception e){
				input = invalid_num;
			}
		}
		// cancel
		else {input = cancel_num;}
		return input;
	}
	
	// method for print error message
	public static void printError(String msg) {
		JDialog dialog = new JOptionPane(msg,
				JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION).createDialog("ERROR"); 
		dialog.setVisible(true);
	}
	
	// method that swap 2 contacts in the ArrayList
	public static void swapContacts(ArrayList<Contact> contacts,int i,int j) {
		Contact temp = new Contact(contacts.get(j)); // create temp contact object
		ContactsApp.contacts.set(j,contacts.get(i)); // set the contacts ArrayList in the j place
		ContactsApp.contacts.set(i,temp); // set the contacts ArrayList in the i place
	}
	
	// create a window with multiple inputs on one screen
	public static String[] multipleInputsWindow(boolean twoOrThreeInputs, String[] labels, String HeaderText)
	{
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		int option;
		
		if (twoOrThreeInputs) // if we need three inputs
		{
			Object[] message = {
			    labels[0], field1,
			    labels[1], field2,
			    labels[2], field3,
			};
			option = JOptionPane.showConfirmDialog(null, message, HeaderText, JOptionPane.OK_CANCEL_OPTION);
		}
		else // if we need two inputs
		{
			Object[] message = {
				    labels[0], field1,
				    labels[1], field2,
			};
			field3.setText(null);
			option = JOptionPane.showConfirmDialog(null, message, HeaderText, JOptionPane.OK_CANCEL_OPTION);
		}
		
		if (option == JOptionPane.OK_OPTION) // if the inputs are valid
		{
			String[] temp = {field1.getText(), field2.getText(), field3.getText()};
			return temp;
		}
		else // if there is an error
			return null;
	}
}


