package ParserMain;

import org.eclipse.jdt.core.dom.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class nodeVisitor {

    CompilationUnit cu;

    public void getCU(CompilationUnit cu){
      this.cu = cu;
    }
    public ArrayList<Statement> visitnodesStatements() {

        // Master list for all the statements
        ArrayList<Statement> masterStatementList=new ArrayList<Statement> ();

        cu.accept ( new ASTVisitor () {
            @Override
            public void endVisit(IfStatement node) {
                masterStatementList.add ( node );
                super.endVisit ( node );
            }
        } );

        cu.accept ( new ASTVisitor () {
            @Override
            public void endVisit(ForStatement node) {
                masterStatementList.add ( node );
                super.endVisit ( node );
            }
        } );

        cu.accept ( new ASTVisitor () {
            @Override
            public void endVisit(WhileStatement node) {
                masterStatementList.add ( node );
                super.endVisit ( node );
            }
        } );

        cu.accept ( new ASTVisitor () {
            @Override
            public boolean visit(ExpressionStatement node) {
                masterStatementList.add ( node );
                return super.visit ( node );
            }
        } );

        cu.accept ( new ASTVisitor () {
            @Override
            public void endVisit(SwitchStatement node) {
                masterStatementList.add ( node );
                super.endVisit ( node );
            }
        } );

        cu.accept ( new ASTVisitor () {
            @Override
            public void endVisit(VariableDeclarationStatement node) {
                masterStatementList.add(node);
                super.endVisit ( node );
            }
        } );

        return masterStatementList;
    }

    public ArrayList<SimpleName> visitnodesSN(){
        // List for Simple Name
        ArrayList<SimpleName> SNlist = new ArrayList<SimpleName> ();

        cu.accept ( new ASTVisitor () {
            @Override
            public boolean visit(SimpleName node) {
                SNlist.add ( node );
/*
                System.out.println ( String.valueOf ( node ) );
*/
                return super.visit ( node );
            }
        } );
/*
        System.out.println ( SNlist.size () );
*/
        return SNlist;
    }

    public ArrayList<MethodInvocation> visitnodesMethod(){
        // List for Method Name
        ArrayList<MethodInvocation> methodList = new ArrayList<MethodInvocation> ();

        cu.accept ( new ASTVisitor () {
            @Override
            public boolean visit(MethodInvocation node) {
                methodList.add (node);
                return super.visit ( node );
            }
        } );
        return methodList;
    }

    public ArrayList<ImportDeclaration> visitImports(){
        ArrayList<ImportDeclaration> importList = new ArrayList<ImportDeclaration> ();
        cu.accept ( new ASTVisitor () {
            @Override
            public boolean visit(ImportDeclaration node) {
                importList.add(node);
                return super.visit ( node );
            }
        } );
        return importList;
    }
}