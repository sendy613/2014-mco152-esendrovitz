package sendrovitz.opportunity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class NasaDownloadThread extends Thread {
	private JLabel imageLabel;
	// private NasaFrame frame;
	private JPanel imagePanel;
	private JLabel number;
	private Integer picNum;
	private Mi_Images[] mi_images;
	private NasaFrame frame;

	public NasaDownloadThread(NasaFrame frame, JLabel number, Integer picNum) {
		// this.frame = frame;
		this.number = number;
		this.picNum = picNum;
		this.frame = frame;
	}

	@Override
	public void run() {
		try {
			URL url = new URL("https://merpublic.s3.amazonaws.com/oss/mera/images/images_sol13.json");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			// want to change string from json and give us back a class.
			Instance nasa = gson.fromJson(json, Instance.class);
			frame.displayFrame(nasa);
		} catch (IOException e) {// object
			e.getMessage();
		}
	}
}
