package sendrovitz.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {
	@Override
	public void run(){
		//put everything in here that want to run on this thread and not main thread
		try{
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		StringBuilder builder = new StringBuilder();
		byte[] b = new byte[4096];
		int n = -1;
		while ((n = in.read(b)) != -1) { // reads it in and saves it in n. then
											// it checks to see if -1. returns
											// -1 when there is no more data to
											// read
			// in.read(b); //reads 4096 bytes from input stream. n=number if
			// bytes returned
			builder.append(new String(b, 0, n)); // creates a new string from
													// b[0] until b[n]= (n-1)
		}
		String json = builder.toString();
		Gson gson = new Gson();
		// want to change string from json and give us back a class.
		WeatherNow now = gson.fromJson(json, WeatherNow.class); // gives us back
																// a weatherNow
		}
		catch(IOException e){// object
			e.getMessage();
		}
	}
}
