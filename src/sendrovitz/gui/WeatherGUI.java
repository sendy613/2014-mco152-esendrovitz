package sendrovitz.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherGUI extends JFrame{

	public WeatherGUI(){
		setSize(800,600);
		setTitle("Current Weather");
		//this is only if your application has one window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//puts window into center
		setLocationRelativeTo(null);
		
		
		Container container = getContentPane();
		
		//sets layout manager of window
		//BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS); //vertically
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		
		//jLabel displays info (cannot interact w it)
		//container.add(new JLabel("Hello World"));
		//container.add(new JLabel("This class rocks!"));
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout()); //flowLayout centers things   box-left
		//add labels to container
		northContainer.add(new JLabel("Hello World"));
		northContainer.add(new JLabel("Hello More World"));
		//add the container to the window
		container.add(northContainer, BorderLayout.NORTH);
		
		container.add(new JLabel("This class rocks!"), BorderLayout.SOUTH);
		JLabel label = new JLabel("thanks");
		label.setBackground(Color.GREEN);
		label.setOpaque(true);
		//center is everything that is not west, east, south, north
		container.add(label, BorderLayout.CENTER);
		container.add(new JLabel("This class rocks!"), BorderLayout.EAST);
		container.add(new JLabel("Thanks for your silence!"), BorderLayout.WEST);
	}
	public static void main (String args[]){
		WeatherGUI frame = new WeatherGUI();
		frame.setVisible(true);
		
	}
	
	}
