package Parser;


import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class ASTVisitMethodDeclaration extends ASTVisitor {
    private CompilationUnit cu;
    private String className;
    private String packageName;
    public static String fncontent=null;
    public static String fnName=null;

    ASTVisitMethodDeclaration(CompilationUnit cu) {
        this.cu = cu;
        this.className = className;
        packageName=cu.getPackage().toString();
        packageName=packageName.substring(0,packageName.length()-2)+" ";

    }
    public boolean visit(MethodDeclaration node) {
        SimpleName name = node.getName();
        if(name.toString().compareTo("getIndex")==0 && name.toString().compareTo(fnName)==0 ) {
            fnName=name.toString();
            fncontent = node.getBody().toString();

        }
        else if(name.toString().compareTo("fillRanges")==0 && name.toString().compareTo(fnName)==0) {
            fnName=name.toString();
            fncontent = node.getBody().toString();
       }
        else if(name.toString().compareTo("Resize")==0 && name.toString().compareTo(fnName)==0) {

                fnName=name.toString();
                fncontent = node.getBody().toString();
                 }
        else if(name.toString().compareTo("RectRotate")==0 && name.toString().compareTo(fnName)==0) {

            fnName=name.toString();
            fncontent = node.getBody().toString();

        }else if(name.toString().compareTo("Rotate")==0 && name.toString().compareTo(fnName)==0) {

            fnName=name.toString();
            fncontent = node.getBody().toString();

        }
        else if(name.toString().compareTo("checkTolerance")==0 && name.toString().compareTo(fnName)==0) {

            fnName=name.toString();
            fncontent = node.getBody().toString();

        }
        else if(name.toString().compareTo("BufferFill")==0 && name.toString().compareTo(fnName)==0) {

            fnName=name.toString();
            fncontent = node.getBody().toString();

        }
        else if(name.toString().compareTo("checktolerator")==0 && name.toString().compareTo(fnName)==0) {

            fnName = name.toString();
            fncontent = node.getBody().toString();
        }
        else if(name.toString().compareTo("getPoint")==0 && name.toString().compareTo(fnName)==0) {

            fnName = name.toString();
            fncontent = node.getBody().toString();
        }
        else if(name.toString().compareTo("RectReAllign")==0 && name.toString().compareTo(fnName)==0) {

            fnName = name.toString();
            fncontent = node.getBody().toString();
        }



        return false;
    }

}
