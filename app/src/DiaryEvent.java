package src;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class DiaryEvent implements Comparable<DiaryEvent> {
	protected Date event_date;
	protected int event_duration;
	long time_sec;
	
	public DiaryEvent(String meeting_date, String meeting_time, int event_duration)
	{
		this.event_date = formatDate(meeting_date, meeting_time);
		this.event_duration = event_duration;
		String[] hour_minute_second = meeting_time.split(":");
		this.time_sec = 60 * 60 * Integer.parseInt(hour_minute_second[0]) + 60 * Integer.parseInt(hour_minute_second[1]) + Integer.parseInt(hour_minute_second[2]);
	}
	
	public abstract String getMissingDetail();
	
	public static Date formatDate(String meeting_date, String meeting_time)
	{
		Calendar c = Calendar.getInstance();
		String[] day_month_year = meeting_date.split("-");
		String[] hour_minute_second = meeting_time.split(":");
		c.set(Integer.parseInt(day_month_year[2]), Integer.parseInt(day_month_year[1]) - 1, Integer.parseInt(day_month_year[0]), Integer.parseInt(hour_minute_second[0]), Integer.parseInt(hour_minute_second[1]), Integer.parseInt(hour_minute_second[2]));
		Date d = c.getTime();
		return d;
	}
	
	@Override
	public int compareTo(DiaryEvent d)
	{
		if (event_date.before(d.event_date)) return -1;
		else if (event_date.after(d.event_date)) return 1;
		
		// equal date
		if (this.time_sec < d.time_sec) return -1;
		else if (this.time_sec > d.time_sec) return 1;
		else return 0;
	}
	
	@Override
	public String toString()
	{
		String newstring = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(event_date);
		String out = "Date And Time: " + newstring + '\n' +
					 "Duration: " + event_duration + '\n';
		return out;
	}
}
