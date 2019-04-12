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

public class AOR {

    public void arithOperatorChangeBinary(String clname, String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/modules/FloodFill.java");
       ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Replacing Arithemetic operator + to -");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("y) + x","$2) - $1");
            src=src.replace("outFile.write(op);","logger(op);");
            src=src.replace("outFile.flush();","//outFile.flush();");
            src=src.replace("Inside function getIndex compute index with 20 * y + x","Inside function getIndex 20 * y +x replaced with 20 * y - x");

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
            cArg[0] = BufferedImage.class; //First argument is of *object* type BufferedImage
            cArg[1] = Color.class; //Second argument is of *object* type Color

            BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
            Color clr=Color.black;

            Object a1 = c1.getDeclaredConstructor(cArg).newInstance(image,clr);
            Method tc1 =  c1.getMethod(name,new Class[]{int.class,int.class});
            tc1.invoke(a1,1,5);
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

    public void arithOperatorChangeShorthand(String clname, String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.setName("core.modules.FloodFill2");
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/modules/FloodFill.java");
        ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Replacing Arithemetic operator -- by ++");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("=x","=$1");
            src=src.replace("filL--","filL++");
            src=src.replace("outFile.write(op);","logger(op);");
            src=src.replace("outFile.flush();","//outFile.flush();");
            src=src.replace("Inside function getIndex compute filL with filL--","Inside function getIndex filL-- replaced with filL++");

        }
        else
        {
            System.out.println("error");
            return;
        }

        method.setBody(src);
        clazz.writeFile();
        Class c2=  clazz.toClass();
        try {
            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = BufferedImage.class; //First argument is of *object* type BufferedImage
            cArg[1] = Color.class; //Second argument is of *object* type Color

            BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
            Color clr=Color.black;

            Object a2 = c2.getDeclaredConstructor(cArg).newInstance(image,clr);

            Method tc2 =  c2.getMethod(name,new Class[]{int.class,int.class});
            tc2.invoke(a2,4,5);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

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
