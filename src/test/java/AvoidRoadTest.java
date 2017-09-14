import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Robert on 7/17/2017.
 */
public class AvoidRoadTest {

    @Test
    public void test1() {
        int width = 6;
        int height = 6;
        String[] bad = {"0 0 0 1","6 6 5 6"};
        long result = AvoidRoads.numWays(width,height,bad);
        Assert.assertEquals("Test 1 Failed", 252L, result);
    }

    @Test
    public void test2() {
        int width = 1;
        int height = 1;
        String[] bad = {};
        long result = AvoidRoads.numWays(width,height,bad);
        Assert.assertEquals("Test 2 Failed", 2L, result);
    }

    @Test
    public void test3() {
        int width = 35;
        int height = 31;
        String[] bad = {};
        long result = AvoidRoads.numWays(width,height,bad);
        Assert.assertEquals("Test 2 Failed", 6406484391866534976L, result);
    }

    @Test
    public void test4() {
        int width = 2;
        int height = 2;
        String[] bad = {"0 0 1 0", "1 2 2 2", "1 1 2 1"};
        long result = AvoidRoads.numWays(width,height,bad);
        Assert.assertEquals("Test 2 Failed", 0L, result);
    }

}
