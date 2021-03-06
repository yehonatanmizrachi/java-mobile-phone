package diary;

public class EventWithoutMeeting extends DiaryEvent {
	private String description;
	
	// constructor
	public EventWithoutMeeting(String meeting_date, String meeting_time, int event_duration, String description)
	{
		super(meeting_date, meeting_time, event_duration);
		this.description = description;
	}
	
	// get the description of the event
	@Override
	public String getMissingDetail()
	{
		return description;
	}
	
	@Override
	public String toString()
	{
		String out = super.toString() + "description: " + this.description + '\n';
		return out;
	}
}
