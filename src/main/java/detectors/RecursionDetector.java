package detectors;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @author Domingues, Joao #2334590D
 */


public class RecursionDetector extends VoidVisitorAdapter<List<Breakpoints>> {

    private String className, methodName;
    private MethodDeclaration signature;
    private int endline, startline;
    private List<Breakpoints> container;

    //  Visitors
    @Override
    public void visit(MethodDeclaration n, List<Breakpoints> container) {
        this.methodName = n.getNameAsString();
        super.visit(n,container);
    }

    public void visit(MethodCallExpr n, List<Breakpoints> container){
        String innerCaller = n.getName().toString();
        if(innerCaller.equals(methodName)){
            store(n);
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<Breakpoints> container){
        this.className = n.getNameAsString();
        this.container = container;
        super.visit(n,container);
    }


    //helper
    public void store(Node n) {
        this.startline = n.getRange().get().begin.line;
        this.endline = n.getRange().get().end.line;
        this.container.add(
                new Breakpoints(className, methodName, startline, endline));
    }
}

/* ALTERNATIVE IMPLEMENTATION OF VISITORS
returning the method definition block range instead of the caller range

    @Override
    public void visit(MethodDeclaration n, List<Breakpoints> container) {
        this.signature = n;
        this.methodName = signature.getNameAsString();
        super.visit(n,container);
    }

    public void visit(MethodCallExpr n, List<Breakpoints> container){
        String innerCaller = n.getName().toString();
        if(innerCaller.equals(methodName)){
            store(signature);
        }
    }
 */