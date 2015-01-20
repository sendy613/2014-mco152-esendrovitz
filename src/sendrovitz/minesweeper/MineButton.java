package sendrovitz.minesweeper;

import javax.swing.JButton;

public class MineButton extends JButton {
	private int row;
	private int col;
	private boolean hasMine;
	private String buttonText;
	private boolean visited;
	private Integer numSurroundingMines;
	private boolean hasFlag;
	private boolean wasCounted;

	public MineButton() {
		this.visited = false;
		this.numSurroundingMines = 0;
		//this.buttonText = "";
		this.hasFlag = false;
		this.wasCounted=false;

	}

	public boolean isWasCounted() {
		return wasCounted;
	}

	public void setWasCounted(boolean wasCounted) {
		this.wasCounted = wasCounted;
	}

	public boolean isHasFlag() {
		return hasFlag;
	}

	public void setHasFlag(boolean hasFlag) {
		this.hasFlag = hasFlag;
	}

	public Integer getNumSurroundingMines() {
		return numSurroundingMines;
	}

	public void setNumSurroundingMines(Integer numSurroundingMines) {
		this.numSurroundingMines = numSurroundingMines;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean getHasMine() {
		return hasMine;
	}

	public void setHasMine(boolean hasMine) {
		this.hasMine = hasMine;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String text) {
		this.buttonText = text;
	}
}
