package sendrovitz.rottenTomatoes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class RottenTomatoes extends JFrame {
	private JButton button;
	private JPanel panel;
	private JPanel imagePanel;
	private JPanel infoPanel;
	private JLabel imageLabel;
	private JLabel title;
	private JLabel rating1;
	private JLabel rating2;
	private JLabel rating3;

	public RottenTomatoes() {
		setSize(450, 200);
		setTitle("Rotten Toamtoes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setLayout(new BorderLayout());
		button = new JButton("get info");
		imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		imageLabel = new JLabel();
		title = new JLabel();
		rating1 = new JLabel();
		rating2 = new JLabel();
		rating3 = new JLabel();
		MoviesDownloadThread thread = new MoviesDownloadThread(this, imageLabel);
		thread.start();
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		Border paddingBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		infoPanel.setBorder(BorderFactory.createCompoundBorder(paddingBorder, paddingBorder));
		imagePanel.setBorder(BorderFactory.createCompoundBorder(paddingBorder, paddingBorder));
		Border paddingBorderRating = BorderFactory.createEmptyBorder(4, 4, 4, 4);
		rating1.setBorder(BorderFactory.createCompoundBorder(border, paddingBorderRating));
		rating2.setBorder(BorderFactory.createCompoundBorder(border, paddingBorderRating));
		rating3.setBorder(BorderFactory.createCompoundBorder(border, paddingBorderRating));
		title.setBorder(BorderFactory.createCompoundBorder(border, paddingBorderRating));
		infoPanel.add(title);
		infoPanel.add(rating1);
		infoPanel.add(rating2);
		infoPanel.add(rating3);

		button.setAlignmentY(CENTER_ALIGNMENT);
		button.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(button, BorderLayout.CENTER);
		
		container.add(panel, BorderLayout.CENTER);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				panel.removeAll();
				//panel.add(imagePanel);
			//	panel.add(infoPanel);
				panel.add(imagePanel, BorderLayout.WEST);
				panel.add(infoPanel, BorderLayout.EAST);
				panel.revalidate();
			}

		});
		/*
		 * container.add(imagePanel); container.add(infoPanel);
		 */

	}

	public void displayFrame(Instance instance) {
		Movies movies[] = instance.getMovies();
		String titleText = movies[2].getTitle();
		title.setText("Title: " + titleText);
		rating1.setText("Critics Rating: " + movies[2].getRatings().getCritics_rating());
		rating2.setText("Critics Score: " + movies[2].getRatings().getCritics_score());
		rating3.setText("Audience Score: " + movies[2].getRatings().getAudience_score());

	}

	public static void main(String args[]) {
		RottenTomatoes frame;
		frame = new RottenTomatoes();
		frame.setVisible(true);

	}

}
