import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Robert on 3/15/2017.
 */
public class BadNeighborsTest {

    @Test
    public void test1() {

        int[] donations = {10, 3, 2, 5, 7, 8};
        int result = BadNeighbors.maxDonations(donations);
        Assert.assertEquals("Test 1 Failed", 19, result);

    }

    @Test
    public void test2() {

        int[] donations = { 11, 15 };
        int result = BadNeighbors.maxDonations(donations);
        Assert.assertEquals("Test 2 Failed", 15, result);

    }

    @Test
    public void test3() {

        int[] donations = { 7, 7, 7, 7, 7, 7, 7 };
        int result = BadNeighbors.maxDonations(donations);
        Assert.assertEquals("Test 3 Failed", 21, result);

    }

    @Test
    public void test4() {

        int[] donations = { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 };
        int result = BadNeighbors.maxDonations(donations);
        Assert.assertEquals("Test 4 Failed", 16, result);

    }

    @Test
    public void test5() {

        int[] donations = { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
                6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
                52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 };
        int result = BadNeighbors.maxDonations(donations);
        Assert.assertEquals("Test 5 Failed", 2926, result);

    }

}