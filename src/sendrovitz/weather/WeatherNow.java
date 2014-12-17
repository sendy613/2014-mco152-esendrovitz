package sendrovitz.weather;

public class WeatherNow {
	private String name; //names much match the names of the objects from the site
	private Weather[] weather;
	private Main main;
	public String getName() {
		return name;
	}
	
	public Weather[] getWeather() {
		return weather;
	}
	
	public Main getMain() {
		return main;
	}
	
	
}
