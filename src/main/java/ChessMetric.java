/**
 * Suppose you had an n by n chess board and a super piece called a kingknight.
 * Using only one move the kingknight denoted 'K' below can reach any of the
 * spaces denoted 'X' or 'L' below:
 * .......
 * ..L.L..
 * .LXXXL.
 * ..XKX..
 * .LXXXL.
 * ..L.L..
 * .......
 *
 * In other words, the kingknight can move either one space in any direction
 * (vertical, horizontal or diagonally) or can make an 'L' shaped move. An 'L'
 * shaped move involves moving 2 spaces horizontally then 1 space vertically or
 * 2 spaces vertically then 1 space horizontally. In the drawing above, the 'L'
 * shaped moves are marked with 'L's whereas the one space moves are marked with 'X's.
 * In addition, a kingknight may never jump off the board.
 *
 * Given the size of the board, the start position of the kingknight and the end
 * position of the kingknight, your method will return how many possible ways
 * there are of getting from start to end in exactly numMoves moves. start and
 * finish are int[]s each containing 2 elements. The first element will be the (0-based)
 * row position and the second will be the (0-based) column position. Rows and columns
 * will increment down and to the right respectively. The board itself will have rows
 * and columns ranging from 0 to size-1 inclusive.
 *
 * Note, two ways of getting from start to end are distinct if their respective
 * move sequences differ in any way. In addition, you are allowed to use spaces on
 * the board (including start and finish) repeatedly during a particular path from
 * start to finish. We will ensure that the total number of paths is less than or equal
 * to 2^63-1 (the upper bound for a long).
 */
public class ChessMetric {

    public static long howMany(int size, int[] start, int[] end, int numMoves){

        int[][][] movesCoordinates = new int[numMoves][size*size][2];
        long[][][] movesValues = new long[numMoves][size][size];

        for(int k=0;k<numMoves;k++){
            for(int i=0;i<size*size;i++){
                movesCoordinates[k][i][0]=-1;
            }
        }


        int m=0;
        for(int i=0;i<size;i++){
           for(int j=0;j<size;j++){
               int[] localEnd = {i,j};
               if(testMove(start,localEnd)){
                   movesValues[0][i][j]++;
                   movesCoordinates[0][m][0] = i;
                   movesCoordinates[0][m][1] = j;
                   m++;
               }
           }
        }

        for(int k=1;k<numMoves;k++){
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                   int n=0;
                   for(int l=0;l<movesCoordinates[k].length;l++){
                       if(movesCoordinates[k-1][l][0]==-1)
                           break;
                       int[] localStart = {i,j};
                       if(testMove(localStart,movesCoordinates[k][l])){
                           movesValues[k][i][j] += movesValues[k-1][movesCoordinates[k-1][l][0]][movesCoordinates[k-1][l][1]];
                           movesCoordinates[k][n][0] = i;
                           movesCoordinates[k][n][1] = j;
                           n++;
                       }
                   }
                }
            }
        }

        return movesValues[numMoves-1][end[0]][end[1]];

    }

    private static boolean testMove(int[] start, int[] end){
        return ((Math.abs((start[0]+start[1])-(end[0]+end[1]))==1)) |
                ((Math.abs(end[0]-start[0])*Math.abs(end[1]+start[1]))==1) |
        ((Math.abs(end[0]-start[0])*Math.abs(end[1]+start[1]))==2);
    }


}
