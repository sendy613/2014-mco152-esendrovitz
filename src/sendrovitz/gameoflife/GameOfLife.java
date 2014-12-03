package sendrovitz.gameoflife;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameOfLife extends JFrame {
	private JButton cells[][] = new JButton[20][20];
	//1. if 0 or 1 neighbor is aive = die
	//2. 2 alive = SAME
	//3. 3 alive = LIVE
	//4. 4+ = DEAD
	//ALIVE = Green
	//DEAD = 
	public GameOfLife() {
		setSize(800, 600);
		setTitle("Game of Life");
		// this is only if your application has one window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// puts window into center
		setLocationRelativeTo(null);
		
		Container container = new Container(); //for the ones that u add to main pane
		container.setLayout(new GridLayout(20, 20));
		
		Container border = getContentPane(); //on main pane
		border.setLayout(new BorderLayout());
		
		JButton next = new JButton("Next Generation");
		next.setOpaque(true);
		next.setBorderPainted(false);
		border.add(next, BorderLayout.WEST);
		border.add(container, BorderLayout.CENTER);
		
		//actionListener is an interface. so how could you call new?
		//creating an anonymous class
		 //called actionPerformed (must create this type of class
		ActionListener listener = new ActionListener(){ //when click on this run this piece of code
			@Override
			public void actionPerformed(ActionEvent event){
				JButton button = (JButton) event.getSource();
				//code of what happens when click
				if(button.getBackground()==Color.GREEN){
				button.setBackground(Color.BLACK);
			}

				else{
					button.setBackground(Color.GREEN);
				}
			}
		};
		
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				nextGeneration();
				
			}
			
		});
		Random random = new Random();
		
		for (int i = 0; i < 20; i++) {
			for(int j=0; j<20; j++){
				
			final JButton button = new JButton();
			cells[i][j]= button;
			next.setOpaque(true);
			next.setBorderPainted(false);
			container.add(button);
			button.addActionListener(listener);
			int n = random.nextInt(100);
			
			if(n<30){
				button.setBackground(Color.GREEN);
			}
			else{
				button.setBackground(Color.BLACK);
			}
		}
	
		
		}
	}
	public int getNumAliveNeighbors(int i, int j){
		int numAlive=0;
		
		if(isALive(i-1, j-1)){
			numAlive++;
		}
		if(isALive(i, j-1)){
			numAlive++;
		}
		if(isALive(i+1, j-1)){
			numAlive++;
		}
		if(isALive(i-1, j)){
			numAlive++;
		}
		if(isALive(i+1, j)){
			numAlive++;
		}
		if(isALive(i+1, j+1)){
			numAlive++;
		}
		if(isALive(i-1, j+1)){
			numAlive++;
		}
		if(isALive(i, j+1)){
			numAlive++;
		}
		return numAlive;
	}
	private boolean isALive(int i, int j) {
		try{
		return cells[i][j].getBackground() == Color.GREEN;
	}
		catch(Exception e){
			return false;
		}
	}
	
	public void nextGeneration(){
		String[][] array = new String[20][20];
		for(int i =0; i<20; i++){
			for(int j=0; j<20; j++){
				int neighbors = getNumAliveNeighbors(i,j);
				switch(neighbors){
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					//cells[i][j].setBackground(Color.BLACK);
					array[i][j] = "BLACK";
					break;
				case 2:
					break;
				case 3:
					//cells[i][j].setBackground(Color.GREEN);
					array[i][j]= "GREEN";
				}
			}
		}
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array.length; j++){
				if(array[i][j]=="BLACK"){
					cells[i][j].setBackground(Color.BLACK);
				}
				else if(array[i][j]=="GREEN"){
					cells[i][j].setBackground(Color.GREEN);
					
				}
			}
		}
	}

	public static void main(String args[]) {
		GameOfLife game = new GameOfLife();
		game.setVisible(true);

	}

}
