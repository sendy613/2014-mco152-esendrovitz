package sendrovitz.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */
public class Datebook {
	private Map<Date, ArrayList<Event>> single = new HashMap<Date, ArrayList<Event>>();
	private ArrayList<Event> daily = new ArrayList<Event>();
	private Map<Integer, ArrayList<Event>> weekly = new HashMap<Integer, ArrayList<Event>>();
	private Map<Integer, ArrayList<Event>> monthly = new HashMap<Integer, ArrayList<Event>>();
	private Map<Integer, ArrayList<Event>> yearly = new HashMap<Integer, ArrayList<Event>>();

	public Datebook() {
		
	}

	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */
	public void addSingleEvent(Event event, Date date) {
		ArrayList<Event> singleEvents = null;
		if(single.get(date)==null){
		singleEvents =  new ArrayList<Event>();
	}
		singleEvents.add(event);	
		single.put(date, singleEvents );

	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {
		daily.add(event);
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
		ArrayList<Event> weeklyEvents = null;
		if(weekly.get(dayOfWeek)==null){
		weeklyEvents =  new ArrayList<Event>();
	}
		weeklyEvents.add(event);	
		weekly.put(dayOfWeek, weeklyEvents );

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {
		ArrayList<Event> monthlyEvents = null;
		if(monthly.get(dayOfMonth)==null){
		monthlyEvents =  new ArrayList<Event>();
	}
		monthlyEvents.add(event);	
		monthly.put(dayOfMonth, monthlyEvents );

	}
	

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {
		ArrayList<Event> yearlyEvents = null;
		if(yearly.get(dayOfYear)==null){
		yearlyEvents =  new ArrayList<Event>();
	}
		yearlyEvents.add(event);	
		yearly.put(dayOfYear, yearlyEvents );

	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		List<Event> list = new ArrayList<Event>();
		//single
		ArrayList<Event> tempSingle = single.get(date);
		if(tempSingle!=null){
			list.addAll(tempSingle);
		}
		//daily
		for(int i=0; i<daily.size(); i++){
			list.add(daily.get(i));
		}
		//weekly
		Date tempDate = new Date(); ; 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		int dayOf = calendar.get(Calendar.DAY_OF_WEEK);
		
		ArrayList<Event> tempWeekly = weekly.get(dayOf);
		if(tempWeekly!=null){
			list.addAll(tempWeekly);
		}
		
		//monthly
		dayOf = calendar.get(Calendar.DAY_OF_MONTH);
		ArrayList<Event> tempMonthly = monthly.get(dayOf);
		if(tempMonthly!=null){
			list.addAll(tempMonthly);
		}
		
		//yearly
		dayOf = calendar.get(Calendar.DAY_OF_YEAR);
		ArrayList<Event> tempYearly = yearly.get(dayOf);
		if(tempYearly!=null){
			list.addAll(tempYearly);
		}
		
		Collections.sort(list);
		
		return list;
	}
}
