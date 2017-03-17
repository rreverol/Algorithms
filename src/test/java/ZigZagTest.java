import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Robert on 3/15/2017.
 */
public class ZigZagTest {

    @Test
    public void test1(){

       int[] sequence = { 1, 7, 4, 9, 2, 5 };
       int result = ZigZag.longestZigZag(sequence);
       Assert.assertEquals( "Test 1 Failed",6, result);

    }

    @Test
    public void test2(){

        int[] sequence = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
        int result = ZigZag.longestZigZag(sequence);
        Assert.assertEquals( "Test 2 Failed",7, result);

    }


    @Test
    public void test3(){

        int[] sequence = { 44 };
        int result = ZigZag.longestZigZag(sequence);
        Assert.assertEquals( "Test 3 Failed",1, result);

    }

    @Test
    public void test4(){

        int[] sequence = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int result = ZigZag.longestZigZag(sequence);
        Assert.assertEquals( "Test 4 Failed",2, result);

    }


    @Test
    public void test5(){

        int[] sequence = { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
        int result = ZigZag.longestZigZag(sequence);
        Assert.assertEquals( "Test 5 Failed",8, result);

    }

    @Test
    public void test6(){

        int[] sequence = { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244 };
        int result = ZigZag.longestZigZag(sequence);
        Assert.assertEquals( "Test 6 Failed",36, result);

    }


}
