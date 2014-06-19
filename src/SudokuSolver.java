import java.util.Scanner;
import java.io.*;

/**
 * The class containing behaviours for reading from and writing to a file and
 * solving the sudoku puzzle to be solved.
 * 
 * @author Dale Peters
 */
public class SudokuSolver {
	/**
	 * Holds a reference to the grid of the sudoku puzzle to be solved.
	 */
	private Grid grid = new Grid();

	/**
	 * Writes the solution of the sudoku puzzle read in to a file.
	 * 
	 * @param fileName
	 *            The name of the file to write the solution out to.
	 * @throws IOException
	 *             In the case that there was an error in writing to the file.
	 */
	public void writeToFile(String fileName) throws IOException {
		PrintWriter fileWriter = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(fileName)));
		for (int i = 0; i < grid.getGridSize(); i++) {
			if (i % 3 == 0) { // If it's the bottom cell of the subgrid
				System.out.println("------------");
			}
			for (int j = 0; j < grid.getGridSize(); j++) {
				if (j % 3 == 0) { // If it's the edge cell of the subgrid
					System.out.print("|");
				}
				if (grid.getCells()[i][j].getInitialValue() == 0) {
					/*
					 * Convert back to spaces so it saves in the same format as
					 * load in file
					 */
					fileWriter.print(' ');
				} else {
					fileWriter.print(grid.getCells()[i][j].getInitialValue());
				}
				System.out.print(grid.getCells()[i][j].getInitialValue());
			}
			fileWriter.println();
			System.out.println();
		}
		System.out.println("------------");
		fileWriter.close();
	}

	/**
	 * Reads in the file containing a representation of the sudoku problem to be
	 * solved.
	 * 
	 * @param fileName
	 *            The name of the file to read the sudoku puzzle to be solved
	 *            from.
	 * @throws FileNotFoundException
	 *             In the case that the file could not be found.
	 */
	public void readFile(String fileName) throws FileNotFoundException {
		Cell[][] cells = new Cell[grid.getGridSize()][grid.getGridSize()];
		Scanner fileReader = new Scanner(new InputStreamReader(
				new FileInputStream(fileName)));
		for (int i = 0; i < grid.getGridSize(); i++) {
			String lineFromDocument = fileReader.nextLine();
			for (int j = 0; j < grid.getGridSize(); j++) {
				/* Gets the relevant char from the string */
				char tempChar = lineFromDocument.charAt(j);
				if (tempChar == ' ') {
					/* Convert spaces to 0s for easier processing */
					tempChar = '0';
				}
				/* Convert characters to their integer equivalent */
				int charAsInt = Character.getNumericValue(tempChar);
				cells[i][j] = new Cell(charAsInt);
			}
		}
		grid.setCells(cells);
		fileReader.close();
	}

	/**
	 * Solves the sudoku puzzle read in by the <code>readFile</code> method,
	 * using "naked singles" and "hidden singles".
	 */
	public void solveGrid() {
		int cellsLeftToSolve = grid.getNumberOfUnsolvedCells();
		int lastCellsLeft = cellsLeftToSolve + 1;
		// While there was an improvement in the number of cells solved...
		while ((cellsLeftToSolve < lastCellsLeft) && (cellsLeftToSolve > 0)) {
			lastCellsLeft = cellsLeftToSolve;
			for (int checkValue = 1; checkValue <= 9; checkValue++) {
				for (int columnNumber = 0; columnNumber < grid.getGridSize(); columnNumber++) {
					grid.columnContainsValue(columnNumber, checkValue);
				}
				grid.setValueOfCells();
				for (int rowNumber = 0; rowNumber < grid.getGridSize(); rowNumber++) {
					grid.rowContainsValue(rowNumber, checkValue);
				}
				grid.setValueOfCells();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						grid.boxContainsValue(i, j, checkValue);
					}
				}
				grid.setValueOfCells();
				for (int columnNumber = 0; columnNumber < 9; columnNumber++) {
					if (grid.timesCandidateAppearsInColumn(columnNumber,
							checkValue) == 1) {
						grid.setHiddenSingleColumn(columnNumber, checkValue);
					}
				}
				for (int rowNumber = 0; rowNumber < 9; rowNumber++) {
					if (grid.timesCandidateAppearsInRow(rowNumber, checkValue) == 1) {
						grid.setHiddenSingleRow(rowNumber, checkValue);
					}
				}
				for (int boxYPosition = 0; boxYPosition < 3; boxYPosition++) {
					for (int boxXPosition = 0; boxXPosition < 3; boxXPosition++) {
						if (grid.timesCandidateAppearsInBox(boxXPosition,
								boxYPosition, checkValue) == 1) {
							grid.setHiddenSingleBox(boxXPosition, boxYPosition,
									checkValue);
						}
					}
				}
			}
			cellsLeftToSolve = grid.getNumberOfUnsolvedCells();
		}
	}

	/**
	 * Allows access to the private instance variable <code>grid</code> from
	 * other classes.
	 * 
	 * @return The sudoku grid.
	 */
	public Grid getGrid() {
		return this.grid;
	}
}
