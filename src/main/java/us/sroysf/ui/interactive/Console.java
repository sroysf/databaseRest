package us.sroysf.ui.interactive;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Console {
    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        System.out.println("Starting interactive console, type 'quit' to exit");
        String prompt = "app > ";
        while (true) {
            String line = null;
            try {
                line = reader.readLine(prompt);
                if ("quit".equalsIgnoreCase(line)) {
                    break;
                }
                System.out.println("Line echo: " + line);
            } catch (UserInterruptException e) {
                // Ignore
            } catch (EndOfFileException e) {
                return;
            }
        }
    }
}
