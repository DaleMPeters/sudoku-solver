import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The JUnit test case for testing that all methods in the <code>Cell</code>
 * class work as expected.
 * 
 * @author Dale Peters
 * 
 */
public class CellTest {

	@Test
	public void testCellConstructorWithInitialValueNotZero() {
		Cell testCell = new Cell(5);
		int[] expectedCandidates = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] actualCandidates = testCell.getCandidates();
		int expectedInitialValue = 5;
		int actualInitialValue = testCell.getInitialValue();
		assertArrayEquals("The expected and actual candidates are not equal",
				expectedCandidates, actualCandidates);
		assertEquals("The expected and actual intial value are not equal",
				expectedInitialValue, actualInitialValue);
	}

	@Test
	public void testCellConstructorWithInitialValueZero() {
		Cell testCell = new Cell(0);
		int expectedInitialValue = 0;
		int actualInitialValue = testCell.getInitialValue();
		assertEquals("The expected and actual initial value are not equal",
				expectedInitialValue, actualInitialValue);
	}

	@Test
	public void testRemovalOfCandidateWithValueGreaterThanZero() {
		Cell testCell = new Cell(0);
		testCell.removeCandidate(5);
		int[] expectedCandidates = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int[] actualCandidates = testCell.getCandidates();
		assertArrayEquals(
				"The expected and actual candidates arrays are not equal",
				expectedCandidates, actualCandidates);
	}

	@Test
	public void testRemovalOfCandidatesWithValueEqualToZero() {
		Cell testCell = new Cell(0);
		testCell.removeCandidate(0);
		int[] expectedCandidates = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] actualCandidates = testCell.getCandidates();
		assertArrayEquals(
				"The expected and actual candidates arrays are not equal",
				expectedCandidates, actualCandidates);

	}

	@Test
	public void testRemovalOfCandidatesWithValueLessThanZero() {
		Cell testCell = new Cell(0);
		testCell.removeCandidate(-1);
		int[] expectedCandidates = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] actualCandidates = testCell.getCandidates();
		assertArrayEquals(
				"The expected and actual candidates arrays are not equal",
				expectedCandidates, actualCandidates);
	}

	@Test
	public void testRemovalOfCandidatesWithValueGreaterThanNine() {
		Cell testCell = new Cell(0);
		testCell.removeCandidate(10);
		int[] expectedCandidates = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] actualCandidates = testCell.getCandidates();
		assertArrayEquals(
				"The expected and actual candidates arrays are not equal",
				expectedCandidates, actualCandidates);
	}

	@Test
	public void testRemoveAllCandidates() {
		Cell testCell = new Cell(0);
		testCell.removeAllCandidates();
		int[] expectedCandidates = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] actualCandidates = testCell.getCandidates();
		assertArrayEquals(
				"The expected and actual candidates arrays are not equal",
				expectedCandidates, actualCandidates);
	}

	@Test
	public void testGetCandidates() {
		Cell testCell = new Cell(0);
		int[] actualCandidates = testCell.getCandidates();
		int[] expectedCandidates = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		assertArrayEquals(
				"The expected and actual candidates arrays are not equal",
				expectedCandidates, actualCandidates);
	}

	@Test
	public void testGetInitialValueWithZero() {
		Cell testCell = new Cell(0);
		int expectedInitialValue = 0;
		int actualInitialValue = testCell.getInitialValue();
		assertEquals("The expected and initial initial values are not equal",
				expectedInitialValue, actualInitialValue);
	}

	@Test
	public void testGetInitialValueWithGreaterThanZero() {
		Cell testCell = new Cell(5);
		int expectedInitialValue = 5;
		int actualInitialValue = testCell.getInitialValue();
		assertEquals("The expected and initial initial values are not equal",
				expectedInitialValue, actualInitialValue);
	}

	@Test
	public void testGetNumberOfNonZeroCandidatesAllZeros() {
		Cell testCell = new Cell(0);
		assertEquals(
				"There number of non zero candidates returned is not correct",
				9, testCell.getNumberOfNonZeroCandidates());
	}

	@Test
	public void testGetNumberOfNonZerosInitialValueNotZero() {
		Cell testCell = new Cell(0);
		testCell.removeCandidate(6);
		assertEquals(
				"The number of non zero candidates returned is not correct", 8,
				testCell.getNumberOfNonZeroCandidates());
	}

	@Test
	public void testSettingNakedSingle() {
		Cell testCell = new Cell(0);
		for (int i = 0; i <= 8; i++) {
			testCell.removeCandidate(i);
		}
		int expectedReturnValue = 9;
		int actualReturnValue = testCell.setNakedSingle();
		assertEquals("The number returned was not correct",
				expectedReturnValue, actualReturnValue);
		int actualDefiniteValue = testCell.getInitialValue();
		assertEquals("The number that was assigned isn't as expected",
				expectedReturnValue, actualDefiniteValue);
	}

	@Test
	public void settingDefiniteValue() {
		Cell testCell = new Cell(0);
		testCell.setDefiniteValue(4);
		int[] expectedCandidates = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] actualCandidates = testCell.getCandidates();
		int expectedDefiniteValue = 4;
		int actualDefiniteValue = testCell.getInitialValue();
		assertArrayEquals("The expected and actual candidates aren't equal",
				expectedCandidates, actualCandidates);
		assertEquals(
				"The expected and actual definite values are not the same",
				expectedDefiniteValue, actualDefiniteValue);
	}

	@Test
	public void testIsSolvedReturnsTrue() {
		Cell testCell = new Cell(8);
		assertTrue("The cell does not have the \"solved\" status",
				testCell.isSolved());
	}

	@Test
	public void testIsSolvedReturnsFalse() {
		Cell testCell = new Cell(0);
		assertFalse("The cell has the \"solved\" status and shouldn't have",
				testCell.isSolved());
	}
}
