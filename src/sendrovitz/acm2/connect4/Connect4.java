package sendrovitz.acm2.connect4;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connect4 extends JFrame {
	// 6 row by 7 columns
	public Connect4() {
		setSize(800, 600);
		setTitle("Connect Four");
		// this is only if your application has one window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// puts window into center
		setLocationRelativeTo(null);
		Container container = getContentPane();

		container.setLayout(new GridLayout(6, 7));
		
		
		//actionListener is an interface. so how could you call new?
		//creating an anonymous class
		 //called actionPerformed (must create this type of class
		ActionListener listener = new ActionListener(){ //when click on this run this piece of code
			@Override
			public void actionPerformed(ActionEvent event){
				JButton button = (JButton) event.getSource();
				//code of what happens when click
				if(button.getBackground()==Color.GREEN){
				button.setBackground(Color.PINK);
			}
				else if(button.getBackground()==Color.PINK){
					button.setBackground(Color.YELLOW);
				}
				else{
					button.setBackground(Color.GREEN);
				}
			}
		};
	
		for (int i = 0; i < 42; i++) {
			final JButton button = new JButton();
			container.add(button);
			button.addActionListener(listener);
		}
	
		

	}

	public static void main(String args[]) {
		Connect4 connect4 = new Connect4();
		connect4.setVisible(true);

	}

}
