package sendrovitz.weather;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.google.gson.Gson;

import sendrovitz.gui.WeatherGUI;

public class WeatherFrame extends JFrame {
	public WeatherFrame() throws IOException {
		WeatherDownloadThread thread = new WeatherDownloadThread();
		thread.start();
		setSize(450, 200);
		setTitle("Current Weather");
		// this is only if your application has one window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// puts window into center
		setLocationRelativeTo(null);

		Container container = getContentPane();

		// sets layout manager of window
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);

		// jLabel displays info (cannot interact w it)
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout()); // flowLayout centers things
													// box-left
		// add labels to container
		northContainer.add(new JLabel("Current Weather for "+ now.getName() + ":", JLabel.CENTER));
		// add the container to the window
		container.add(northContainer, BorderLayout.NORTH);
		
		//IMAGE
		Image image;
		Weather weather[] = now.getWeather();
		String icon = weather[0].getIcon();
		try {
			URL url2 = new URL("http://openweathermap.org/img/w/" + icon + ""
					+ ".png");
			image = ImageIO.read(url2);
			JLabel label3 = new JLabel(new ImageIcon(image));
			label3.setBackground(Color.GREEN);
			label3.setOpaque(true);
			container.add(label3, BorderLayout.CENTER);
		} catch (IOException e) {
		}
		
		//TEMP
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.GREEN);
		JLabel label2 = new JLabel("Temp: "+ Double.toString(now.getMain().getTemp()) + " degrees",JLabel.CENTER);
		label2.setBackground(Color.GREEN);
		label2.setOpaque(true);
		panel.add(label2, BorderLayout.WEST);

		// display first image from the web. id.png
		// description of all the times of weather, temp, min temp, max temp
	
		//temp min
		JLabel label4 = new JLabel("Min temp: " + Double.toString(now.getMain().getTempMin()) + " degrees",JLabel.CENTER);
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setVerticalAlignment(SwingConstants.CENTER);
		label4.setBackground(Color.GREEN);
		label4.setOpaque(true);
		panel.add(label4, BorderLayout.CENTER);
		
		//temp max
		JLabel label5 = new JLabel("Max temp: " + Double.toString(now.getMain().getTempMax()) + " degrees", JLabel.CENTER);
		label5.setBackground(Color.GREEN);
		label5.setOpaque(true);
		panel.add(label5, BorderLayout.EAST);
		
		//description
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel2.setBackground(Color.GREEN);
		Weather array[] = now.getWeather();
		JLabel labels[] = new JLabel[array.length];
		for(int i=0; i<array.length; i++){
		JLabel desc = new JLabel(array[i].getDescription(), JLabel.CENTER);
		panel2.add(desc);
			}

		panel.add(panel2, BorderLayout.SOUTH);
		
		container.add(panel, BorderLayout.SOUTH);
		container.setBackground(Color.GREEN);
		// center is everything that is not west, east, south, north
	}

	public static void main(String args[]) {
		WeatherFrame frame;
		try {
			frame = new WeatherFrame();
			frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
