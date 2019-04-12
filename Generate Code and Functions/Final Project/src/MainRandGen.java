import JavaGenerator.Generator;
import ConfigFileReader.ReadConfigFile;
import ProdCollection.Lists;
import ConfigFileReader.Rules;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;



public class MainRandGen {
    public String temp;
    public int productionLines=0;
    int count = 0;
    static  File configFile = new File ("src/Production_rule.properties");


    public static void main(String[] args) throws IOException{
        Properties prop = new Properties();
        OutputStream output = null;


        int i=0;
        ReadConfigFile config = new ReadConfigFile();
        config.readConfig();

        int NumberOfClasses = config.valueReturnOfNumberOfClasses();
        //System.out.println("Number of Classes : "+NumberOfClasses);

        int NumberOfLines = config.valueReturnOfNumberOfLines();
        //System.out.println("Number of Lines : "+NumberOfLines);

        int minNoOfParametersPerMethod = config.valueReturnOfminNoOfParametersPerMethod();
        String newRule="method_param_list::=\"(\")";
        if(minNoOfParametersPerMethod>0) {
            String rule = "\"(\"";
            for (int r = 1; r <= minNoOfParametersPerMethod - 1; r++) {
                rule += "typeSpec\" \"identifier\",\"";
            }
            rule += "typeSpec\" \"identifier\")\"";

             newRule = "method_param_list::=" + rule;
            //System.out.print(newRule);
        }
        try {
                PropertiesConfiguration configchange = new PropertiesConfiguration("src/Production_rule.properties");
                configchange.setProperty("20", newRule);
                configchange.save();
            }catch(ConfigurationException ex){

            }


        //System.out.println("Number of minNoOfParametersPerMethod : "+minNoOfParametersPerMethod);
        int maxNoOfMethodsPerClass = config.valueReturnOfmaxNoOfMethodsPerClass();
       // System.out.println("Number of maxNoOfMethodsPerClass : "+maxNoOfMethodsPerClass);
        int AllowArray = config.valueReturnOfAllowArray();
       // System.out.println("Number of maxNoOfMethodsPerClass : "+AllowArray);
        int NumberOfInterfaces = config.valueReturnOfNumberOfInterfaces();
        //System.out.println("Number of maxNoOfMethodsPerClass : "+NumberOfInterfaces);
        int maxAllowedMethodCalls = config.valueReturnOfmaxAllowedMethodCalls();
       // System.out.println("Number of maxNoOfMethodsPerClass : "+maxAllowedMethodCalls);
        int maxInterfacesToImplement = config.valueReturnOfmaxInterfacesToImplement();
       // System.out.println("Number of maxNoOfMethodsPerClass : "+maxInterfacesToImplement);
        int maxValueForLoop = config.valueReturnOfmaxValueForLoop();
       // System.out.println("Number of maxNoOfMethodsPerClass : "+maxValueForLoop);
        String[] AllowedTypes = config.valueReturnOfAllowedTypes();
  //System.out.println("Number of AllowedTypes : ");
        newRule = "typeSpec::=\"int\"| \"float\"| \"double\"| \"long\"| \"byte\"| \"char\"| \"short\"| \"class_name\"| \"interface_name\"";
        if (AllowedTypes.length!=0) {
            String rule = "";

        int j;
        for (j = 0; j < AllowedTypes.length - 1; j++) {
            rule += "\"" + AllowedTypes[j] + "\"| ";
        }
        rule += "\"" + AllowedTypes[j] + "\" ";
        newRule = "typeSpec::=" +rule;
        System.out.print(" " + newRule);
    }
        try {
            PropertiesConfiguration configchange = new PropertiesConfiguration("src/Production_rule.properties");
            configchange.setProperty("9", newRule);
            configchange.save();
        } catch (ConfigurationException ex) {

        }


        do {
            System.out.println("");
            MainRandGen read = new MainRandGen();
            read.readConfigfile();
            Generator generator = new Generator("class_declaration");
            generator.codeGenerator();
            i++;
        }while(i < NumberOfClasses);

    }

    public void readConfigfile(){
        try {
            // reading production lines
            BufferedReader reader = new BufferedReader(new FileReader(configFile));
            while (reader.readLine() != null) productionLines++;
            reader.close();

            FileReader configread = new FileReader(configFile);
            Properties getKeyValue = new Properties();
            getKeyValue.load(configread);

            for (int i = 1; i <= productionLines; i++) {
                temp = getKeyValue.getProperty(i + "");

                String[] lineIdentity = temp.split("::=");
                String[] rhsOfLine = lineIdentity[1].split("\\|");
                int minval = count;
                for (String s : rhsOfLine) {
                    count++;
                    Lists.prodRHS.add(s);

                }

                int maxval = count - 1;
                lineIdentity[0] = lineIdentity[0].trim();
                Lists.prodLHS.put(lineIdentity[0], new Integer[]{minval, maxval});
            }
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }


}



