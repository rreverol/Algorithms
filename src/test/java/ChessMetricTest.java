import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Robert on 10/03/2017.
 */
public class ChessMetricTest {

    @Test
    public void test1(){
        int[] start = {0,0};
        int[] end = {1,0};
        long result = ChessMetric.howMany(3,start,end,1);
        Assert.assertEquals("Test 1 Failed",1L,result);
    }

    @Test
    public void test2(){
        int[] start = {0,0};
        int[] end = {1,2};
        long result = ChessMetric.howMany(3,start,end,1);
        Assert.assertEquals("Test 2 Failed",1L,result);
    }

    @Test
    public void test3(){
        int[] start = {0,0};
        int[] end = {2,2};
        long result = ChessMetric.howMany(3,start,end,1);
        Assert.assertEquals("Test 3 Failed",0L,result);
    }

    @Test
    public void test4(){
        int[] start = {0,0};
        int[] end = {0,0};
        long result = ChessMetric.howMany(3,start,end,2);
        Assert.assertEquals("Test 4 Failed",5L,result);
    }

    @Test
    public void test5(){
        int[] start = {0,0};
        int[] end = {0,99};
        long result = ChessMetric.howMany(100,start,end,10);
        Assert.assertEquals("Test 5 Failed",243097320072600L,result);
    }

}
