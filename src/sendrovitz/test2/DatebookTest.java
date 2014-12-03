package sendrovitz.test2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);

		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		Date date = getDate(2014, Calendar.JANUARY, 1);
		int dayOfYear = 1;
		datebook.addYearlyEvent(event, dayOfYear);

		List<Event> list = datebook.getEvents(date);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned day of year
		Date tomorrow = getDate(2014, Calendar.JANUARY, 2);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		Date date = getDate(2014, Calendar.JANUARY, 13);
		int dayOfMonth = 13;
		datebook.addMonthlyEvent(event, dayOfMonth);

		List<Event> list = datebook.getEvents(date);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned next day of month
		Date tomorrow = getDate(2014, Calendar.JANUARY, 14);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		Date date = getDate(2014, Calendar.JANUARY, 13);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		datebook.addWeeklyEvent(event, dayOfWeek);

		List<Event> list = datebook.getEvents(date);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned next month
		Date tomorrow = getDate(2014, Calendar.JANUARY, 14);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		Date date = getDate(2014, Calendar.JANUARY, 13);
		datebook.addDailyEvent(event);
		
		List<Event> list = datebook.getEvents(date);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
		
		Event event2 = new Event("EVENT 2", 1100);
		Date date2 = getDate(2014, Calendar.JANUARY, 14);
		datebook.addDailyEvent(event2);
		list = datebook.getEvents(date2);
		Assert.assertEquals(2, list.size());
		Assert.assertNotNull(list);
	
	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {
		Datebook datebook = new Datebook();
		//daily
		Event event = new Event("EVENT 1", 1200);
		Date date = getDate(2014, Calendar.JANUARY, 13);
		datebook.addDailyEvent(event);
		//weekly
		Event event2 = new Event("EVENT 2",0000);
		Date date2 = getDate(2014, Calendar.JANUARY, 13);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		datebook.addWeeklyEvent(event2, dayOfWeek);
		//monthly
		Event event3 = new Event("EVENT 3", 1000);
		Date date3 = getDate(2014, Calendar.JANUARY, 13);
		int dayOfMonth = 13;
		datebook.addMonthlyEvent(event3, dayOfMonth);
		//yearly
		Event event4 = new Event("EVENT 4", 1240);
		Date date4 = getDate(2014, Calendar.JANUARY, 13);
		int dayOfYear = 13;
		datebook.addYearlyEvent(event4, dayOfYear);
		//single
		Event event5 = new Event("EVENT 5", 2300);
		Date today = getDate(2014, Calendar.JANUARY, 13);
		datebook.addSingleEvent(event5, today);
		
		
		List<Event> list=  datebook.getEvents(date);
		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		
		boolean bool = true;
		if(list.get(0)!=event2){
			bool =false;
		}
		else if(list.get(1)!=event3){
			bool =false;
		}
		else if(list.get(2)!=event){
			bool =false;
		}
		else if(list.get(3)!=event4){
			bool =false;
		}
		else if(list.get(4)!=event5){
			bool =false;
		}
		Assert.assertEquals(true, bool);
	}

}
