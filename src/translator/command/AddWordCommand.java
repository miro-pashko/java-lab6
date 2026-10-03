package translator.command;


import translator.ConsoleIO;
import translator.service.TranslationService;

public class AddWordCommand implements Command {

    private final TranslationService service;
    private final ConsoleIO io;

    public AddWordCommand(TranslationService service, ConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        String english = io.ask("English word: ");
        String ukrainian = io.ask("Ukrainian word: ");

        if (english.isEmpty() || ukrainian.isEmpty()) {
            io.say("Both fields are required - nothing was added.\n");
            return;
        }

        boolean overwriting = service.knows(english);
        service.addTranslation(english, ukrainian);
        io.say((overwriting ? "Replaced " : "Learned ") + english + " = " + ukrainian + "\n");
    }
}
