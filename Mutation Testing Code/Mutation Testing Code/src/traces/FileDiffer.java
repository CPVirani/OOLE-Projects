package traces;
import core.geom.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static traces.WriteFile.FileDiff;

public class FileDiffer {

    public void printdiff() throws IOException

    {
        String sCurrentLine;
        File filepathex = new File("src/execTrace.txt");
        File filepathmute=new File("src/MuataedexecTrace.txt");

        BufferedReader br1 = new BufferedReader(new FileReader(filepathex));
        BufferedReader br2 = new BufferedReader(new FileReader(filepathmute));

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        while ((sCurrentLine = br1.readLine()) != null) {
            list1.add(sCurrentLine);
        }
        while ((sCurrentLine = br2.readLine()) != null) {
            list2.add(sCurrentLine);
        }
        List<String> tmpList = new ArrayList<String>(list1);
        tmpList.removeAll(list2);
        System.out.println("content from Execution Trace");
        FileDiff.write("content from Execution Trace"+System.lineSeparator()+System.lineSeparator());
        FileDiff.flush();
        for (int i = 0; i < tmpList.size(); i++) {
            System.out.println(tmpList.get(i)); //content from test.txt which is not there in test2.txt
            FileDiff.write(tmpList.get(i)+System.lineSeparator());
            FileDiff.flush();
        }
        System.out.println();
        System.out.println();

        System.out.println("content from Mutated Trace");
        FileDiff.write(System.lineSeparator()+System.lineSeparator()+"content from Mutated Trace"+System.lineSeparator());
        FileDiff.flush();
        tmpList = list2;
        tmpList.removeAll(list1);
        for (int i = 0; i < tmpList.size(); i++) {
            System.out.println(tmpList.get(i)); //content from test2.txt which is not there in test.txt
            FileDiff.write(tmpList.get(i)+System.lineSeparator());
            FileDiff.flush();
        }
    }


}
