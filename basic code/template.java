
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class template {

    public static void logger(int linesno, String type, String... args ) throws IOException {

        FileWriter fw =null;
        BufferedWriter bw =null;
        System.out.print(linesno + " , " + type + " , " + args[0]);
        File output = new File("output.txt");

        if(args.length >1)
        {
            System.out.print(" , " +args[1]);
            fw = new FileWriter(output, true);
            bw = new BufferedWriter(fw);
            bw.write(linesno + " , "+ type+" , "+ args[0] + " , "+ args[1]);
            bw.close();
            fw.close();
        }


        System.out.println("");



    }

    public static void main(String[] args) throws IOException {

        String current = new java.io.File( “file_path here” ).getCanonicalPath();
        File folder = new File(current);
        File[] ListofFiles = folder.listFiles();

        for(int i=0; i < ListofFiles.length; i++)
        {
            if(ListofFiles[i].getName().equals("template.java")){

            }

            else{
                System.out.println("File : " + ListofFiles[i].getName());
                String path = ListofFiles[i].getPath();
                File file = new File(path);
                String str = FileUtils.readFileToString(file);

                ASTParser parser = ASTParser.newParser(AST.JLS8);
                parser.setResolveBindings(true);
                parser.setKind(ASTParser.K_COMPILATION_UNIT);
                Map options = JavaCore.getOptions();
                parser.setCompilerOptions(options);
                parser.setBindingsRecovery(true);

                String unitName = "App.java";
                parser.setUnitName(unitName);

                String[] sources = {current};
                String[] classpath = {""};
                parser.setEnvironment(classpath, sources, new String[]{"UTF-8"}, true);
                parser.setSource(str.toCharArray());
                CompilationUnit cu = (CompilationUnit) parser.createAST(null);
                if (cu.getAST().hasBindingsRecovery()) {
                    System.out.println("Binding activated.");
                }

                AST modify = cu.getAST();
                ASTRewrite rewrite = ASTRewrite.create(modify);

                cu.accept(new ASTVisitor() {
                    public boolean visit(VariableDeclarationStatement node){

                        for (Iterator iter = node.fragments().iterator(); iter.hasNext();) {
                            System.out.println("------------------");

                            VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
                            IVariableBinding binding = fragment.resolveBinding();
                            IMethodBinding method = binding.getDeclaringMethod();
                            ITypeBinding cls = method.getDeclaringClass();
                            String pack = cls.getPackage().getName();
                            System.out.println(cu.getLineNumber(node.getStartPosition())+" type "+"  "+ pack+"."+cls.getName()+ "."+method.getName()+"."+binding.getVariableDeclaration().getName());
                            System.out.println("binding: " +binding);

                            MethodInvocation mi = modify.newMethodInvocation();
                            mi.setExpression(modify.newSimpleName("template"));
                            mi.setName(modify.newSimpleName("logger"));

                            NumberLiteral nl = modify.newNumberLiteral();
                            nl.setToken(""+cu.getLineNumber(node.getStartPosition()));
                            StringLiteral sl = modify.newStringLiteral();
                            sl.setLiteralValue("declare");
                            StringLiteral s2 = modify.newStringLiteral();
                            String s2_value = pack+"."+cls.getName()+ "."+method.getName()+"."+binding.getVariableDeclaration().getName();
                            s2.setLiteralValue(s2_value);
                            mi.arguments().add(nl);
                            mi.arguments().add(sl);
                            mi.arguments().add(s2);

                            Statement newStatement = modify.newExpressionStatement(mi);
                            ListRewrite listRewrite= rewrite.getListRewrite(node.getParent(), Block.STATEMENTS_PROPERTY);
                            listRewrite.insertAfter(newStatement,node,null);
                        }
                        return true;
                    }

                    public boolean visit(FieldDeclaration node){
                        for (Iterator iter = node.fragments().iterator();iter.hasNext();){
                            System.out.println("------------------");

                            VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();

                            IVariableBinding binding = fragment.resolveBinding();
                            ITypeBinding cls = binding.getVariableDeclaration().getDeclaringClass();
                            IPackageBinding pck = cls.getPackage();
                            System.out.println("variable " + pck+"."+cls.getName()+"."+binding.getVariableDeclaration().getName());
                            System.out.println("binding: " + binding);
                        }
                        return  true;
                    }

                    public boolean visit(Assignment node){

                        SimpleName left = null;
                        Expression lhs = node.getLeftHandSide();
                        Expression rhs = node.getRightHandSide();
                        if(lhs instanceof org.eclipse.jdt.core.dom.FieldAccess){
                            left = ((FieldAccess) lhs).getName();
                            System.out.println(cu.getLineNumber(node.getStartPosition())+" assign  "+ left + " , " + rhs);
                            MethodInvocation mi = modify.newMethodInvocation();
                            mi.setExpression(modify.newSimpleName("template"));
                            mi.setName(modify.newSimpleName("logger"));
                            NumberLiteral nl = modify.newNumberLiteral();
                            nl.setToken(""+cu.getLineNumber(node.getStartPosition()));
                            StringLiteral sl = modify.newStringLiteral();
                            sl.setLiteralValue("assign");
                            StringLiteral sl2 = modify.newStringLiteral();
                            sl2.setLiteralValue(String.valueOf(left)+" = ");
                            //SimpleName sl3 = modify.newSimpleName("left");
                            MethodInvocation inner_mi = modify.newMethodInvocation();
                            inner_mi.setExpression(modify.newSimpleName(left.getIdentifier()));
                            inner_mi.setName(modify.newSimpleName("toString"));
                            mi.arguments().add(nl);
                            mi.arguments().add(sl);
                            mi.arguments().add(sl2);
                            mi.arguments().add(inner_mi);
                            System.out.println("hello"+ mi.toString());
                            Statement newStatement = modify.newExpressionStatement(mi);

                            ListRewrite listRewrite= rewrite.getListRewrite(node.getParent().getParent(), Block.STATEMENTS_PROPERTY);
                            listRewrite.insertAfter(newStatement,node.getParent(),null);

                        }
                        else{
                            System.out.println(cu.getLineNumber(node.getStartPosition())+" assign  "+ lhs.toString() + " , " + rhs);
                            MethodInvocation mi = modify.newMethodInvocation();
                            mi.setExpression(modify.newSimpleName("template"));
                            mi.setName(modify.newSimpleName("logger"));
                            NumberLiteral nl = modify.newNumberLiteral();
                            nl.setToken(""+cu.getLineNumber(node.getStartPosition()));
                            StringLiteral sl = modify.newStringLiteral();
                            sl.setLiteralValue("assign");
                            StringLiteral sl2 = modify.newStringLiteral();
                            sl2.setLiteralValue(lhs.toString()+" = ");
                            //SimpleName sl3 = modify.newSimpleName("left");
                            MethodInvocation inner_mi = modify.newMethodInvocation();
                            inner_mi.setExpression(modify.newSimpleName(lhs.toString()));
                            inner_mi.setName(modify.newSimpleName("toString"));
                            mi.arguments().add(nl);
                            mi.arguments().add(sl);
                            mi.arguments().add(sl2);
                            mi.arguments().add(inner_mi);
                            System.out.println("hello"+ mi.toString());
                            Statement newStatement = modify.newExpressionStatement(mi);

                            ListRewrite listRewrite= rewrite.getListRewrite(node.getParent().getParent(), Block.STATEMENTS_PROPERTY);
                            listRewrite.insertAfter(newStatement,node.getParent(),null);

                        }
                        return  true;
                    }

                });

                try {
                    Document document = new Document(str);
                    TextEdit edit = rewrite.rewriteAST(document,null);
                    edit.apply(document);

                    String name = "Old_" + file.getName();
                    String path1 = “directory path here” + name + ".txt";
                    File new_file = new File(path1);
                    if(file.renameTo(new_file)){
                        System.out.println("rename sucessful");
                    }

                    File file1 = new File(path);
                    file1.createNewFile();
                    FileWriter writer = new FileWriter(file1);
                    writer.write(document.get());
                    writer.close();
                    System.out.println(document.get());
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



