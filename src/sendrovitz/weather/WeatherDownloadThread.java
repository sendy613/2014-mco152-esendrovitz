package sendrovitz.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {
	private WeatherFrame frame;
	public WeatherDownloadThread(WeatherFrame frame){
		this.frame = frame;
	}
	@Override
	public void run(){
		//put everything in here that want to run on this thread and not main thread
		try{
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		String json = IOUtils.toString(in);
		Gson gson = new Gson();
		// want to change string from json and give us back a class.
		WeatherNow now = gson.fromJson(json, WeatherNow.class); // gives us back
																// a weatherNow
		frame.displayWeather(now);
		}
		catch(IOException e){// object
			e.getMessage();
		}
	}
}
