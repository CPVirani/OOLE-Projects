package MutationInjection;

import Parser.ASTParserTree;
import javassist.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static Parser.ASTVisitMethodDeclaration.fnName;
import static Parser.ASTVisitMethodDeclaration.fncontent;

public class SOR {
    public void ShiftOperatorChange(String clname,String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/geom/Star.java");
        ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Replacing bit wise operator << with >>");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("<<",">>");
            src=src.replace("p1","$1");
            src=src.replace("outFile.write(op);","logger(op);");
            src=src.replace("outFile.flush();","//outFile.flush();");
            src=src.replace("with expr","- expr changed to");

        }
        else
        {
            System.out.println("error");
            return;
        }

        method.setBody(src);
        clazz.writeFile();
        Class c1=  clazz.toClass();
        try {


            Object a1 = c1.newInstance();
            Method tc1 =  c1.getMethod(name,new Class[]{int.class});
            tc1.invoke(a1,4);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        clazz.detach();

    }

    public static String readFileToString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            //System.out.println(numRead);
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}

