import java.io.*;
import java.util.List;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ITrackedNodePosition;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.text.edits.UndoEdit;


public class Main {
    public static void logger(int linesno, String type, String... args ) throws IOException {

        FileWriter fw =null;
        BufferedWriter bw =null;
        System.out.print(linesno + " , " + type + " , " + args[0]);
        File output = new File("C:\\Users\\ADARSH\\sketch11\\src\\Output.txt");

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


    public static void parse(String str) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        List<AbstractTypeDeclaration> x = cu.types();
        for (AbstractTypeDeclaration type : x) {
            if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {
                List<BodyDeclaration> bodies = type.bodyDeclarations();
                for (BodyDeclaration bdy : bodies) {
                    if (bdy.getNodeType() == ASTNode.METHOD_DECLARATION) {
                        MethodDeclaration method = (MethodDeclaration)bdy;
                        System.out.println("Method name used: " + method.getName().getFullyQualifiedName());
                    }
                }

            }
        }
        cu.accept(new ASTVisitor() {

            Set names = new HashSet();

            public boolean visit(VariableDeclarationFragment node) {
                SimpleName abc = node.getName();
                this.names.add(abc.getIdentifier());
                System.out.println("Declaration of variable '" + abc + "' at line"
                        + cu.getLineNumber(abc.getStartPosition()));
                return false;
            }

            public boolean visit(SimpleName node) {
                if (this.names.contains(node.getIdentifier())) {
                    System.out.println("Usage of variable '" + node + "' at line "
                            + cu.getLineNumber(node.getStartPosition()));
                }
                return true;
            }
        });

    }

    //read file content into a string
    public static String readFileToString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);

        }

        reader.close();

        return  fileData.toString();
    }

    //loop directory to get file list
    public static void ParseFilesInDir() throws IOException{
        //File dirs = new File(".");
        //String dirPath = dirs.getCanonicalPath() + File.separator+"src"+File.separator+"app";

        //File root = new File(dirPath);
        File root = new File("/Users/sree/adarsh_janapareddyvenkata_sreetama_banerjee_charvi_virani/src/app/Launch.java");
        //System.out.println(rootDir.listFiles());
        //File[] files = root.listFiles ( );
        String filePath = null;

        //for (File f : files ) {
            filePath = root.getAbsolutePath();
            //if(f.isFile()){
                parse(readFileToString(filePath));
            //}
        //}
    }

    public static void main(String[] args) throws IOException {
        ParseFilesInDir();
    }

}