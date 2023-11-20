package comp3607project;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
 
public class FileCompilationChecker {
    // Counter to keep track of failed compilations
    private int failedCompilationCount;

    public FileCompilationChecker() {
        failedCompilationCount = 0;
    }

    public void checkCompilation(String filePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) 
            System.out.println("Java Compiler not found. Ensure a JDK is installed.");
         
        int compilationResult = compiler.run(null, null, null, filePath);

        if (compilationResult != 0) {
            System.out.println("Compilation failed for file: " + filePath);
            failedCompilationCount++;
        } else {
            System.out.println("Compilation succeeded for file: " + filePath);
        }
    }

    public int getFailedCount() {
        return failedCompilationCount;
    }
}
