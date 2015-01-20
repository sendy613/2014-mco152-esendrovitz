package sendrovitz.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
	private int number;
	private JPanel board;
	private JPanel statusBar;
	private JLabel remainingMines;
	private JPanel endGame;
	private JLabel gameOver;
	private JPanel panel;
	private JButton reload;
	private JLabel imageLabel;
	private Container container;
	private MineButton mineField[][];
	private Stack<MineButton> cells;

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
		this.panel.setLayout(new GridLayout(10, 10));
		this.endGame = new JPanel();
		endGame.setLayout(new BorderLayout());
		this.gameOver = new JLabel("GAME OVER");
		this.numOfMines = 0;
		mineField = new MineButton[ROWS][COLUMNS];
		cells = new Stack<MineButton>();
		MouseAdapter listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				MineButton button = (MineButton) e.getComponent();
				boolean m = button.getHasMine();

				if (SwingUtilities.isLeftMouseButton(e) && button.isVisited() == false) {
					if (m == true) {
						// ////?????????????
						for (int i = 0; i < ROWS; i++) {
							for (int j = 0; j < COLUMNS; j++) {
								// if button has mine
								if (mineField[i][j].getHasMine()) {
									String urlString = ("http://www.rw-designer.com/icon-view/3078.png");
									URL url;
									try {
										url = new URL(urlString);
										DownloadImageThread imageThread = new DownloadImageThread(url, button);
										imageThread.start();
										button.add(imageLabel);
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} else {
									//???????????????????????
									String text = mineField[i][j].getButtonText().toString();
									mineField[i][j].setButtonText(text);
								}
							}
						}

					}
					if (m == false) {
						getSurroundingCells(button);
						button.setVisited(true);
					}

				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (button.isHasFlag()) {
						button.setIcon(null);
						numOfMines++;
						remainingMines.setText("Number of remaining mines: " + numOfMines.toString());
						button.setVisited(false);
					} else {
						numOfMines--;
						remainingMines.setText("Number of remaining mines: " + numOfMines.toString());
						String urlString = ("http://www.rw-designer.com/icon-view/3079.png");
						URL url;
						try {
							url = new URL(urlString);
							DownloadImageThread imageThread = new DownloadImageThread(url, button);
							imageThread.start();
							// button.add(imageLabel);
							button.setHasFlag(true);
							button.setVisited(true);
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
		this.statusBar = new JPanel();
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));
		this.remainingMines = new JLabel("Number of remaining mines: " + numOfMines.toString());
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
		imageLabel = new JLabel();

	}

	public void setMines(MineButton button) {
		Random random = new Random();
		number = random.nextInt(100);

		if (number >= 70) {
			button.setHasMine(true);
			setNumbers(button);
			numOfMines++;
		} else {
			button.setHasMine(false);
		}

	}

	public void setNumbers(MineButton button) {
		MineButton temp = findButton(button.getRow(), button.getCol());
		int r = temp.getRow();
		int c = temp.getCol();
		// up
		if (r != 0) {
			temp.setRow(r - 1);
			temp.setCol(c);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}

		}
		// down
		if (r != ROWS) {
			temp.setRow(r + 1);
			temp.setCol(c);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
		// right
		if (c != COLUMNS) {
			temp.setRow(r);
			temp.setCol(c + 1);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
		// left
		if (c != 0) {
			temp.setRow(r);
			temp.setCol(c - 1);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
		// up-right
		if (r != 0 && c != COLUMNS) {
			temp.setRow(r - 1);
			temp.setCol(c + 1);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
		// up-left
		if (r != 0 && c != 0) {
			temp.setRow(r - 1);
			temp.setCol(c - 1);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
		// down-right
		if (r != ROWS && c != COLUMNS) {
			temp.setRow(r + 1);
			temp.setCol(c + 1);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
		// down-left
		if (r != ROWS && c != 0) {
			temp.setRow(r + 1);
			temp.setCol(c - 11);
			if (!temp.isWasCounted()) {
				temp.setNumSurroundingMines(temp.getNumSurroundingMines() + 1);
				temp.setButtonText(temp.getNumSurroundingMines().toString());
				temp.setWasCounted(true);
			}
		}
	}

	public void getSurroundingCells(MineButton button) {
		cells.push(button);
		move(button);
		MineButton temp;
		// get everything off stack and set Text of button
		while (!cells.isEmpty()) {
			temp = cells.pop();
			int r = temp.getRow();
			int c = temp.getCol();
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLUMNS; j++) {
					if (r == i && c == j) {
						mineField[i][j].setText(mineField[i][j].getButtonText());
					}
				}
			}
			// String text = temp.getButtonText();
			// temp.setText(text);

		}
	}

	public void move(MineButton currentButton) {
		right(currentButton);
		left(currentButton);
		down(currentButton);
		up(currentButton);
	}

	public void right(MineButton currentButton) {
		MineButton tempButton = currentButton;
		if (tempButton.getCol() != COLUMNS) {
			tempButton = findButton(currentButton.getRow(), currentButton.getCol() + 1);
		}
		while (tempButton.getCol() != COLUMNS && tempButton != null) {
			if (!tempButton.isVisited()) {
				if (tempButton.getHasMine() == true) {
					break;
				} else {
					cells.push(tempButton);
				}

			}
			tempButton.setVisited(true);
			if (tempButton.getCol() == COLUMNS - 1) {
				break;
			}
			tempButton = findButton(tempButton.getRow(), tempButton.getCol() + 1);
		}

	}

	public void left(MineButton currentButton) {
		MineButton tempButton = currentButton;
		if (tempButton.getCol() != 0) {
			tempButton = findButton(currentButton.getRow(), currentButton.getCol() - 1);
		}
		while (tempButton.getCol() != 0 && tempButton != null) {
			if (!tempButton.isVisited()) {
				if (tempButton.getHasMine() == true) {
					break;
				} else {
					cells.push(tempButton);
				}

			}
			tempButton.setVisited(true);
			if (tempButton.getCol() == 0) {
				break;
			}
			tempButton = findButton(tempButton.getRow(), tempButton.getCol() - 1);
		}
	}

	public void up(MineButton currentButton) {
		MineButton tempButton = currentButton;
		if (tempButton.getRow() != 0) {
			tempButton = findButton(currentButton.getRow() - 1, currentButton.getCol());
		}
		while (tempButton.getRow() != 0 && tempButton != null) {
			if (!tempButton.isVisited()) {
				if (tempButton.getHasMine() == true) {
					break;
				} else {
					cells.push(tempButton);
				}

			}
			tempButton.setVisited(true);
			if (tempButton.getRow() == 0) {
				break;
			}
			tempButton = findButton(tempButton.getRow() - 1, tempButton.getCol());
		}
	}

	public void down(MineButton currentButton) {
		MineButton tempButton = currentButton;
		if (tempButton.getRow() != ROWS) {
			tempButton = findButton(currentButton.getRow() + 1, currentButton.getCol());
		}
		while (tempButton.getRow() != ROWS && tempButton != null) {
			if (!tempButton.isVisited()) {
				if (tempButton.getHasMine() == true) {
					break;
				} else {
					cells.push(tempButton);
				}

			}
			tempButton.setVisited(true);
			if (tempButton.getRow() == ROWS - 1) {
				break;
			}
			tempButton = findButton(tempButton.getRow() + 1, tempButton.getCol());
		}

	}

	public MineButton findButton(int row, int col) {
		MineButton button = null;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (i == row && j == col) {
					button = mineField[i][j];
					return button;
				}
			}
		}
		return button;

	}

	public static void main(String[] args) {
		JFrame frame;
		frame = new Mine();
		frame.setVisible(true);

	}
}