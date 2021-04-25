package src;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class DiaryApp extends ContactsApp {
	private Map<Date,DiaryEvent> events = new HashMap<Date,DiaryEvent>();
	
	@Override
	public void run() throws IOException {
		
	}

	@Override
	public void contactRemoved(Contact contact) {
	
	}
	
	public void addEvent(DiaryEvent ev)
	{
	}
}
