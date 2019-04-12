
// Java Program to illustrate reading from FileReader
// using BufferedReader
package ConfigFileReader;
import java.util.Scanner;
import ProdCollection.Lists;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import  java.util.Arrays;

public class ReadConfigFile {
    private static int NumberOfClasses=0;
    private static int NumberOfLines=0;
    private static int minNoOfParametersPerMethod=0;
    private static int maxNoOfMethodsPerClass=0;
    private static int AllowArray=0;
    private static int NumberOfInterfaces=0;
    private static int maxAllowedMethodCalls=0;
    private static int maxInterfacesToImplement=0;
    private static int maxValueForLoop=0;
    private static String[] AllowedTypes=new String[9];


    public static void readConfig(){

        try{
            Scanner scanner = new Scanner(new File("config.txt"));


            while (scanner.hasNext()) {      // while there is another token to read
                String s = scanner.nextLine();   // checks what parameter is given as input
                String[] ConfigParameters =s.split(" ");

                //System.out.println(" "+ConfigParameters[0]);
                if (ConfigParameters[0].equals("NumberOfClasses") ){
                    String ParameterValue = ConfigParameters[1];
                    NumberOfClasses = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("NumberOfLines")){
                    String ParameterValue = ConfigParameters[1];
                    NumberOfLines = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("NoOfParametersPerMethod")){
                    String ParameterValue = ConfigParameters[1];
                    minNoOfParametersPerMethod = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("maxNoOfMethodsPerClass")){
                    String ParameterValue = ConfigParameters[1];
                    maxNoOfMethodsPerClass = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("AllowArray")) {
                    String ParameterValue = ConfigParameters[1];
                    if (ParameterValue.equals("yes")) {
                        AllowArray = 0;
                    } else {
                        AllowArray = 1;
                    }
                }
                else if(ConfigParameters[0].equals("NumberOfInterfaces")){
                    String ParameterValue = ConfigParameters[1];
                    NumberOfInterfaces = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("maxAllowedMethodCalls")){
                    String ParameterValue = ConfigParameters[1];
                    maxAllowedMethodCalls = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("maxInterfacesToImplement")){
                    String ParameterValue = ConfigParameters[1];
                    maxInterfacesToImplement = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("maxValueForLoop")){
                    String ParameterValue = ConfigParameters[1];
                    maxValueForLoop = Integer.parseInt(ParameterValue);
                }
                else if(ConfigParameters[0].equals("AllowedTypes")){
                   // int i=1;
                   // while(ConfigParameters[i]!=null){
                  //  for(int i=0;i<ConfigParameters.length-1;i++) {
                  //      String a= ConfigParameters[i+1];
                  //      AllowedTypes[i] = a;
                  //  }
                    AllowedTypes = Arrays.copyOfRange(ConfigParameters, 1 , ConfigParameters.length);
                }

            }

            scanner.close();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public static int valueReturnOfNumberOfClasses() {

        return NumberOfClasses;
    }
    public static int valueReturnOfNumberOfLines() {

        return NumberOfLines;
    }
    public static int valueReturnOfminNoOfParametersPerMethod() {

        return minNoOfParametersPerMethod;
    }
    public static int valueReturnOfmaxNoOfMethodsPerClass() {

        return maxNoOfMethodsPerClass;
    }
    public static int valueReturnOfAllowArray() {

        return AllowArray;
    }
    public static int valueReturnOfNumberOfInterfaces() {

        return NumberOfInterfaces;
    }
    public static int valueReturnOfmaxAllowedMethodCalls() {

        return maxAllowedMethodCalls;
    }
    public static int valueReturnOfmaxInterfacesToImplement() {

        return maxInterfacesToImplement;
    }
    public static int valueReturnOfmaxValueForLoop() {

        return maxValueForLoop;
    }
    public static String[] valueReturnOfAllowedTypes() {

        return AllowedTypes;
    }
}