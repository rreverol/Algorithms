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

        long[][][] numWaysValues = new long[size][size][numMoves+1];
        int[][][] positionMovements = new int[size*size][KINGKNIGHT_MOVEMENTS.length][2];

        for(int i=0;i<numWaysValues.length;i++){
            for(int j=0;j<numWaysValues[i].length;j++){
                for(int k=0;k<numWaysValues[i][j].length;k++){
                    numWaysValues[i][j][k] = -1;
                }
            }
        }

        for(int i=0;i<positionMovements.length;i++){
            for(int j=0;j<positionMovements[i].length;j++){
                positionMovements[i][j][0] = -1;
            }
        }


        return howMany(start[0],start[1],end[0],end[1],numMoves,positionMovements,numWaysValues);

    }

    private static int[][] move(int x, int y, int[][][] positionMovements){
        int size = (int) Math.sqrt(positionMovements.length);
        if (positionMovements[x*size+y][0][0]>-1) {
            return positionMovements[x*size+y];
        }
        int boardSize = (int) Math.sqrt(positionMovements.length);
        int xDiff = x - KINGKNIGHT_REFERENCE[0];
        int yDiff = y - KINGKNIGHT_REFERENCE[1];
        int n=0;
        for(int i=0;i<KINGKNIGHT_MOVEMENTS.length;i++){
            int x1 = KINGKNIGHT_MOVEMENTS[i][0] + xDiff;
            int y1 = KINGKNIGHT_MOVEMENTS[i][1] + yDiff;
            if(x1>=0&&x<boardSize&&y1>=0&&y1<boardSize){
                int[] currentCoordinate = {x1,y1};
                positionMovements[x*size+y][n] = currentCoordinate;
                n++;
            }
        }
        return positionMovements[x*size+y];
    }

    private static long howMany(int sx, int sy, int x, int y, int numMoves,int[][][] positionMovements, long[][][] numWaysValues ){

        if(numWaysValues[x][y][numMoves]!=-1)
            return numWaysValues[x][y][numMoves];

        long numWays = 0;
        int[][] positions = move(x,y,positionMovements);
        if(numMoves>1){
            for(int i=0;i<positions.length;i++){
                if(positions[i][0]==-1)
                    break;
                numWays+= howMany(sx,sy,positions[i][0],positions[i][1],numMoves-1,positionMovements,numWaysValues);
            }
        }else if(numMoves==1){
            for(int i=0;i<positions.length;i++){
               if(positions[i][0]==sx && positions[i][1]==sy){
                   numWays=1;
                   break;
               }
            }
        }
        numWaysValues[x][y][numMoves] = numWays;
        return numWays;
    }

}