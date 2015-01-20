package sendrovitz.minesweeper;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

	public class DownloadImageThread extends Thread{
		private URL url;
		private MineButton button;
		public DownloadImageThread(URL url,MineButton button){
			this.url=url;
			//this.imageLabel = imageLabel;
			this.button = button;
		}
		@Override
		public void run(){
			ImageIcon image = new ImageIcon(url);
			//imageLabel.setIcon(image);
			button.setIcon(image);
		}
}
