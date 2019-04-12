package MutationInjection;


import Parser.ASTParserTree;
import javassist.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static Parser.ASTVisitMethodDeclaration.fnName;
import static Parser.ASTVisitMethodDeclaration.fncontent;

public class ROR {
    public void relationalOperatorChange(String clname,String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/geom/Line.java");
        ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Replacing < operator with >");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("<",">");
            src=src.replace("x1","$1");
            src=src.replace("x2","$3");
            src=src.replace("y1","$2");
            src=src.replace("y2","$4");
            src=src.replace("outFile.write(op);","logger(op);");
            src=src.replace("outFile.flush();","//outFile.flush();");
            src=src.replace("with expr $3 > $1","- expr changed to x2 > x1");

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
            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point
            cArg[1] = Point.class; //Second argument is of *object* type Point

            Point x = new Point(1,1);
            Point y = new Point(5,5);

            Object a1 = c1.getDeclaredConstructor(cArg).newInstance(x,y);
            Method tc1 =  c1.getMethod(name,new Class[]{int.class,int.class,int.class,int.class});
            tc1.invoke(a1,4,5,2,2);
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

