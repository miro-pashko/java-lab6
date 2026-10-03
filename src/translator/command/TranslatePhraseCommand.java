package translator.command;


import translator.ConsoleIO;
import translator.service.TranslationService;

public class TranslatePhraseCommand implements Command {

    private final TranslationService service;
    private final ConsoleIO io;

    public TranslatePhraseCommand(TranslationService service, ConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        String phrase = io.ask("Phrase to translate: ");
        io.say(phrase + "  =>  " + service.translate(phrase) + "\n");
    }
}
