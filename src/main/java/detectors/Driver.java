package detectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.JavaParser;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Domingues, Joao #2334590D
 */

public class Driver {

    static void printOut(List<Breakpoints> concreteVisitor, String s){
        System.out.println(s);
        for(Breakpoints bp : concreteVisitor){
            System.out.println(bp);
        }
    }

    static File getFile(){
        final String cd = System.getProperty("user.dir");
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File (cd));
        int returnVal = fc.showOpenDialog(null); // set to null b/ no event binding

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return(fc.getSelectedFile());
        }else{
                return null;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {

        //get file
        CompilationUnit cu = null;
        try {
           cu = JavaParser.parse(getFile());
        } catch(IOException e){
            System.out.println("Parsing Error" + e.getMessage());
        }
        //instantiate concreteVisitors
            UselessControlFlowDetector concreteFlow = new UselessControlFlowDetector();
            List<Breakpoints> containerFlow = new ArrayList<Breakpoints>();
            concreteFlow.visit(cu, containerFlow);

            RecursionDetector concreteRec = new RecursionDetector();
            List<Breakpoints> containerRec = new ArrayList<Breakpoints>();
            concreteRec.visit(cu, containerRec);
        //stdout
            printOut(containerFlow,"Useless Control Flow:");
            printOut(containerRec,"Polymorphic Recursion:");

        }
    }


