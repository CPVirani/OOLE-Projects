package JavaGenerator;

import java.util.Random;

public class IntGenerator {

    int rand;
    int random(int p_min, int p_max)
    {
        Random rnd= new Random();
        rand = rnd.nextInt((p_max - p_min) + 1) + p_min;
       // System.out.println("min:"+p_min+"  max:"+p_max+"   no chosen="+rand);
        return rand;
    }
}
