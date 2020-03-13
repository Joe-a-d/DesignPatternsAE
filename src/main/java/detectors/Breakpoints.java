package detectors;

/**
 * @author Domingues, Joao #2334590D
 */

public class Breakpoints {

    private String className, methodName;
    private int startline, endline;

    public Breakpoints(String className, String methodName, int startline, int endline) {
        this.className = className;
        this.methodName = methodName;
        this.startline = startline;
        this.endline = endline;
    }


    @Override
    public String toString() {
        return  "className='" + className +
                ", methodName='" + methodName +
                ", startline=" + startline +
                ", endline=" + endline
                ;
    }
}
