package ParserMain;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;

public class astRewrite {

    CompilationUnit cu;

    public void getCU(CompilationUnit cu){
        this.cu = cu;
    }

    public void reWrite(ArrayList<Statement> stmList, ArrayList<SimpleName> SNList, ArrayList<MethodInvocation> methodList , String filename) throws BadLocationException, IOException, JavaModelException {
        ASTRewrite astrewrite = ASTRewrite.create(cu.getAST());
        int linenumber;
        int count = 0;
        SimpleName sn=null;
        for ( int i =0 ; i < stmList.size () ; i++) {
            try{
                Statement a=stmList.get ( i );
                linenumber=(cu.getLineNumber ( a.getStartPosition () ));
                String n1="TemplateClass.instrum(" + "\"" + "LineNumber: " + "\"" + "," + "\""+ linenumber + "\"" +", " + "\""+ "Type: " + "\"" + "," + "\"" +  a.getClass ().getName ().toString () + "\"";
                ASTNode sp1=a.getParent ();
                String spp=sp1.getClass ().getName ().toString ();

                while (spp != "org.eclipse.jdt.core.dom.MethodDeclaration") {
                    sp1=sp1.getParent ();
                    spp=sp1.getClass ().getName ().toString ();
                }

                MethodDeclaration md=(MethodDeclaration) sp1;
                TypeDeclaration td=(TypeDeclaration) md.getParent ();

                if (md != null){
                    n1=n1 + ", " + "\"" + "Method: " + "\"" +","+ "\"" + md.getName () + "\"";
                }

                if (td != null){
                    n1 = n1 + ", " + "\"" + "Class: " +"\"" + "," + "\"" + td.getName ()+ "\"";
                }

                for (int j=0; j < SNList.size (); j++) {
                    sn = SNList.get (j);
                    if (cu.getLineNumber (sn.getStartPosition ()) == linenumber) {
                        for (int k=0; k < methodList.size (); k++) {
                            MethodInvocation mi=methodList.get ( k );
                            if (cu.getLineNumber(mi.getStartPosition ()) == linenumber) {
                                count = 1;
                                break;
                            }
                            else {
                                count =0;
                            }
                        }
                        if ( count == 0){
                            n1 = n1 + ", " + "\""+ "VariableName: " + "\"" +","+ "\"" + sn.getIdentifier () + "\"" ;}
                    }
                }

                n1 = n1 + ");";

                TextElement newtext=cu.getAST ().newTextElement ();
                newtext.setText ( n1 );
                ListRewrite lrw=astrewrite.getListRewrite ( a.getParent (), Block.STATEMENTS_PROPERTY );
                lrw.insertAfter ( newtext, a, null );
            }

            catch (Exception e){
            }
        }

        IDocument newdocument = new Document (readFileToString ( new File ( filename )));
        TextEdit edits = astrewrite.rewriteAST(newdocument, null);
        edits.apply(newdocument);
        System.out.print(newdocument.get());
        writeStringToFile ( new File(filename),newdocument.get ());
    }
}
