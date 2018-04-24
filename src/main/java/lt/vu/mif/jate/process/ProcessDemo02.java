package lt.vu.mif.jate.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessDemo02 {

    private static final String FIFO = "target/fifo";
    
    static Process runCommand(String... args) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(args);
        return builder.start();
    }
    
    public static void main(String[] args) throws IOException {

        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classPath = System.getProperty("java.class.path");
        String classMain = System.getProperty("sun.java.command");
        
        if (args.length == 0) {

            // Create fifo for communication
            runCommand("rm", "-f", FIFO);
            runCommand("mkfifo", FIFO);
            
            // Spawn another process
            runCommand(javaBin, "-cp", classPath, classMain, "Hello", "from", "another", "process!");

            // Read from fifo until exit received
            try (BufferedReader br = new BufferedReader(new FileReader(FIFO))) {
                String line;
                do {
                    line = br.readLine();
                    System.out.format("Received: %s%n", line);
                } while (! "exit".equals(line));
            }
            
        } else {
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FIFO))) {
                for (String arg: args) {
                    bw.write(arg);
                    bw.newLine();
                }
                bw.write("exit");
            }
            
        }
        
    }

}
