import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * The JUnit test case for testing that all methods in the Cell class work as expected.
 * 
 * @author Dale Peters
 */
public class GridTest {

	private SudokuSolver test;

	@Before
	public void instantiateGrid() throws FileNotFoundException {
		test = new SudokuSolver();
		test.readFile("guardian.sud");
	}

	@Test
	public void testSetAndGetCells() {
		Grid testGrid = new Grid();
		Cell[][] expectedCells = new Cell[9][9];
		testGrid.setCells(expectedCells);
		Cell[][] actualCells = testGrid.getCells();
		assertArrayEquals("The expected and actual cells[][] are not the same",
				expectedCells, actualCells);
	}

	@Test
	public void testNumberOfTimesCandidateAppearsInRow() {
		int actual = test.getGrid().timesCandidateAppearsInRow(0, 3);
		int expected = 7;
		assertEquals(
				"The number of times the candidate appears in the row isn't correct",
				expected, actual);
	}

	@Test
	public void testNumberOfTimesCandidateAppearsInColumn() {
		int actual = test.getGrid().timesCandidateAppearsInColumn(0, 3);
		int expected = 7;
		assertEquals(
				"The number of times the candidate appears in the column isn't correct",
				expected, actual);
	}

	@Test
	public void testNumberOfTimesCandidateAppearsInBox() {
		int actual = test.getGrid().timesCandidateAppearsInBox(0, 0, 2);
		int expected = 6;
		assertEquals(
				"The number of times the candidate appears in the box isn't correct",
				expected, actual);
	}

	@Test
	public void testGetGridSize() {
		int actual = test.getGrid().getGridSize();
		int expected = 9;
		assertEquals(
				"The actual grid size and expected grid size aren't equal",
				expected, actual);
	}

	@Test
	public void testNumberOfUnsolvedCells() {
		int expected = 57;
		int actual = test.getGrid().getNumberOfUnsolvedCells();
		assertEquals("The number of unsolved cells isn't correct", expected, actual);
	}
}
