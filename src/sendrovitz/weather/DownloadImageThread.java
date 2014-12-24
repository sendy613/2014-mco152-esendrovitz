package sendrovitz.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class DownloadImageThread extends Thread{
	private URL url;
	private JLabel imageLabel;
	public DownloadImageThread(URL url,JLabel imageLabel){
		this.url=url;
		this.imageLabel = imageLabel;
	}
	@Override
	public void run(){
		ImageIcon image = new ImageIcon(url);
		imageLabel.setIcon(image);
	}
}
