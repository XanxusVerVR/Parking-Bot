package pojotest;

import java.util.List;
import model.Calculate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AreaTest {

    public AreaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //判斷myLatitude與myLongitude這個座標是否在成大1公里的範圍內
    @Test
    public void judgmentPositionWhetherArea() {
        double myLatitude = 22.995307;
        double myLongitude = 120.214746;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(true, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea2() {
        double myLatitude = 23.012961;
        double myLongitude = 120.232568;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea3() {
        double myLatitude = 22.992165;
        double myLongitude = 120.203623;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea4() {
        double myLatitude = 22.998519;
        double myLongitude = 120.200530;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea5() {
        double myLatitude = 22.9985257;
        double myLongitude = 120.2343994;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea6() {
        double myLatitude = 22.991316;
        double myLongitude = 120.212857;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea7() {
        double myLatitude = 22.991614;
        double myLongitude = 120.212551;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(true, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea8() {
        double myLatitude = 22.998998;
        double myLongitude = 120.222084;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(true, isAreaWithin);
    }

    //此經緯度地圖位置https://bit.ly/2LYTjdV
    @Test
    public void judgmentPositionWhetherArea9() {
        double myLatitude = 22.998558;
        double myLongitude = 120.204960;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    //此經緯度地圖位置https://bit.ly/2yoV81I
    @Test
    public void judgmentPositionWhetherArea10() {
        double myLatitude = 22.996285;
        double myLongitude = 120.205794;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea11() {
        double myLatitude = 22.999480;
        double myLongitude = 120.203569;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }

    @Test
    public void judgmentPositionWhetherArea12() {
        double myLatitude = 22.993808;
        double myLongitude = 120.207063;
        List<Double> list = Calculate.getMaxMinLatitudeLongitude(23.0003887, 120.2136187, 1);
        boolean isAreaWithin;
        if (myLongitude >= list.get(2) && myLongitude <= list.get(3) && myLatitude >= list.get(0) && myLatitude <= list.get(1)) {
            isAreaWithin = true;
        } else {
            isAreaWithin = false;
        }
        assertEquals(false, isAreaWithin);
    }
}
