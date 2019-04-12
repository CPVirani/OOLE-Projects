package MutationLauncher;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;
import traces.FileDiffer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static MutationLauncher.Mutationmatrix.mutation_matrix;

public class TestCases {
    ExecutorService es= Executors.newFixedThreadPool(10);

    public void Testcase1() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.modules.FloodFill");
        es.execute(new Runnable() {
            public void run() {
                try {
                    Class[] cArg = new Class[2]; //Our constructor has 2 arguments
                    cArg[0] = BufferedImage.class; //First argument is of *object* type BufferedImage
                    cArg[1] = Color.class; //Second argument is of *object* type Color

                    BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
                    Color clr=Color.black;

                    Object a1 = origin.getDeclaredConstructor(cArg).newInstance(image,clr);
                    Method tc1 =  origin.getMethod("getIndex",new Class[]{int.class,int.class});
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

            }
        });
        es.shutdown();
        es.awaitTermination(24L, TimeUnit.HOURS);
    }

    public void Testcase2() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.modules.FloodFill");

        try {
            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = BufferedImage.class; //First argument is of *object* type BufferedImage
            cArg[1] = Color.class; //Second argument is of *object* type Color

            BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
            Color clr=Color.black;


            //classToLoad.getDeclaredConstructor(cArg).newInstance(l, s, i);
            Object a2 = origin.getDeclaredConstructor(cArg).newInstance(image,clr);
            Method tc2 =  origin.getMethod("fillRanges",new Class[]{int.class,int.class});
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

    public void Testcase3() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.Line");

        try {
            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point
            cArg[1] = Point.class; //Second argument is of *object* type Point

            Point x = new Point(1,1);
            Point y = new Point(5,5);


            Object a3 = origin.getDeclaredConstructor(cArg).newInstance(x,y);
            Method tc3 =  origin.getMethod("Resize",new Class[]{int.class,int.class,int.class,int.class});
            tc3.invoke(a3,4,5,2,2);
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
    public void TestCase4() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.FreeShape");

        try {
            Class[] cArg = new Class[1]; //Our constructor has 1 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point

            Point x = new Point(1,1);


            Object a3 = origin.getDeclaredConstructor(cArg).newInstance(x);
            Method tc3 =  origin.getMethod("RectRotate",new Class[]{int.class,int.class});
            tc3.invoke(a3,4,0);
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

    public void TestCase5() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.FreeShape");

        try {
            Class[] cArg = new Class[1]; //Our constructor has 1 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point

            Point x = new Point(1,1);


            Object a3 = origin.getDeclaredConstructor(cArg).newInstance(x);
            Method tc3 =  origin.getMethod("Rotate",new Class[]{int.class,int.class,int.class,int.class,boolean.class});
            tc3.invoke(a3,4,0,5,2,true);
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
    public void Testcase6() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.Oval");

        try {
            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = Point.class; //First argument is of *object* type Point
            cArg[1] = Point.class; //Second argument is of *object* type Point

            Point x = new Point(1,1);
            Point y = new Point(5,5);


            Object a3 = origin.getDeclaredConstructor(cArg).newInstance(x,y);
            Method tc3 =  origin.getMethod("checkTolerance",new Class[]{int.class});
            tc3.invoke(a3,245);

            Method tc4 =  origin.getMethod("BufferFill",new Class[]{int.class});
            tc4.invoke(a3,245);


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

    public void Testcase7() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.Curve");

        try {
            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = Point.class; //First argument is of *object* type BufferedImage
            cArg[1] = Point.class; //Second argument is of *object* type Color

            Point start = new Point(1,2);
            Point end = new Point(3,4);

            Object a1 = origin.getDeclaredConstructor(cArg).newInstance(start,end);
            Method tc1 =  origin.getMethod("checktolerator",new Class[]{int.class});
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


    }
    public void Testcase8() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.Star");

        try {


            Object a1 = origin.newInstance();
            Method tc1 =  origin.getMethod("getPoint",new Class[]{int.class});
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


    }
    public void Testcase9() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        Loader cl = new Loader(pool);
        Class origin = cl.loadClass("core.geom.Rect");

        try {

            Class[] cArg = new Class[2]; //Our constructor has 2 arguments
            cArg[0] = Point.class; //First argument is of *object* type BufferedImage
            cArg[1] = Point.class; //Second argument is of *object* type Color

            Point start = new Point(1,2);
            Point end = new Point(3,4);
            Object a1 = origin.getDeclaredConstructor(cArg).newInstance(start,end);;
            Method tc1 =  origin.getMethod("RectReAllign");
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


    }

    public void testcaseexecute() throws IOException, InstantiationException, InvocationTargetException, NoSuchMethodException, InterruptedException, IllegalAccessException, CannotCompileException, NotFoundException, ClassNotFoundException {
        File file1=new  File("src/execTrace.txt");
        File file2=new  File("src/MuataedexecTrace.txt");
        if(file1.exists())
        {
            file1.delete();
            file2.delete();
            }
            else
        {
            System.out.println("Error");
        }

        Testcase1();
        Testcase2();
        Testcase3();
        TestCase4();
        TestCase5();
        Testcase6();
        Testcase7();
        Testcase8();
        Testcase9();

    }

    public static void main(String []agrs) throws IOException, InstantiationException, InvocationTargetException, NoSuchMethodException, InterruptedException, IllegalAccessException, CannotCompileException, NotFoundException, ClassNotFoundException {
        TestCases test=new TestCases();
        test.testcaseexecute();
        Mutationmatrix mutatecode=new Mutationmatrix();
        mutatecode.readTrace();
        new TestCasesMutatewithAssist();
        FileDiffer differ = new FileDiffer();
        differ.printdiff();

    }

}




