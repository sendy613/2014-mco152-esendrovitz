package sendrovitz.opportunity;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NasaFrame extends JFrame {
	// private JPanel panel;
	private JPanel statusBar;
	private JPanel imagePanel;
	private JLabel imageLabel;
	private JButton left;
	private JButton right;
	private JLabel number;
	private Integer picNum;
	private Mi_Images mi_images[];
	private Pcam_Images pcam_images[];
	private Ncam_Images ncam_images[];
	private Fcam_Images fcam_images[];
	private Images[] array;
	private Container container;

	public NasaFrame() {
		setSize(450, 200);
		setTitle("Nasa Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		container = getContentPane();
		container.setLayout(new BorderLayout());
		this.statusBar = new JPanel();
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));
		this.imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		imageLabel = new JLabel();
		array = new Images[255];
		imagePanel.add(imageLabel);
		picNum = 1;
		number = new JLabel(picNum.toString());
		left = new JButton("<");
		right = new JButton(">");
		statusBar.add(left);
		statusBar.add(number);
		statusBar.add(right);
		container.add(statusBar, BorderLayout.PAGE_END);
		container.add(imagePanel, BorderLayout.CENTER);
		NasaDownloadThread thread = new NasaDownloadThread(this, number, picNum);
		thread.start();

		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				picNum--;
				if (picNum == -1) {
					picNum = 255;
				}
				ImageDownloadThread thread = new ImageDownloadThread(mi_images, imageLabel, imagePanel, number, picNum);
				thread.start();
			}

		});

		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				picNum++;
				if (picNum == 256) {
					picNum = 1;
				}
				ImageDownloadThread thread = new ImageDownloadThread(mi_images, imageLabel, imagePanel, number, picNum);
				thread.start();

			}

		});

	}

	public void displayFrame(Instance instance) {
		mi_images = instance.getMi_images();
		pcam_images = instance.getPcam_images();
		fcam_images = instance.getFcam_images();
		ncam_images = instance.getNcam_images();
		ImageDownloadThread imageThread = new ImageDownloadThread(mi_images, imageLabel, imagePanel, number, picNum);
		imageThread.start();

	}

	public static void main(String args[]) {
		NasaFrame frame = new NasaFrame();
		frame.setVisible(true);
	}
}
