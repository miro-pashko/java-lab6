package translator;

import java.util.Scanner;

public class ConsoleIO {

    private final Scanner input = new Scanner(System.in);

    public String ask(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    public void say(String text) {
        System.out.println(text);
    }

    public void close() {
        input.close();
    }
}
