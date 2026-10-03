package translator;

import translator.command.AddWordCommand;
import translator.command.AppContext;
import translator.command.Command;
import translator.command.ExitCommand;
import translator.command.ShowVocabularyCommand;
import translator.command.TranslatePhraseCommand;
import translator.service.TranslationService;

import java.util.LinkedHashMap;
import java.util.Map;

public class Launcher implements AppContext {

    private final ConsoleIO io = new ConsoleIO();
    private final TranslationService service = new TranslationService();
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private boolean alive = true;

    public static void main(String[] args) {
        new Launcher().run();
    }

    private Launcher() {
        commands.put("1", new AddWordCommand(service, io));
        commands.put("2", new TranslatePhraseCommand(service, io));
        commands.put("3", new ShowVocabularyCommand(service, io));
        commands.put("0", new ExitCommand(this, io));
    }

    private void run() {
        while (alive) {
            printOptions();
            Command chosen = commands.get(io.ask("Pick an option: "));
            if (chosen == null) {
                io.say("That option doesn't exist.\n");
                continue;
            }
            chosen.execute();
        }
        io.close();
    }

    private void printOptions() {
        io.say("""
                -----------------------------------------
                 EN -> UK PHRASE TRANSLATOR
                -----------------------------------------
                1) Teach a new word
                2) Translate a phrase
                3) List everything learned
                0) Quit""");
    }

    @Override
    public void stop() {
        alive = false;
    }
}
