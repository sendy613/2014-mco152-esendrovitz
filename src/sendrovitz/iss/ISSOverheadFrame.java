package sendrovitz.iss;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverheadFrame extends JFrame {
private String add;
private JTextField address;
	public ISSOverheadFrame()throws IOException{
		setSize(1500, 1000);
		setTitle("ISS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		Vector<JTextField> textFieldVector = new Vector<JTextField>();
		address= new JTextField();
		address.setBounds(70, 70, 120, 20);
		textFieldVector.add(address);
		//panel.add(textFieldVector);
		JButton button = new JButton("get times");
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				add = address.getText();
//				//System.out.println(add);
//			
//						}
//
//		});
		panel.add(button);
		
//		JList<String> list;
//		String addressFinal = URLEncoder.encode(add,"UTF-8");
//		URL urlGeo = new URL(
//				"https://maps.googleapis.com/maps/api/geocode/json?address="+ addressFinal+"&key=792b41615caf3e33b251d4ee53a37b92%3A11%3A70519033");
//		URLConnection connection = urlGeo.openConnection();
//		InputStream in = connection.getInputStream();
//		String json = IOUtils.toString(in);
//		Gson gson = new Gson();
//		Instance iss = gson.fromJson(json, Instance.class);
//		Results[] array = iss.getResults();
//		String LAT="";
//		String LON="";
//		for(int i =0; i<array.length; i++){
//			LAT = array[i].getGeometry().getLocation().getLat();
//			LON= array[i].getGeometry().getLocation().getLng();
//		}
//		
//		URL url = new URL(
//				"http://api.open-notify.org/iss-pass.json?lat="+LAT+"&lon="+LON);
//		URLConnection connection2 = url.openConnection();
//		InputStream in2 = connection2.getInputStream();
//		String json2 = IOUtils.toString(in2);
//		Gson gson2 = new Gson();
//		Instance2 instance2 = gson2.fromJson(json2, Instance2.class);
//		Response[] array2 = instance2.getResponse();
//		DefaultListModel<String> model = new DefaultListModel<String>();
//		Long timestamp;
//		for(int i=0; i<array2.length; i++){
//			timestamp = array2[i].getRisetime();
//			long unixSeconds = timestamp;
//			Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
//			String formattedDate = sdf.format(date);
//			model.addElement(formattedDate + "\n");
//		}
//		list= new JList<String>(model);
//		
//
	//	panel.add(list);
		container.add(panel);
		

		
		
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
