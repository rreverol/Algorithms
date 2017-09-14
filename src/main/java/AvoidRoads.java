/**
 * In the city, roads are arranged in a grid pattern.
 * Each point on the grid represents a corner where two blocks meet.
 * The points are connected by line segments which represent the various
 * street blocks. Using the cartesian coordinate system, we can assign a pair
 * of integers to each corner as shown below.
 */
public class AvoidRoads {

    private static int LEFT_CLOSED = 1;
    private static int BOTTOM_CLOSED = 2;
    private static int BOTH_OPEN = 3;


    public static long numWays(int width, int height, String[] bad){

        long[][] paths = new long[width+1][height+1];
        int[][] openPaths = new int[width+1][height+1];

        for(int i=0;i<openPaths.length;i++){
            for(int j=0;j<openPaths[i].length;j++){
                openPaths[i][j] = BOTH_OPEN;
            }
        }

        for(int i=0;i<bad.length;i++){
            String[] badCoordinates = bad[i].split(" ");
            int x1 = Integer.parseInt(badCoordinates[0]);
            int y1 = Integer.parseInt(badCoordinates[1]);
            int x2 = Integer.parseInt(badCoordinates[2]);
            int y2 = Integer.parseInt(badCoordinates[3]);
            if(x1!=x2)
                openPaths[x1>x2?x1:x2][y1] = LEFT_CLOSED;
            if(y1!=y2)
                openPaths[x1][y1>y2?y1:y2] = BOTTOM_CLOSED;
        }

        for(int i=0;i<paths.length;i++){
            for(int j=0;j<paths[i].length;j++){
               int lefPath = openPaths[i][j]==LEFT_CLOSED?0:1;
               int bottomPath = openPaths[i][j]==BOTTOM_CLOSED?0:1;
               if(i==0&&j==0)
                   paths[i][j]=1;
               else
                 paths[i][j] = (i>0?paths[i-1][j]*lefPath:0) + (j>0?paths[i][j-1]*bottomPath:0);
            }
        }

        return paths[width][height];
    }

}
