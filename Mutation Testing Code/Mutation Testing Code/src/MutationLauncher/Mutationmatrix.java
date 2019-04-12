package MutationLauncher;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;


public class Mutationmatrix {

    public static String[][] mutation_matrix=new String[50][2];//initializing the mutation matrix
    public void readTrace() throws IOException {
        try {

            File f = new File("src/execTrace.txt");
            List<String> lines = FileUtils.readLines(f, "UTF-8");
            Boolean apply;
            int counti=0;

            for (int i=0;i<lines.size();i++) {
                int countj = 0;
                if (lines.get(i).contains("compute")) {
                    apply = checkAOR(lines.get(i), counti, countj);
                    if (apply == true) {
                        counti++;

                    }

                } else if (lines.get(i).contains("if condition")&&lines.get(i).contains("with expr"))
                {
                        apply = checkROR_COR_SOR(lines.get(i), counti, countj);
                        if (apply == true) {
                            counti++;

                        }

                }
                else if(lines.get(i).contains("assigned")||lines.get(i).contains("Logical")){

                    apply = checkLOR(lines.get(i), counti, countj);
                    if (apply == true) {
                        counti++;
                }
            }else if (lines.get(i).contains("calculate")) {
                    apply = checkASR(lines.get(i), counti, countj);
                    if (apply == true) {
                        counti++;

                    }
                }
                else if (lines.get(i).contains("print Stmnt")) {
                    apply = SDL(lines.get(i), counti, countj);
                    if (apply == true) {
                        counti++;

                    }
                }

            }

            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getPackage(String line)
    {

        String token[]=line.split(" ");
        int count=0;
        for (String temp: token)
        {
            count++;
            if(count==4) {

                return temp;
            }
        }
        return null;
    }
    public String getClass(String line)
    {
        String token[]=line.split(" ");

        int count=0;
        for (String temp: token)
        {
            count++;
            if(count==6)
                return  temp;
        }
        return null;
    }
    public String getFunction(String line)
    {
        String token[]=line.split(" ");
        int count=0;
        for (String temp: token)
        {
            count++;
            if(count==9)
                return  temp;
        }
        return null;
    }
    public Boolean checkLOR(String line, int i,int j) {
        String parentPackageName = getPackage(line);
        String parentClassName = getClass(line);
        String functionName=getFunction(line);
        mutation_matrix[i][j++] = parentPackageName+ "." +parentClassName+" "+functionName;
        if(line.contains("&")||line.contains("|")||line.contains("^"))
           {
               mutation_matrix[i][j]="LOR";
               return true;
           }
           else if (line.contains("~"))
        {
            mutation_matrix[i][j]="LOD";
            return true;
        }
        return false;
    }
    public Boolean checkROR_COR_SOR(String line, int i,int j){
        String parentPackageName = getPackage(line);
        String parentClassName = getClass(line);
        String functionName=getFunction(line);
        mutation_matrix[i][j++] = parentPackageName+ "." +parentClassName+" "+functionName;
        if(line.contains(">")||line.contains("<")||line.contains(">=")||line.contains("<=")||line.contains("==")||line.contains("!="))
        {
            if((line.contains("&&"))||(line.contains("||")))
            {
                mutation_matrix[i][j]="COR";
                return true;
            }
            else if(line.contains(">>")||line.contains("<<"))
            {
                mutation_matrix[i][j]="SOR";
                return true;
            }
            else {
                mutation_matrix[i][j]="ROR";
                return true;
            }

        }
        else if(line.contains("&&")||line.contains("||")||line.contains("^"))
        {
            mutation_matrix[i][j]="COR";
            return true;
        }
        else if (line.contains("!"))
        {
            mutation_matrix[i][j]="COD";
            return true;
        }
        return  false;


    }

   public Boolean checkAOR(String line, int i,int j){
       String parentPackageName = getPackage(line);
       String parentClassName = getClass(line);
       String functionName=getFunction(line);
       mutation_matrix[i][j++] = parentPackageName+ "." +parentClassName+" "+functionName;
       if(line.contains("+"))
               if(line.contains("++"))
                  mutation_matrix[i][j] = "AORs";

                else
                   mutation_matrix[i][j] = "AORb";
       else if(line.contains("-"))
                if(line.contains("--"))
                    mutation_matrix[i][j] = "AORs";
                else
                    mutation_matrix[i][j] = "AORb";
       return true;



   }
    public Boolean checkASR(String line, int i,int j){
        String parentPackageName = getPackage(line);
        String parentClassName = getClass(line);
        String functionName=getFunction(line);
        mutation_matrix[i][j++] = parentPackageName+ "." +parentClassName+" "+functionName;
        if(line.contains("+=")||line.contains("-=")||line.contains("/=")||line.contains("*=")||line.contains("%=")||line.contains("&=")) {
            mutation_matrix[i][j] = "ASR";
        }

        return true;
    }
    public Boolean SDL(String line, int i,int j){
        String parentPackageName = getPackage(line);
        String parentClassName = getClass(line);
        String functionName=getFunction(line);
        mutation_matrix[i][j++] = parentPackageName+ "." +parentClassName+" "+functionName;
        mutation_matrix[i][j] = "SDL";


        return true;
    }
}