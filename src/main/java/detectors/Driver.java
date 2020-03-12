package detectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.StaticJavaParser;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Domingues, Joao #2334590D
 */

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {

        final String cd = System.getProperty("user.dir");
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File (cd));
        int returnVal = fc.showOpenDialog(null); // set to null b/ no event binding

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            CompilationUnit cu = StaticJavaParser.parse(file);
            System.out.println(cu);

        }
    }
}

