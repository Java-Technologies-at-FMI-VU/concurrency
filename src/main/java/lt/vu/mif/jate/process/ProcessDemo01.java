package lt.vu.mif.jate.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessDemo01 {

    public static void main(String[] args) throws IOException {

        ProcessBuilder pbuilder = new ProcessBuilder("/usr/bin/find", "target", "-type", "f");
        Process proc = pbuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while (true) {

            String line = reader.readLine();
            if (line == null) {
                break;
            }

            System.out.println(line);

        }

    }

}
