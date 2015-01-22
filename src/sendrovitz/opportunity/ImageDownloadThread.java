package sendrovitz.opportunity;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageDownloadThread extends Thread {
	private Mi_Images[] mi_images;
	private JLabel imageLabel;
	private JPanel imagePanel;
	private JLabel number;
	private Integer picNum;

	public ImageDownloadThread(Mi_Images[] mi_images, JLabel imageLabel, JPanel imagePanel, JLabel number,
			Integer picNum) {
		this.mi_images = mi_images;
		this.imageLabel = imageLabel;
		this.imagePanel = imagePanel;
		this.number = number;
		this.picNum = picNum;

	}

	@Override
	public void run() {
		URL newUrl;
		try {
			Images images[] = mi_images[picNum].getImages();
			newUrl = new URL(images[0].getUrl());
			ImageIcon image = new ImageIcon(newUrl);
			imageLabel.setIcon(image);

			number.setText(picNum.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
