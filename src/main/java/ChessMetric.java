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

    private static final int[] KINGKNIGHT_REFERENCE = {2,2};
    private static final int[][] KINGKNIGHT_MOVEMENTS = {{0,1},{0,3},{1,0},{1,1},{1,2},{1,3},{1,4},
            {2,1},{2,3},{3,0},{3,1},{3,2},{3,3},{3,4},{4,1},{4,3}};


    public static long howMany(int size, int[] start, int[] end, int numMoves){

        int[][][] movesCoordinates = new int[numMoves][size*size][2];
        long[][][] movesValues = new long[numMoves][size][size];
        int[][][] positionMovements1 = new int[size*size][KINGKNIGHT_MOVEMENTS.length][2];


        for(int i=0;i<size*size;i++){
            for(int j=0;j<KINGKNIGHT_MOVEMENTS.length;j++){
                positionMovements1[i][j][0] = -1;
            }
        }

        movesCoordinates[0] = move(start,positionMovements1);
        for(int i=0;i<movesCoordinates[0].length;i++){
            if(movesCoordinates[0][i][0]==-1)
                break;
            movesValues[0][movesCoordinates[0][i][0]][movesCoordinates[0][i][1]] = 1;
        }
        for(int i=1;i<numMoves;i++){
            int n=0;
            for(int j=0;j<movesCoordinates[i].length;j++){
                if(movesCoordinates[i-1][j][0]==-1)
                    break;
                int[][] currentMoves = move(movesCoordinates[i-1][j],positionMovements1);
                for(int k=0;k<currentMoves.length;k++){
                    if(currentMoves[k][0]==-1)
                        break;
                    if(!containsCoordinates(movesCoordinates[i],currentMoves[k])) {
                        movesCoordinates[i][n] = currentMoves[k];
                        n++;
                    }
                    movesValues[i][currentMoves[k][0]][currentMoves[k][1]] +=
                            movesValues[i-1][movesCoordinates[i-1][j][0]][movesCoordinates[i-1][j][1]];
                }
            }

        }

        return movesValues[numMoves-1][end[0]][end[1]];

    }

    private static boolean containsCoordinates( int[][] coordinates, int[] coordinate){
        boolean result = false;
        for(int i=0;i<coordinates.length;i++){
            if(coordinates[i][0]==coordinate[0] && coordinates[i][1]==coordinate[1]){
                result=true;
                break;
            }
        }
        return  result;
    }

    private static int[][] move(int[] start, int[][][][] positionMovements){

        if (positionMovements[start[0]][start[1]][0][0]>-1) {
            return positionMovements[start[0]][start[1]];
        }
        int boardSize = positionMovements.length;
        int xDiff = start[0] - KINGKNIGHT_REFERENCE[0];
        int yDiff = start[1] - KINGKNIGHT_REFERENCE[1];
        int n=0;
        for(int i=0;i<KINGKNIGHT_MOVEMENTS.length;i++){
            int x = KINGKNIGHT_MOVEMENTS[i][0] + xDiff;
            int y = KINGKNIGHT_MOVEMENTS[i][1] + yDiff;
            if(x>=0&&x<boardSize&&y>=0&&y<boardSize){
                int[] currentCoordinate = {x,y};
                positionMovements[start[0]][start[1]][n] = currentCoordinate;
                n++;
            }
        }
        return positionMovements[start[0]][start[1]];
    }

    private static int[][] move(int[] start, int[][][] positionMovements1){
        int size = (int) Math.sqrt(positionMovements1.length);
        if (positionMovements1[start[0]*size+start[1]][0][0]>-1) {
            return positionMovements1[start[0]*size+start[1]];
        }
        int boardSize = (int) Math.sqrt(positionMovements1.length);
        int xDiff = start[0] - KINGKNIGHT_REFERENCE[0];
        int yDiff = start[1] - KINGKNIGHT_REFERENCE[1];
        int n=0;
        for(int i=0;i<KINGKNIGHT_MOVEMENTS.length;i++){
            int x = KINGKNIGHT_MOVEMENTS[i][0] + xDiff;
            int y = KINGKNIGHT_MOVEMENTS[i][1] + yDiff;
            if(x>=0&&x<boardSize&&y>=0&&y<boardSize){
                int[] currentCoordinate = {x,y};
                positionMovements1[start[0]*size+start[1]][n] = currentCoordinate;
                n++;
            }
        }
        return positionMovements1[start[0]*size+start[1]];
    }

}
