package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "app", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "Print the difference between two files")

public class App implements Callable<Integer> {
    public Integer call() {
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
