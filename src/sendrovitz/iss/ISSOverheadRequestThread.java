package sendrovitz.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JList;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverheadRequestThread extends Thread {
	private JList<String> list;
	private String address;

	public ISSOverheadRequestThread(JList<String> list, String address) {
		this.list=list; //cant create new one need to use existing one
		this.address = address;
	}

	@Override
	public void run() {
		String addressFinal;
		try {
			addressFinal = URLEncoder.encode(address, "UTF-8");
			URL urlGeo = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + addressFinal
					+ "&key=AIzaSyDtaau9U0eCoMJ7_cCjX5fAInwAJtz49_M");
			URLConnection connection = urlGeo.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			Instance iss = gson.fromJson(json, Instance.class);
			Results[] array = iss.getResults();
			String LAT = "";
			String LON = "";
			for (int i = 0; i < array.length; i++) {
				LAT = array[i].getGeometry().getLocation().getLat();
				LON = array[i].getGeometry().getLocation().getLng();
			}
			URL url = new URL("http://api.open-notify.org/iss-pass.json?lat=" + LAT + "&lon=" + LON);
			URLConnection connection2 = url.openConnection();
			InputStream in2 = connection2.getInputStream();
			String json2 = IOUtils.toString(in2);
			Instance2 instance2 = gson.fromJson(json2, Instance2.class);
			Response[] array2 = instance2.getResponse();
			String[] listArray = new String[array2.length];
			Long timestamp;
			for (int i = 0; i < array2.length; i++) {
				timestamp = array2[i].getRisetime();
				long unixSeconds = timestamp;
				Date date = new Date(unixSeconds * 1000L); // *1000 is to
															// convert
															// seconds to
															// milliseconds
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the
																						// format
																						// of
																						// your
																						// date
				String formattedDate = sdf.format(date);
				listArray[i] = formattedDate;
				list.setListData(listArray);
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
