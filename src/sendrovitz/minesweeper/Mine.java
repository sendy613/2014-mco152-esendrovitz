package sendrovitz.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class Mine extends JFrame {
	private final int ROWS = 10;
	private final int COLUMNS = 10;
	private Integer numOfMines;
	private Integer begNumMines;
	private int number;
	private JPanel board;
	private JPanel statusBar;
	private JLabel remainingMines;
	private JPanel endGame;
	private JLabel gameOver;
	private JPanel panel;
	private JButton reload;
	private Container container;
	private MineButton mineField[][];
	private ImageIcon mineImage;
	private ImageIcon flagImage;
	private MouseAdapter listener;

	public Mine() {
		setSize(800, 600);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		container = getContentPane();
		container.setLayout(new BorderLayout());
		board = new JPanel();
		board.setLayout(new BorderLayout());
		this.panel = new JPanel();
		this.panel.setLayout(new GridLayout(ROWS, COLUMNS));
		this.endGame = new JPanel();
		endGame.setLayout(new FlowLayout());
		this.gameOver = new JLabel("GAME OVER");
		gameOver.setBackground(Color.RED);
		Border endPaddingBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border endBorder = BorderFactory.createLineBorder(Color.RED);
		gameOver.setBorder(BorderFactory.createCompoundBorder(endBorder, endPaddingBorder));
		endGame.add(gameOver);
		this.begNumMines = 0;
		mineField = new MineButton[ROWS][COLUMNS];
		flagImage = new ImageIcon("./smallFlag.png");
		mineImage = new ImageIcon("./smallMine.png");

		listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				MineButton button = (MineButton) e.getComponent();
				boolean m = button.getHasMine();

				if (SwingUtilities.isLeftMouseButton(e) && button.isVisited() == false) {
					if (m == true) {
						for (int i = 0; i < ROWS; i++) {
							for (int j = 0; j < COLUMNS; j++) {
								// if button has mine
								if (mineField[i][j].getHasMine()) {
									mineField[i][j].setIcon(mineImage);
								} else {
									mineField[i][j].setVisited(true);
									//mineField[i][j].removeMouseListener(listener);
								}
							}
						}
						endGame(false);

					}
					if (m == false) {
						getSurroundingCells(button);
						button.setVisited(true);
						checkIfWon();
					}

				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (button.isHasFlag()) {
						button.setIcon(null);
						numOfMines++;
						remainingMines.setText("Number of remaining mines: " + numOfMines.toString());
					} else if (numOfMines.compareTo(0) < 0 || numOfMines.compareTo(0) > 0) {
						numOfMines--;
						remainingMines.setText("Number of remaining mines: " + numOfMines.toString());
						button.setIcon(flagImage);
						button.setHasFlag(true);
						checkIfWonFlag();
					}
				} else if (e.getButton() == 3 && e.getButton() == 1) {
					// expose all surrounding cells if all surrounding bombs are
					// marked
				}
			}
		};

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				mineField[i][j] = new MineButton();
				mineField[i][j].setRow(i);
				mineField[i][j].setCol(j);
				mineField[i][j].addMouseListener(listener);
				panel.add(mineField[i][j]);
				setMines(mineField[i][j]);
			}
		}
		this.numOfMines = begNumMines;
		this.statusBar = new JPanel();
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));
		this.remainingMines = new JLabel("Number of remaining mines: " + begNumMines.toString());
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		remainingMines.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
		statusBar.add(remainingMines);
		this.reload = new JButton("RESTART");
		reload.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
		this.reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Mine.main(null);
			}
		});

		statusBar.add(reload);
		board.add(statusBar, BorderLayout.NORTH);
		board.add(panel, BorderLayout.CENTER);
		container.add(board, BorderLayout.CENTER);

	}

	public void setMines(MineButton button) {
		Random random = new Random();
		number = random.nextInt(100);

		if (number >= 80) {
			button.setHasMine(true);
			begNumMines++;
		} else {
			button.setHasMine(false);
		}

	}

	public void getSurroundingCells(MineButton button) {
		int r = button.getRow();
		int c = button.getCol();
		Integer count = 0;
		if (isOutOfBounds(r, c)) {
			return;
		}
		if (!isOutOfBounds(r, c - 1) && mineField[r][c - 1].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r, c + 1) && mineField[r][c + 1].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r + 1, c) && mineField[r + 1][c].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r - 1, c) && mineField[r - 1][c].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r - 1, c - 1) && mineField[r - 1][c - 1].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r - 1, c + 1) && mineField[r - 1][c + 1].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r + 1, c - 1) && mineField[r + 1][c - 1].getHasMine()) {
			count++;
		}
		if (!isOutOfBounds(r + 1, c + 1) && mineField[r + 1][c + 1].getHasMine()) {
			count++;
		}

		// no mines where found. cell is empty so need to check all its
		// surroundingcells.
		else if (count.compareTo(0) == 0 && !mineField[r][c].isVisited()) {
			mineField[r][c].setVisited(true);

			if(!isOutOfBounds(r,c-1)){
			getSurroundingCells(mineField[r][c - 1]);
			}
			if(!isOutOfBounds(r, c+1)){
			getSurroundingCells(mineField[r][c + 1]);
			}
			if(!isOutOfBounds(r+1, c)){
			getSurroundingCells(mineField[r + 1][c]);
			}
			if(!isOutOfBounds(r-1, c)){
			getSurroundingCells(mineField[r - 1][c]);
			}
			if(!isOutOfBounds(r-1, c-1)){
			getSurroundingCells(mineField[r - 1][c - 1]);
			}
			if(!isOutOfBounds(r-1, c+1)){
			getSurroundingCells(mineField[r - 1][c + 1]);
			}
			if(!isOutOfBounds(r+1, c-1)){
			getSurroundingCells(mineField[r + 1][c - 1]);
			}
			if(!isOutOfBounds(r+1, c+1)){
			getSurroundingCells(mineField[r + 1][c + 1]);
			}
		}

		mineField[r][c].setVisited(true);
		if (count.compareTo(0) < 0 || count.compareTo(0) > 0) {
			mineField[r][c].setText(count.toString());
		}
	}

	public boolean isOutOfBounds(int r, int c) {
		if (r < 0 || r >= ROWS || c < 0 || c >= COLUMNS) {
			return true;
		}
		return false;
	}

	
	//two ways to win either you flagged all the correct mines (and nothing extra) or you left all the mines covered and clicked everything else
	public void checkIfWonFlag() {
		boolean won = false;
		Integer count = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (mineField[i][j].isHasFlag() && mineField[i][j].getHasMine()) {
					count++;
				}
			}
		}
		if (count.compareTo(begNumMines) == 0) {
			won = true;
			gameOver.setText("YOU WON!");
			gameOver.setOpaque(true);
			container.add(endGame, BorderLayout.PAGE_START);
		}
	}

	public void checkIfWon() {
		boolean won = false;
		Integer count = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (mineField[i][j].isVisited()) {
					count++;
				}
			}
		}
		if (count.compareTo((ROWS * COLUMNS) - begNumMines) == 0) {
			won = true;
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLUMNS; j++) {
					if (mineField[i][j].getHasMine()) {
						mineField[i][j].setIcon(flagImage);
					}

				}

			}
			endGame(won);
		}
	}

	public void endGame(boolean b) {
		if (b == true) {
			gameOver.setText("YOU WON!");
		}
		gameOver.setOpaque(true);
		container.add(endGame, BorderLayout.PAGE_START);

	}

	public static void main(String[] args) {
		JFrame frame;
		frame = new Mine();
		frame.setVisible(true);

	}
}