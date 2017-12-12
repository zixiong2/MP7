/** Generate a 4*4 sudoku board.**/
public class SudokuBoard {
	
	/** instance variable that represents the 4*4 sudoku board **/
	private int[][] board;
	
	/** instance variable that represents the 4*4 sudoku board puzzle**/
	private int[][] blankBoard;
	
	
	
	/**
	 * Constructor 
	 */
	public SudokuBoard() {
		board = new int[4][4];
		blankBoard = new int[4][4];
	}
	
	/**
	 * fill the board with 16 numbers that follows the law of sudoku.
	 * @param board the sudoku board
	 * @param x the x coordinate on sudoku board
	 * @param y the y coordinate on sudoku board
	 * @return true if board is filled with all 16 numbers, false otherwise.
	 */
	public boolean fillBoard(int[][] board, int x, int y) {
		// base case: reach the end.
		if (board[3][3] != 0) {
			return true;
		}
		for (int i = 0; i < 4; i++) {
			// generate a random number.
			int fill = random(i);
			
			// check if the random number break the law of sudoku.
			if (condition(board, x, y, fill)) {
				int nextX = (x+1) % 4;
				int nextY = y;
				if (nextX == 0) { 
					nextY++;
				}
				// fill the current the slot with the number.
				board[x][y] = fill;
				if (fillBoard(board, nextX, nextY)) {
					return true;
				} else { 
					// start from beginning.
					fillBoard(board, 0, 0);
				}
			}
		}
		return false;
	}
		
		
	/**
	 * print out the sudoku board, or the sudoku board puzzle.
	 * @param x determine which kind of board should be printed. 0 for the board answer.
	 * 1 for the easy board puzzle. 2 for the hard board puzzle.
	 */
	public void getBoard(int x) {
		if (x == 0) {
			// print the answer.
			System.out.println(java.util.Arrays.toString(board[0]));
			System.out.println(java.util.Arrays.toString(board[1]));
			System.out.println(java.util.Arrays.toString(board[2]));
			System.out.println(java.util.Arrays.toString(board[3]));
		}
		if (fillBoard(board, 0, 0) && x == 1) {
			//remove 4 numbers(easy puzzle).
			remove(1);
			// print the easy puzzle.
			System.out.println(java.util.Arrays.toString(blankBoard[0]));
			System.out.println(java.util.Arrays.toString(blankBoard[1]));
			System.out.println(java.util.Arrays.toString(blankBoard[2]));
			System.out.println(java.util.Arrays.toString(blankBoard[3]));
		}
		if (fillBoard(board, 0, 0) && x == 2) {
			//remove 6 numbers(easy puzzle).
			remove(2);
			// print the hard puzzle.
			System.out.println(java.util.Arrays.toString(blankBoard[0]));
			System.out.println(java.util.Arrays.toString(blankBoard[1]));
			System.out.println(java.util.Arrays.toString(blankBoard[2]));
			System.out.println(java.util.Arrays.toString(blankBoard[3]));
		}
		return;
	}
	
	/**
	 * remove the 4 or 6 random numbers from the board depending on the difficulty value.
	 * @param diff represents the difficulty of the puzzle. 1 for easy, 2 for hard.
	 */
	public void remove(int diff) {
		// create a deep copy of board to represent the board puzzle.
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				blankBoard[x][y] = board[x][y];
			}
		}
		int i = 0 + (int)(Math.random() * 2);
		int j = 2 + (int)(Math.random() * 2);
		int m = 0 + (int)(Math.random() * 2);
		int n = 2 + (int)(Math.random() * 2);
		// remove 4 random numbers, one from each box.
		blankBoard[i][m] = 0;
		blankBoard[i][n] = 0;
		blankBoard[j][n] = 0;
		blankBoard[j][m] = 0;
		// remove 2 more numbers. 
		if (diff == 2) {
			if (i != m) {
				blankBoard[i][i] = 0;
			} else if (j != n) {
				blankBoard[i][j] = 0;
			} else if (i == 0) {
				blankBoard[1][1] = 0;
			} else {
				blankBoard[0][0] = 0;
			}
			
			if (j != n) {
				blankBoard[j][j] = 0;
			} else if (i != m) {
				blankBoard[j][i] = 0;
			} else if (j == 2){
				blankBoard[3][3] = 0;
			} else {
				blankBoard[2][2] = 0;
			}
		}
		
		
		
	}
	
	/**
	 * Generate a random value from the range 1 to 4.
	 * @param m the index of the randomNum array.
	 * @return a value from the randomNum array.
	 */
	public int random(int m) {
		int[] randomNum = new int[4];
		for (int i = 0; i < 4; i++) {
			randomNum[i] = i + 1;
		}
		// shuffle the array 20 times to get a random order num array.
		for (int j = 0; j < 20; j++) {
			int a = (int)(Math.random() * 4);
			int b = (int)(Math.random() * 4);
			// swap
			int n = randomNum[a];
			randomNum[a] = randomNum[b];
			randomNum[b] = n;
		}
		return randomNum[m];
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
		// Test if there is a same number in the box.
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
		if (x >= 0 && x < 2 ) {
			lowBoundX = 0;
			hiBoundX = 1;
		} else {
			lowBoundX = 2;
			hiBoundX = 3;
		}
		
		if (y >= 0 && y < 2 ) {
			lowBoundY = 0;
			hiBoundY = 1;
		} else {
			lowBoundY = 2;
			hiBoundY = 3;
		}
		bound[0] = lowBoundX;
		bound[1] = hiBoundX;
		bound[2] = lowBoundY;
		bound[3] = hiBoundY;
		return bound;
		
	}
	
}
