package core.geom;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import static traces.WriteFile.MutatatedoutFile;
import static traces.WriteFile.outFile;

/**
 *
 * @author Mithusayel Murmu
 */
public class Star {
    
    private int cx;
    private int cy;
    private int arms;
    
    private float outRad;
    private float inRad;
    
    private GeneralPath path;
    
    public Star() {
        cx = 4;
        cy = 2;
        this.arms = 2;
        
        outRad = 5;
        inRad = 2;
        
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        createStar();
    }
    
    private void createStar() {
        double angle = Math.PI / arms;

        for (int i = 0; i < 2 * arms; i++) {
            double r = (i & 1) == 0 ? outRad : inRad;
            
            double x = cx + Math.cos(i * angle) * r;
            double y = cy + Math.sin(i * angle) * r;
            
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        
        path.closePath();
    }
    
    public Shape getShape() {
        return path;
    }
    public void getPoint(int p1) {
        int c,point =p1;
        try {

            String op=System.lineSeparator()+System.lineSeparator()+"Package Name: package core.geom Class: Star "+"Inside function getPoint at line 66 if condition with expr (point<<8)>0"+System.lineSeparator();
            outFile.write(op);
            outFile.flush();
        }
        catch (java.io.IOException er) {er.printStackTrace();}

        if((c=(point<<8))>0)

            try {

                String op="Package Name: package core.geom Class: Star "+"at line 67 if block executed. point<<8= "+c+System.lineSeparator();
                outFile.write(op);
                outFile.flush();
            }catch (java.io.IOException er) {er.printStackTrace();}
        else
        {

            try {

                String op="Package Name: package core.geom Class: Star "+"at line 78 else block executed. point<<8= "+c+System.lineSeparator();
                outFile.write(op);
                outFile.flush();
            }catch (java.io.IOException er) {er.printStackTrace();}}
    }

    public void logger(String c){
        try {

            MutatatedoutFile.write(c);
            MutatatedoutFile.flush();
        }
        catch (java.io.IOException er) {er.printStackTrace();}


    }
}
