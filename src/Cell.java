import java.util.Arrays;

/**
 * Represents a cell of the sudoku puzzle to be solved.
 * 
 * @author Dale Peters
 */
public class Cell {
	/**
	 * Holds the range of possible values that the cell could possibly be
	 * assigned as a permanent value.
	 */
	private int[] candidates = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * Holds the cell's assigned value that will be output to the solution file.
	 */
	private int initialValue;
	
	/**
	 * Creates an instance of the Cell class, representing a cell in the sudoku
	 * grid. Fills the rest of the candidates array with 0.
	 * 
	 * @param initialValue
	 *            The value of the cell read in from the file, or 0 if the value
	 *            is unknown
	 */
	public Cell(int initialValue) {
		if (initialValue != 0) {
			this.removeAllCandidates();
		}
		this.initialValue = initialValue;
	}

	/**
	 * "Removes" a specific cell's candidate (sets it to 0).
	 * 
	 * @param value
	 *            The value to "remove" from the cell's candidates.
	 */
	public void removeCandidate(int value) {
		if ((value > 0) && (value <= 9)) {
			this.candidates[value - 1] = 0;
		}
	}

	/**
	 * "Removes" all candidates of a cell (sets them to 0).
	 */
	public void removeAllCandidates() {
		Arrays.fill(this.candidates, 0);
	}

	/**
	 * Allows retrieval of a particular cell's possible values from another
	 * class.
	 * 
	 * @return The cell's candidates array.
	 */
	public int[] getCandidates() {
		return this.candidates;
	}

	/**
	 * Allows retrieval of a particular cell's assigned value from another
	 * class.
	 * 
	 * @return The cell's assigned value.
	 */
	public int getInitialValue() {
		return this.initialValue;
	}

	/**
	 * Checks if the number of candidates that aren't 0 is 1 and if so, sets the
	 * cell's permanent value to that candidate.
	 * 
	 * @return The permanent value the cell was assigned.
	 */
	public int setNakedSingle() {
		int cand = 0; // To store the integer it will assign
		if (this.getNumberOfNonZeroCandidates() == 1) {
			for (int candidate : candidates) {
				cand = candidate;
				if (candidate != 0) {
					this.setDefiniteValue(candidate);
				}
			}
		}
		return cand;
	}

	/**
	 * Assigns a particular cell with a permanent value and "removes" all the
	 * cell's candidates (sets all elements of the candidates array to 0).
	 * 
	 * @param value
	 *            The number to set to the cell's permanent value and remove
	 *            from its candidates.
	 */
	public void setDefiniteValue(int value) {
		this.initialValue = value;
		this.removeAllCandidates();
	}

	/**
	 * Counts the number of candidates the cell has that aren't zero.
	 * 
	 * @return The number of candidates that aren't zero.
	 */
	public int getNumberOfNonZeroCandidates() {
		int number = 0;
		for (int candidate : this.candidates) {
			if (candidate != 0) {
				number++;
			}
		}
		return number;
	}

	/**
	 * Determines whether a cell has been assigned a permanent value by checking
	 * if the <code>initialValue</code> is not equal to 0.
	 * 
	 * @return True if the cell's permanent value is not equal to 0.
	 */
	public boolean isSolved() {
		return (this.initialValue != 0);
	}
}