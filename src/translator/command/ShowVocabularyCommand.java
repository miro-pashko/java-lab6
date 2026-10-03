package translator.command;


import translator.ConsoleIO;
import translator.service.TranslationService;

import java.util.Map;

public class ShowVocabularyCommand implements Command {

    private final TranslationService service;
    private final ConsoleIO io;

    public ShowVocabularyCommand(TranslationService service, ConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        io.say("\nKnown words: " + service.vocabularySize());
        service.snapshot().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> io.say("  " + entry.getKey() + " -> " + entry.getValue()));
        io.say("");
    }
}
