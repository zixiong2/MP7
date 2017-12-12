import java.util.Scanner;
/** A driver class that operate the sudokuBoard class **/
public class DriverClass {
	public static void main(String[] args) {
		SudokuBoard s = new SudokuBoard();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("e for Easy, h for Hard, type your level of Sudoku Board: ");
		String difficulty = scanner.nextLine();
		if (difficulty.equals("e") || difficulty.equals("E")) {
			s.getBoard(1);
		} else if (difficulty.equals("h") || difficulty.equals("H")) {
			s.getBoard(2);
		} 
		
		System.out.print("type a for answer: ");
		String anwser = scanner.nextLine();
		if (anwser.equals("a") || anwser.equals("A")) {
			s.getBoard(0);
		} 
		
		
		
	}

}
