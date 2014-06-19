import java.io.IOException;
import java.util.Scanner;

/**
 * The main class of the application, where the sudoku solver is ran from.
 * 	
 * @author Dale Peters
 */
public class Main {

	/**
	 * @param args Program arguments
	 */
	public static void main(String args[]) throws IOException {
		SudokuSolver solver = new SudokuSolver();
		Scanner userInputReader = new Scanner(System.in);
		System.out.println("Enter the name of the file you wish to read in:");
		String readFileName = userInputReader.nextLine();
		try{
			solver.readFile(readFileName);
			solver.solveGrid();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("Enter the name of the file you wish to save to:");
		String writeFileName = userInputReader.nextLine();
		System.out.println("Here is the solution to the problem you loaded in:");
		solver.writeToFile(writeFileName);
		userInputReader.close();
	}
}
