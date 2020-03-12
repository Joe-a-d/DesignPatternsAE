package detectors;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

/**
 * @author Domingues, Joao #2334590D
 */

/*
Sources
https://javaparser.org/the-quick-and-the-full-api-of-javaparser/
https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest
https://github.com/javaparser/javaparser-visited/blob/master/src/main/java/org/javaparser/examples
https://leanpub.com/javaparservisited
https://tomassetti.me/getting-started-with-javaparser-analyzing-java-code-programmatically/
 */

/*
API : Nodes
BlockStmt -- in between {}
  FLOW
    ForStmt
    IfStmt
    SwitchEntryStmt
    SwitchStmt
    ForeachStmt
    DoStmt
    TryStmt
    WhileStmt
    BlockComment
  NONflow
    Anonymous code blocks ?
    MethodDeclaration
    ClassOrInterfaceDeclaration
LineComment
EmptyStmt -- a ";" where a statement is expected.
 */

/*
JavaParser Visited CH2 ; page19
@Override
        public void visit(MethodDeclaration md, List<String> collector) {
            super.visit(md, collector);
            collector.add(md.getNameAsString());
        }
 */

/*
As per the lecturer's instructions, we'll only be looking at empty control flow statements.
CASES:
- BlockStmt.isEmpty && isNot(MethodDeclaration, ClassorInterfaceDeclaration)
*/

public class UselessControlFlowDetector extends VoidVisitorAdapter<List<Breakpoints>> {

    private String className, methodName;
    private int endline, startline;
    private List<Breakpoints>  container;





    @Override
    public void visit(BlockStmt n, List<Breakpoints> container){
        if(n.isEmpty()){
            store(n);
            return;
        }
        super.visit(n, container);
    }


    public void store(Node n) {
        this.startline = n.getRange().get().begin.line;
        this.endline = n.getRange().get().end.line;
        this.container.add(

                new Breakpoints(className, methodName, startline, endline));
    }



}
