package sendrovitz.iss;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ISSOverheadFrame extends JFrame {
	private JTextField address;
	private JList<String> list;

	public ISSOverheadFrame() throws IOException {
		setSize(1500, 1000);
		setTitle("ISS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		address = new JTextField();
		JButton button = new JButton("Get Times");
		panel.add(address);
		panel.add(button);
		list = new JList<String>();
		container.add(list, BorderLayout.CENTER);
		container.add(panel, BorderLayout.NORTH);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ISSOverheadRequestThread t = new ISSOverheadRequestThread(list, address.getText());
				t.start();
				/*
				 * String add = address.getText(); String addressFinal =
				 * URLEncoder.encode(add, "UTF-8"); URL urlGeo = new
				 * URL("https://maps.googleapis.com/maps/api/geocode/json?address="
				 * + addressFinal +
				 * "&key=AIzaSyDtaau9U0eCoMJ7_cCjX5fAInwAJtz49_M");
				 * URLConnection connection = urlGeo.openConnection();
				 * InputStream in = connection.getInputStream(); String json =
				 * IOUtils.toString(in); Gson gson = new Gson(); Instance iss =
				 * gson.fromJson(json, Instance.class); Results[] array =
				 * iss.getResults(); for (int i = 0; i < array.length; i++) {
				 * LAT = array[i].getGeometry().getLocation().getLat(); LON =
				 * array[i].getGeometry().getLocation().getLng(); } URL url =
				 * new URL("http://api.open-notify.org/iss-pass.json?lat=" + LAT
				 * + "&lon=" + LON); URLConnection connection2 =
				 * url.openConnection(); InputStream in2 =
				 * connection2.getInputStream(); String json2 =
				 * IOUtils.toString(in2); Instance2 instance2 =
				 * gson.fromJson(json2, Instance2.class); Response[] array2 =
				 * instance2.getResponse(); String[] listArray = new
				 * String[array2.length]; Long timestamp; for (int i = 0; i <
				 * array2.length; i++) { timestamp = array2[i].getRisetime();
				 * long unixSeconds = timestamp; Date date = new
				 * Date(unixSeconds * 1000L); // *1000 is to convert // seconds
				 * to // milliseconds SimpleDateFormat sdf = new
				 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the // format
				 * // of // your // date String formattedDate =
				 * sdf.format(date); listArray[i]= formattedDate;
				 * 
				 * list.setListData(listArray);
				 */

			}
		});

	}

	public static void main(String args[]) {
		ISSOverheadFrame frame;
		try {

			frame = new ISSOverheadFrame();
			frame.setVisible(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
