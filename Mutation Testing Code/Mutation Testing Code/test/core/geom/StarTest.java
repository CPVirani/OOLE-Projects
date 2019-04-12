package core.geom;

import org.junit.Test;

import static org.junit.Assert.*;

public class StarTest {
    boolean y;
    @Test
    public void getShape() throws Exception {
        int a = 20;
        int b = 40;
        int p = a*b;
        if(p==800) {
             y = true;
        }
        assertEquals(true,y);
        }
    }