package sendrovitz.rottenTomatoes;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageDownloadThread extends Thread{
	private String url;
	private JLabel imageLabel;
	public ImageDownloadThread(String url,JLabel imageLabel){
		this.url=url;
		this.imageLabel = imageLabel;
	}
	@Override
	public void run(){
		URL newUrl;
		try {
			newUrl = new URL(url);
			ImageIcon image = new ImageIcon(newUrl);
			imageLabel.setIcon(image);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
