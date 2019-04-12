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

public class ASR {
    public void assignopreplacer(String clname, String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/geom/Curve.java");
        ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Replacing shorthand Assignment operator += with -=");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("=x","=$1");
            src=src.replace("checktol+=4","checktol-=4");
            src=src.replace("compute checktol with checktol+=4","checktol+=4 replaced with checktol-=4");
            src=src.replace("outFile.write(op);","logger(op);");
            src=src.replace("outFile.flush();","//outFile.flush();");
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
            cArg[0] = Point.class; //First argument is of *object* type BufferedImage
            cArg[1] = Point.class; //Second argument is of *object* type Color

            Point start = new Point(1,2);
            Point end = new Point(3,4);

            Object a1 = c1.getDeclaredConstructor(cArg).newInstance(start,end);
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
