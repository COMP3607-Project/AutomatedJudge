import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.Arrays;

public class FileCompilationChecker {
    // Counter to keep track of failed compilations
    private int failedCompilationCount;

    public FileCompilationChecker() {
        failedCompilationCount = 0;
    }

    public void checkCompilation(String filePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {
            System.out.println("Java Compiler not found. Ensure a JDK is installed.");
            return;
        }

        int compilationResult = compiler.run(null, null, null, filePath);

        if (compilationResult != 0) {
            System.out.println("Compilation failed for file: " + filePath);
            failedCompilationCount++;
        } else {
            System.out.println("Compilation succeeded for file: " + filePath);
        }
    }

    public int getFailedCompilationCount() {
        return failedCompilationCount;
    }

    public static void main(String[] args) {
        FileCompilationChecker checker = new FileCompilationChecker();

        // Replace with the path to your Java file
        String filePath = "path/to/YourJavaFile.java";

        // Check compilation
        checker.checkCompilation(filePath);

        // Access the count of failed compilations
        int failedCompilationCount = checker.getFailedCompilationCount();
        System.out.println("Total failed compilations: " + failedCompilationCount);
    }
}
