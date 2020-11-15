package tags.unionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
 * surrounded by 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * 
 * Example:
 * 
 * X X X X 
 * X O O X 
 * X X O X 
 * X O X X After running your function, the board should
 * be:
 * 
 * X X X X 
 * X X X X 
 * X X X X 
 * X O X X
 */
public class SurroundedRegions130 {
	public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        Queue<Integer> q = new LinkedList<>();//用来向外扩展把相连的设值
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
            	//最外圈，不会被同化的设为*
                if ((i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) && board[i][j] == 'O') {
                    board[i][j] = '*';
                    q.offer(i * board[0].length + j);
                    bfs(board, q, i, j);
                }
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {//除了*都可以同化
                board[i][j] = board[i][j] == '*' ? 'O' : 'X';
            }
        }
        
    }
	//用来向外扩展把相连的设值
    private void bfs(char[][] board, Queue<Integer> q, int row, int col) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        
        while (!q.isEmpty()) {
            int index = q.poll();
            int rowIndex = index / board[0].length;
            int colIndex = index % board[0].length;
            for(int i = 0; i < 4; i++) {
                int x = rowIndex + dx[i];
                int y = colIndex + dy[i];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                    q.offer(x * board[0].length + y);
                    board[x][y] = '*';
                }
            }
        }
    }
}
