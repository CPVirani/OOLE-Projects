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

public class DeleteInjection {
    public void  StatementDeLetion(String clname, String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/geom/Rect.java");
        ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Deleteing Print Statement");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("System.out.println(\"Test for SDL\")","");
            src=src.replace("print Stmnt","print Stmnt deleted");
            src=src.replace("outFile.write(op);","logger(op);");
            src=src.replace("outFile.flush();","//outFile.flush();");
        }
        else
        {
            System.out.println("error");
            return;
        }
        System.out.println(src);
        method.setBody(src);
        clazz.writeFile();
        Class c1=  clazz.toClass();
        try {
            Class[] cArg = new Class[2]; //Our constructor has 1 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point
            cArg[1] = Point.class; //Second argument is of *object* type Color

            Point x = new Point(1,2);

            Point y = new Point(1,1);

            Object a1 = c1.getDeclaredConstructor(cArg).newInstance(x,y);
            Method tc1 =  c1.getMethod(name);
            tc1.invoke(a1);
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

    public void OperatorDeLetion(String clname, String name) throws NotFoundException, IOException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.get(clname);
        clazz.setName(clname+"2");
        clazz.defrost();

        CtMethod method=clazz.getDeclaredMethod(name);
        fnName=name;
        String  src=readFileToString("src/core/geom/FreeShape.java");
        ASTParserTree parser=new ASTParserTree(src);
        System.out.println("Deleting Conditional Operator !");
        System.out.println();

        if(fncontent!=null)
        {

            src=fncontent;
            src=src.replace("(!","(");
            src=src.replace("rotn","$5");
            src=src.replace("dx","$1");
            src=src.replace("dy","$2");
            src=src.replace("cx","$3");
            src=src.replace("cy","$4");
            src=src.replace("with expr !rot","- expr !no_rotation_apply replaced with no_rotation_apply");
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
        Class c2=  clazz.toClass();
        try {
            Class[] cArg = new Class[1]; //Our constructor has 1 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point

            Point x = new Point(1,1);

            Object a2 = c2.getDeclaredConstructor(cArg).newInstance(x);

            Method tc2 =  c2.getMethod(name,new Class[]{int.class,int.class,int.class,int.class,boolean.class});
            tc2.invoke(a2,4,0,5,2,true);
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
