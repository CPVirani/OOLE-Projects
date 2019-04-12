package core.modules;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.imageio.ImageIO;

@RunWith(value = Parameterized.class)
public class FloodFillTest {

    @Parameterized.Parameter(value = 0)
    public int numberY;

    @Parameterized.Parameter(value = 1)
    public int numberX;

    @Parameterized.Parameter(value = 2)
    public int expected;

    @Parameters(name = "{index}: testGetIndex({0},{1}) = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, 21},
                {2, 2, 42},
                {8, 2, 48},
                {5, 4, 85},
                {5, 5, 105}
        });
    }
    @Test
    public void getIndex() throws Exception {
        // File img = new File("C:\\Users\\ADARSH\\sketch\\src\\splash.png");
        //BufferedImage image = ImageIO.read(img);
        BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        FloodFill fillIndex = new FloodFill(image, Color.black);
        assertEquals(expected,fillIndex.getIndex(numberY,numberX));
    }



}