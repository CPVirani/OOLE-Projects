package MutationLauncher;

import MutationInjection.*;
import javassist.*;
import static MutationLauncher.Mutationmatrix.mutation_matrix;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestCasesMutatewithAssist {
    public TestCasesMutatewithAssist() throws IOException, NotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            CannotCompileException, ClassNotFoundException, InstantiationException {
        AOR aor = new AOR();
        ROR ror = new ROR();
        COR cor=new COR();
        LOR lor=new LOR();
        ASR asr = new ASR();
        SOR sor=new SOR();
        DeleteInjection sdl=new DeleteInjection();

        for (int i = 0; i < mutation_matrix.length; i++) {


            if (mutation_matrix[i][0] != null) {
                String[] class_fun = (mutation_matrix[i][0].split(" "));

                if (mutation_matrix[i][1] == "AORb")
                    aor.arithOperatorChangeBinary(class_fun[0], class_fun[1]);
                else if (mutation_matrix[i][1] == "AORs")
                    aor.arithOperatorChangeShorthand(class_fun[0], class_fun[1]);
                else if (mutation_matrix[i][1] == "ROR") {
                    ror.relationalOperatorChange(class_fun[0], class_fun[1]);}
                else if (mutation_matrix[i][1] == "COR") {
                    cor.conditionalOperatorChangeBinary(class_fun[0], class_fun[1]);}
                else if(mutation_matrix[i][1]=="COD")
                    cor.conditionalOperatorDeleteUnary(class_fun[0],class_fun[1]);
                else if(mutation_matrix[i][1]=="LOR")
                    lor.logicalOperatorChangeBinary(class_fun[0],class_fun[1]);
                else if(mutation_matrix[i][1]=="LOD")
                    lor.logicalOperatorDeleteUnary(class_fun[0],class_fun[1]);
                else if(mutation_matrix[i][1]=="ASR")
                    asr.assignopreplacer(class_fun[0],class_fun[1]);
                else if(mutation_matrix[i][1]=="SOR") {

                    sor.ShiftOperatorChange(class_fun[0], class_fun[1]);
                }
                else if(mutation_matrix[i][1]=="SDL")
                    sdl.StatementDeLetion(class_fun[0],class_fun[1]);


            } else
                    break;
            }
        }


    }


