package ParserMain;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jface.text.BadLocationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.io.FileUtils.readFileToString;

public class ParseTree {

    public static void main(String[] args) throws CoreException, IOException, BadLocationException{
        ASTParse();
    }

    public static void ASTParse() throws IOException, BadLocationException, JavaModelException {
        ASTParser parser=ASTParser.newParser ( AST.JLS8 );
        parser.setKind ( ASTParser.K_COMPILATION_UNIT );
        parser.setResolveBindings ( true );
        Map options=JavaCore.getOptions ();
        parser.setCompilerOptions ( options );
        String unitName="test.java";
        parser.setUnitName ( unitName );
        String[] sources={"C:\\Users\\ngpgo\\Desktop\\ngpgopi-gopikrishnan_narasimhaguptha_hw1-27b40f7b5d1e\\src\\main\\java\\seedu\\address"};
        String[] classpath={"C:\\Program Files\\Java\\jdk1.8.0_144\\src.zip"};
        parser.setEnvironment ( classpath, sources, new String[]{"UTF-8"}, true );

        ArrayList<String> listofFiles = new ArrayList<String> ();
        fileList fl = new fileList ();
        fl.getFileList ();
        listofFiles = fl.returnFileList ();

        for (int i = 0; i < listofFiles.size () ; i++) {
            System.out.println ( "FileName: " + listofFiles.get ( i ) );
            parser.setSource ( readFileToString (new File (listofFiles.get(i))).toCharArray ());
            final CompilationUnit cu=(CompilationUnit) parser.createAST ( null );
            nodeVisitor nv=new nodeVisitor ();

            nv.getCU ( cu );

            ArrayList<Statement> stmList=nv.visitnodesStatements ();
            ArrayList<SimpleName> SNList=nv.visitnodesSN ();
            ArrayList<MethodInvocation> methodList=nv.visitnodesMethod ();

            astRewrite ar=new astRewrite ();
            ar.getCU ( cu );

            ar.reWrite ( stmList, SNList, methodList,listofFiles.get(i));
        }
    }
}