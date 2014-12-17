package sendrovitz.earthquakes;

import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;

import sendrovitz.weather.WeatherNow;

import com.google.gson.Gson;

public class PastEarthquakes {

	URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
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
	Earthquake q = gson.fromJson(json, Earthquake.class); // gives us back
															// a weatherNo
															// object
//	Feature[] array = q.getFeatures();
//	for(int i=0; i<array.length; i++){
//		builder.append(array[i].getProperties().getPlace());
//		builder.append(" ");
//		builder.append(array[i].getProperties().getMag());
//		builder.append("\n");
//	}
//	System.out.println(builder.toString());
	setSize(450, 200);
	setTitle("Current Weather");
	// this is only if your application has one window
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// puts window into center
	setLocationRelativeTo(null);

	Container container = getContentPane();
}

}
