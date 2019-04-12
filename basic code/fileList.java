package ParserMain;

import java.io.File;
import java.util.ArrayList;

public class fileList {
    public String a = "C:\\Users\\ngpgo\\Desktop\\ngpgopi-gopikrishnan_narasimhaguptha_hw1-27b40f7b5d1e\\src\\main\\java\\seedu\\address";

    public int count;
    ArrayList<String> files = new ArrayList<String> ();

    public void getFileList(){

        File folder = new File(a);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files.add(listOfFiles[i].getAbsolutePath ());
            } else if (listOfFiles[i].isDirectory()) {
                a = listOfFiles[i].getAbsolutePath ();
                getFileList();
            }
        }
    }

    public ArrayList<String> returnFileList(){
        return files;
    }
}
