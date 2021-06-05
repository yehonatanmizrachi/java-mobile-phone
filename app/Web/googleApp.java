package Web;

import java.net.URI;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import src.App;
import src.ToolsFuncs;

public class googleApp implements App {
	
	private static ArrayList<String> last_search = new ArrayList<String>();
	private int num_search;
	
	public googleApp(){
		this.num_search = 0;
	}
	
	public void run() {
		ImageIcon pic = ToolsFuncs.getMainPicture("./pics/google.png",50,50);	
		
		String s = (String) JOptionPane.showInputDialog(null,"Enter search key:","Google",
				JOptionPane.QUESTION_MESSAGE, pic, null,null);
		
		if(s != null) {
			String []words = s.split("\\s+");
			String search = words[0];
			for(int i = 1; i<words.length;i++) {
				search = search + "%20" + words[i];
			}
			try {
				   URI uri= new URI("http://www.google.com/search?q=" + search);
				   
				   java.awt.Desktop.getDesktop().browse(uri);
				   
				   if(this.num_search < 5) {
					   googleApp.last_search.add(s);
					   this.num_search++;
				   }
				   else {
					   googleApp.last_search = new ArrayList<String>();
					   googleApp.last_search.add(s);
					   this.num_search = 1;
					   
				   }
				 
				  } 
			catch (Exception e) {
				   ToolsFuncs.printError("Illegal search");
			}
		}
	
	}
	
	public String getAppContent() {
		String content = "Last search on google:\n";
		if(this.num_search == 0) {
			content = content + "No search has been performed yet";
		}
		else {
			for(int i = 0; i<this.num_search;i++) {
				content = content + googleApp.last_search.get(i) +"\n";
			}
		}
		return content;
	}

}
