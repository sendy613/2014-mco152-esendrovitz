package sendrovitz.earthquakes;

import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JList;

import sendrovitz.weather.WeatherFrame;
import sendrovitz.weather.WeatherNow;

import com.google.gson.Gson;

public class PastEarthquakes extends JFrame{
public PastEarthquakes() throws IOException{
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
	setSize(600, 600);
	setTitle("Past Earthquakes");
	// this is only if your application has one window
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// puts window into center
	setLocationRelativeTo(null);

	Container container = getContentPane();
	Feature[] array = q.getFeatures();
	String[] array2 = new String[array.length*3];
	int counter =0;
	for(int i=0; i<array.length; i++){
		array2[counter++]=array[i].getProperties().getPlace();
		array2[counter++] = ("MAG: "+(array[i].getProperties().getMag()).toString());
		array2[counter++] = ("\n");
	}
	JList list = new JList(array2);
	list.setBackground(Color.ORANGE);
	container.add(list);
	
	
}
public static void main(String args[]) {
	PastEarthquakes quake;
	try {
		quake = new PastEarthquakes();
		quake.setVisible(true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
