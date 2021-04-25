package src;

import java.util.Date;
import java.util.Calendar;

public class DiaryEvent {
	protected Date event_date;
	protected int event_duration;
	
	public DiaryEvent(String meeting_date, String meeting_time, int event_duration)
	{
		Calendar c = Calendar.getInstance();
		String[] year_month_day = meeting_date.split("-");
		String[] hour_minute_second = meeting_time.split(":");
		c.set(Integer.parseInt(year_month_day[0]), Integer.parseInt(year_month_day[1]) - 1, Integer.parseInt(year_month_day[2]), Integer.parseInt(hour_minute_second[0]), Integer.parseInt(hour_minute_second[1]), Integer.parseInt(hour_minute_second[2]));
		Date d = c.getTime();
		this.event_date = d;
		this.event_duration = event_duration;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + event_date.hashCode();
		return result;
	}
	
	@Override
	public String toString()
	{
		String out = "Date And Time: " + event_date.toString() + '\n' +
					 "Duration: " + event_duration + '\n';
		return out;
	}
}
