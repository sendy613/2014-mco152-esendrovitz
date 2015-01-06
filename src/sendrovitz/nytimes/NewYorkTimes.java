package sendrovitz.nytimes;

import java.awt.Color;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class NewYorkTimes extends JFrame {
	private JList<String> list;

	public NewYorkTimes() throws IOException {
		Calendar calendar = Calendar.getInstance();
		int year = (calendar.get(Calendar.YEAR));
		int month = (calendar.get(Calendar.MONTH) + 1);
		int day = (calendar.get(Calendar.DAY_OF_MONTH));
		String date = (year + "" + month + "" + day);
		URL url = new URL(
				"http://api.nytimes.com/svc/search/v2/articlesearch.json?callback=svc_search_v2_articlesearch&end_date="
						+ date + "&sort=newest&page=0&api-key=792b41615caf3e33b251d4ee53a37b92%3A11%3A70519033");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		// StringBuilder builder = new StringBuilder();
		// byte[] b = new byte[4096];
		// int n = -1;
		// while ((n = in.read(b)) != -1) {
		// builder.append(new String(b, 0, n)); // creates a new string from
		// // b[0] until b[n]= (n-1)
		// }
		// this line of code replaces the whole code above
		String json = IOUtils.toString(in);
		Gson gson = new Gson();

		Instance nyt = gson.fromJson(json, Instance.class);

		setSize(1500, 1000);
		setTitle("Headlines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		Docs[] array = nyt.getResponse().getDocs();

		for (int i = array.length - 1; i >= 0; i--) {
			DefaultListModel<String> model = new DefaultListModel<String>();
			model.addElement("\n");
			model.addElement("Headline: " + array[i].getHeadline().getMain() + "\n");
			model.addElement("Lead Paragraph: " + array[i].getLead_paragraph() + "\n");

			list = new JList<String>(model);
			list.setBackground(Color.YELLOW);
			list.setAlignmentX(0);
			panel.add(list);
			JButton button = new JButton("Click here for full article:");
			button.setBackground(Color.WHITE);

			// need to make the url final bec you need i at point where button
			// is created
			final URI uri = array[i].getWeb_url();
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					// String text = button.getText();
					// URI url = URI.create(text);
					try {
						java.awt.Desktop.getDesktop().browse(uri);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			});

			panel.add(button);

		}

		panel.setBackground(Color.YELLOW);
		JScrollPane listScrollPane = new JScrollPane(panel);
		container.add(listScrollPane);

		// JButton button = new JButton("RELOAD");
		// container.add(button, BorderLayout.WEST);

		// Container buttons = new Container();
		// buttons.setLayout(new BoxLayout(buttons,BoxLayout.PAGE_AXIS));
		// container.add(buttons, BorderLayout.EAST);
		// JTextField beg = new JTextField("Enter beginning date: (yyyymmdd)");
		// beg.setVisible(true);
		// buttons.add(beg);
		// JButton button = new JButton("RELOAD");
		// buttons.add(button);
		// // JTextField end = new JTextField("Enter end date: (yyyymmdd)");
		// // end.setVisible(true);
		// // buttons.add(end);
		// String begDate = beg.getText();
		// //String endDate = end.getText();
		// //this.date=begDate;
		//
		//
		//
		// button.addActionListener(new ActionListener(){
		//
		// @Override
		// public void actionPerformed(ActionEvent event) {
		// //String begDate = beg.getText();
		// //System.out.println(begDate);
		// NewYorkTimes.main(array2);
		//
		// }
		//
		// });
	}

	public static void main(String args[]) {
		NewYorkTimes times;
		try {
			times = new NewYorkTimes();
			times.setVisible(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

//space in json variable name:
//@SerializedName("complete streets")

//private Complete completestreets;
