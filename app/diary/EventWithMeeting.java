package diary;

import phoneBook.Contact;

public class EventWithMeeting extends DiaryEvent {
	private Contact with_contact;
	
	// constructor
	public EventWithMeeting(String meeting_date, String meeting_time, int event_duration, Contact with_contact)
	{
		super(meeting_date, meeting_time, event_duration);
		this.with_contact = with_contact;
	}
	
	// get the name of the contact
	@Override
	public String getMissingDetail()
	{
		return with_contact.getName();
	}
	
	@Override
	public String toString()
	{
		String out = super.toString() + "with contact: " + with_contact.getName() + '\n' +
										"phone: " + with_contact.getPhoneNumber() + '\n';
		return out;
	}
}
