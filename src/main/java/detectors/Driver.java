package detectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.StaticJavaParser;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Domingues, Joao #2334590D
 */

public class Driver {

    static void printOut(List<Breakpoints> concreteVisitor){
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
            CompilationUnit cu = StaticJavaParser.parse(getFile());
        //instantiate concreteVisitors
            UselessControlFlowDetector concreteFlow = new UselessControlFlowDetector();
            List<Breakpoints> containerFlow = new ArrayList<Breakpoints>();
            concreteFlow.visit(cu, containerFlow);
        //stdout
            printOut(containerFlow);

        }
    }


