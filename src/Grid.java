/**
 * Represents the grid of the sudoku puzzle to be solved.
 * 
 * @author Dale Peters
 */
public class Grid {
	/**
	 * Holds a representation of the cells in the sudoku grid to be solved.
	 */
	private Cell[][] cells;

	/**
	 * Holds the dimensions of the grid.
	 */
	private int gridSize = 9;

	/**
	 * Creates an instance of Grid, representing the sudoku grid to be solved.
	 */
	public Grid() {
		cells = new Cell[gridSize][gridSize];
	}

	/**
	 * Allows the setting of the private instance variable <code>cells</code>
	 * from other classes.
	 * 
	 * @param cells
	 */
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	/**
	 * Allows retrieval of the private instance variable <code>cells</code> from
	 * other classes.
	 * 
	 * @return The 2D array of cells that represents the sudoku grid to be
	 *         solved.
	 */
	public Cell[][] getCells() {
		return this.cells;
	}

	/**
	 * Counts the number of times a candidate for a cell appears in a column of
	 * the grid.
	 * 
	 * @param columnNumber
	 *            The index of the column to look down.
	 * @param candidateValue
	 *            The candidate to look for.
	 * @return The number of times a candidate appears in a column.
	 */
	public int timesCandidateAppearsInColumn(int columnNumber,
			int candidateValue) {
		int appearances = 0;
		for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
			if (cells[rowNumber][columnNumber].getCandidates()[candidateValue - 1] != 0) {
				appearances++;
			}
		}
		return appearances;
	}

	/**
	 * Counts the number of times a candidate for a cell appears in a row of the
	 * grid.
	 * 
	 * @param rowNumber
	 *            The index of the row to look across.
	 * @param candidateValue
	 *            The candidate to look for.
	 * @return The number of times a candidate for a cell appears in a row.
	 */
	public int timesCandidateAppearsInRow(int rowNumber, int candidateValue) {
		int appearances = 0;
		for (int columnNumber = 0; columnNumber < gridSize; columnNumber++) {
			if (cells[rowNumber][columnNumber].getCandidates()[candidateValue - 1] != 0) {
				appearances++;
			}
		}
		return appearances;
	}

	/**
	 * Counts the number of times a candidate for a cell appears in a 3x3 box of
	 * the grid.
	 * 
	 * @param boxX
	 *            The x-coordinate of the 3x3 box to look in for a candidate.
	 * @param boxY
	 *            The y-coordinate of the 3x3 box to look in for a candidate.
	 * @param candidateValue
	 *            The candidate to look for.
	 * @return The number of times a candidate for a cell appears in a 3x3 box.
	 */
	public int timesCandidateAppearsInBox(int boxX, int boxY, int candidateValue) {
		int appearances = 0;
		for (int x = boxX * 3; x < (boxX * 3) + 3; x++) {
			for (int y = boxY * 3; y < (boxY * 3) + 3; y++) { // x and y swapped
				if (cells[y][x].getCandidates()[candidateValue - 1] != 0) {
					appearances++;
				}
			}
		}
		return appearances;
	}

	/**
	 * Checks to see if a specific row has the specified definite value
	 * contained in it. If it does, the method will remove it from the
	 * candidates of the cell and the candidates of the column, row and box the
	 * cell is in.
	 * 
	 * @param rowNumber
	 *            The number of the row to check for the value.
	 * @param value
	 *            The value to check the specified row for.
	 */
	public void rowContainsValue(int rowNumber, int value) {
		for (int columnNumber = 0; columnNumber < gridSize; columnNumber++) {
			if (value == cells[rowNumber][columnNumber].getInitialValue()) {
				this.cells[rowNumber][columnNumber].removeCandidate(value);
				this.removeRowCandidates(rowNumber, value);
				this.removeColumnCandidates(columnNumber, value);
				this.removeBoxCandidates(columnNumber / 3, rowNumber / 3, value);
			}
		}
	}

	/**
	 * Checks to see if a specific column has the specified definite value
	 * contained in it. If it does, the method will remove it from the
	 * candidates of the cell and the candidates of the column, row and box the
	 * cell is in.
	 * 
	 * @param columnNumber
	 *            The number of the column to check for the value.
	 * @param value
	 *            The value to check the specified column for.
	 */
	public void columnContainsValue(int columnNumber, int value) {
		for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
			if (value == cells[rowNumber][columnNumber].getInitialValue()) {
				this.cells[rowNumber][columnNumber].removeCandidate(value);
				this.removeColumnCandidates(columnNumber, value);
				this.removeRowCandidates(rowNumber, value);
				this.removeBoxCandidates(columnNumber / 3, rowNumber / 3, value);
			}
		}
	}

	/**
	 * Checks to see if a specific 3x3 box has the specified definite value
	 * contained in it. If it does, the method will remove it from the
	 * candidates of the cell and the candidates of the column, row and box the
	 * cell is in.
	 * 
	 * @param boxX
	 *            The x-coordinate of the 3x3 box (sub-grid) to check for the
	 *            value.
	 * @param boxY
	 *            The y-coordinate of the 3x3 box (sub-grid) to check for the
	 *            value.
	 * @param value
	 *            The value to check the specified 3x3 box (sub-grid) for.
	 */
	public void boxContainsValue(int boxX, int boxY, int value) {
		for (int x = boxX * 3; x < (boxX * 3) + 3; x++) {
			for (int y = boxY * 3; y < (boxY * 3) + 3; y++) {
				if (value == cells[x][y].getInitialValue() && x == boxX
						&& y == boxY) {
					this.cells[x][y].removeCandidate(value);
					this.removeBoxCandidates(boxX, boxY, value);
					this.removeColumnCandidates(y, value);
					this.removeRowCandidates(x, value);
				}
			}
		}
	}

	/**
	 * Allows retrieval of the private instance variable <code>gridSize</code>
	 * from other classes.
	 * 
	 * @return The dimensions of the grid.
	 */
	public int getGridSize() {
		return this.gridSize;
	}

	/**
	 * Loops through all cells in the grid and runs the method
	 * <code>setNakedSingle</code> (from the <code>Cell</code> class) on them.
	 * Also removes the value it sets the cell to from the candidates of the
	 * cells of the column, row and box.
	 */
	public void setValueOfCells() {
		for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
			for (int columnNumber = 0; columnNumber < gridSize; columnNumber++) {
				int value = this.cells[rowNumber][columnNumber].setNakedSingle();
				if (value > 0) {
					this.removeRowCandidates(rowNumber, value);
					this.removeColumnCandidates(columnNumber, value);
					this.removeBoxCandidates(columnNumber / 3, rowNumber / 3, value);
				}
			}
		}
	}

	/**
	 * Removes the specified candidate from an entire column's cells' candidate
	 * values array.
	 * 
	 * @param columnNumber
	 *            The number of the column the candidate will be removed from.
	 * @param value
	 *            The value to be removed from the column's cell's candidate
	 *            values array.
	 */
	public void removeColumnCandidates(int columnNumber, int value) {
		for (int rowNumber = 0; rowNumber < gridSize; rowNumber++) {
			this.cells[rowNumber][columnNumber].removeCandidate(value);
		}
	}

	/**
	 * Removes the specified candidate from an entire row's cells' candidate
	 * values array.
	 * 
	 * @param rowNumber
	 *            The number of the row the candidate will be removed from.
	 * @param value
	 *            The value to be removed from the row's cell's candidate values
	 *            array.
	 */
	public void removeRowCandidates(int rowNumber, int value) {
		for (int columnNumber = 0; columnNumber < gridSize; columnNumber++) {
			this.cells[rowNumber][columnNumber].removeCandidate(value);
		}
	}

	/**
	 * Removes the specified candidate from an entire 3x3 box's (sub-grid's)
	 * cells' candidate values array.
	 * 
	 * @param boxX
	 *            The number of the 3x3 box (sub-grid) along the x axis the
	 *            candidates will be removed from.
	 * 
	 * @param boxY
	 *            The number of the 3x3 box (sub-grid) along the y axis the
	 *            candidates will be removed from.
	 * @param value
	 *            The value that is being removed from the 3x3 box's
	 *            (sub-grid's) cells' candidate values array.
	 */
	public void removeBoxCandidates(int boxX, int boxY, int value) {
		for (int rowNumber = boxY * 3; rowNumber < (boxY * 3) + 3; rowNumber++) {
			for (int columnNumber = boxX * 3; columnNumber < (boxX * 3) + 3; columnNumber++) {
				this.cells[rowNumber][columnNumber].removeCandidate(value);
			}
		}
	}

	/**
	 * Checks if each cell in the specified row has the specified candidate. If
	 * it does, it will set it to the cell's definite value, remove all of that
	 * cell's candidates and removes the number it was set to from the
	 * candidates of other cells in the same row, column and box.
	 * 
	 * @param rowNumber
	 *            The number of the row to look across for the specified
	 *            candidate.
	 * @param value
	 *            The specified candidate the method will look for.
	 */
	public void setHiddenSingleRow(int rowNumber, int value) {
		for (int columnNumber = 0; columnNumber < 9; columnNumber++) {
			if (this.cells[rowNumber][columnNumber].getCandidates()[value - 1] != 0) {
				this.cells[rowNumber][columnNumber].setDefiniteValue(value);
				this.cells[rowNumber][columnNumber].removeAllCandidates();
				this.removeRowCandidates(rowNumber, value);
				this.removeColumnCandidates(columnNumber, value);
				this.removeBoxCandidates(columnNumber / 3, rowNumber / 3, value);
			}
		}
	}

	/**
	 * Checks if each cell in the specified column has the specified candidate.
	 * If it does, it will set it to the cell's definite value, remove all of
	 * that cell's candidates and removes the number it was set to from the
	 * candidates of other cells in the same row, column and box.
	 * 
	 * @param columnNumber
	 *            The number of the column to look down for the specified
	 *            candidate.
	 * @param value
	 *            The specified candidate the method will look for.
	 */
	public void setHiddenSingleColumn(int columnNumber, int value) {
		for (int row = 0; row < 9; row++) {
			if (this.cells[row][columnNumber].getCandidates()[value - 1] != 0) {
				this.cells[row][columnNumber].setDefiniteValue(value);
				this.cells[row][columnNumber].removeAllCandidates();
				this.removeColumnCandidates(columnNumber, value);
				this.removeRowCandidates(row, value);
				this.removeBoxCandidates(columnNumber / 3, row / 3, value);
			}
		}
	}

	/**
	 * Checks if each cell in the specified 3x3 box has the specified candidate.
	 * If it does, it will set it to the cell's definite value, remove all of
	 * that cell's candidates and removes the number it was set to from the
	 * candidates of other cells in the same row, column and box.
	 * 
	 * @param boxX
	 *            The x-coordinate of the 3x3 box to look for the specified
	 *            candidate in.
	 * @param boxY
	 *            The y-coordinate of the 3x3 box to look for the specified
	 *            candidate in.
	 * @param value
	 *            The specified candidate to look in the 3x3 box for.
	 */
	public void setHiddenSingleBox(int boxX, int boxY, int value) {
		for (int rowNumber = boxY * 3; rowNumber < (boxY * 3) + 3; rowNumber++) {
			for (int columnNumber = boxX * 3; columnNumber < (boxX * 3) + 3; columnNumber++) {
				if (this.cells[rowNumber][columnNumber].getCandidates()[value - 1] != 0) {
					this.cells[rowNumber][columnNumber].setDefiniteValue(value);
					this.cells[rowNumber][columnNumber].removeAllCandidates();
					this.removeColumnCandidates(columnNumber, value);
					this.removeRowCandidates(rowNumber, value);
					this.removeBoxCandidates(columnNumber / 3, rowNumber / 3,
							value);
				}
			}
		}
	}

	/**
	 * Counts the number of cells in the grid that don't have the "solved"
	 * status.
	 * 
	 * @return The number of cells that haven't yet been solved.
	 */
	public int getNumberOfUnsolvedCells() {
		int numberOfUnsolvedCells = 0;
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				if (!cells[row][column].isSolved()) {
					numberOfUnsolvedCells++;
				}
			}
		}
		return numberOfUnsolvedCells;
	}
}
