package sendrovitz.minesweeper;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DownloadFlagThread extends Thread {
		private URL url;
		private JLabel imageLabel;
		public void DownloadImageThread(URL url,JLabel imageLabel){
			this.url=url;
			this.imageLabel = imageLabel;
		}
		@Override
		public void run(){
			ImageIcon image = new ImageIcon(url);
			imageLabel.setIcon(image);
		}
	}


