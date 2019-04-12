package traces;

import java.io.FileWriter;
import java.io.IOException;
public class WriteFile {
    public  static FileWriter outFile;
    public  static  FileWriter MutatatedoutFile;
    public static FileWriter FileDiff;
    static {
        try {

            String path = "src/execTrace.txt";
                outFile = new FileWriter(path,true); // now appends
            String pathmutataed = "src/MuataedexecTrace.txt";
               MutatatedoutFile = new FileWriter(pathmutataed,true); // now appends
            String pathDiffer="src/TraceCompare.txt";
            FileDiff = new FileWriter(pathDiffer);
             }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}