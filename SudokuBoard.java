import java.util.ArrayList;

/** Generate a 9*9 sudoku board.**/
public class SudokuBoard {
	
	/** instance variable that represents the sudoku board  **/
	private int[][] board;
	
	private ArrayList<Integer>[][] lists = (ArrayList<Integer>[][]) new ArrayList[9][9];
	
	
	public SudokuBoard() {
		board = new int[9][9];
		lists = (ArrayList<Integer>[][]) new ArrayList[9][9];
		for (int x=0; x<9; x++) {
			for (int y=0; y<9; y++) {
				lists[x][y] = new ArrayList<Integer>();
			}
		}
	}
	
	/**
	 * fill the board with 81 numbers that follows the law of sudoku.
	 */
	public void setBoard() {
		int count = 0;
		int count1 = 0;
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				//System.out.println(count++);
				System.out.println(java.util.Arrays.toString(board[0]));
				System.out.println(java.util.Arrays.toString(board[1]));
				System.out.println(java.util.Arrays.toString(board[2]));
				System.out.println(java.util.Arrays.toString(board[3]));
				System.out.println(java.util.Arrays.toString(board[4]));
				System.out.println(java.util.Arrays.toString(board[5]));
				System.out.println(java.util.Arrays.toString(board[6]));
				System.out.println(java.util.Arrays.toString(board[7]));
				System.out.println(java.util.Arrays.toString(board[8]));
				backTracking(board, x, y);
			}
		}
	}
	
	
	public void backTracking(int[][] board, int x, int y) {
		int minNum = 1;
		int maxNum = 9;
		int random;
		if (y == -1) {return ;}
		ArrayList<Integer> visited = new ArrayList<Integer>(lists[x][y]);
		do {
			do {
				random = minNum + (int)(Math.random() * ((maxNum - minNum) + 1));
				if (visited.size() == 9) {
					if (x == 0) {
						backTracking(board, 8, y-1);
						break;
					} else {backTracking(board, x-1, y); break;}
				}
				if (!visited.contains(random)) {
					board[x][y] = random;
					visited.add(random);
					break;
				}
			} while (visited.contains(random));
		} while (!condition(board, x, y, board[x][y]));
		lists[x][y].add(board[x][y]);
	}
		
		
	
	
	/**
	 * return the sudoku board
	 * @return sudoku board
	 */
	public int[][] getBoard() {
		return board;
	}
	
	/**
	 * check if the value of board[x][y] follows the rule of sudoku
	 * @param board the sudoku board
	 * @param x the x-coordinate 
	 * @param y the y-coordinate
	 * @param num the value of board[x][y]
	 * @return true if follows the condition, false otherwise
	 */
	public boolean condition(int[][] board, int  x, int y, int num) {
		// Test if there is a same number in the row.
		for (int m = 0; m < board.length; m++) {
			if (m != x && board[m][y] == num) {
				return false;
			}
		}
		// Test if there is a same number in the column.
		for (int n = 0; n < board[0].length; n++) {
			if (n != y && board[x][n] == num) {
				return false;
			}	
		}
		// Test if there is a same number in the area.
		int [] bound = bound(x, y);
		for (int m = bound[0]; m <= bound[1]; m++) {
			for (int n = bound[2]; n <= bound[3]; n++) {
				if ((m != x && n != y) && board[m][n] == num) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * return the bound of x and y given the coordinate.
	 * @param x x-coordinate 
	 * @param y y-coordinate 
	 * @return an array that contains the bound of x and y
	 */
	public int[] bound(int x, int y) {
		int[] bound = new int[4];
		int lowBoundX, hiBoundX, lowBoundY, hiBoundY;
		if (x >= 0 && x < 3 ) {
			lowBoundX = 0;
			hiBoundX = 2;
		} else if (x >= 3 && x < 6 ) {
			lowBoundX = 3;
			hiBoundX = 5;
		} else {
			lowBoundX = 6;
			hiBoundX = 8;
		}
		
		if (y >= 0 && y < 3 ) {
			lowBoundY = 0;
			hiBoundY = 2;
		} else if (y >= 3 && y < 6 ) {
			lowBoundY = 3;
			hiBoundY = 5;
		} else {
			lowBoundY = 6;
			hiBoundY = 8;
		}
		bound[0] = lowBoundX;
		bound[1] = hiBoundX;
		bound[2] = lowBoundY;
		bound[3] = hiBoundY;
		return bound;
		
	}

}
