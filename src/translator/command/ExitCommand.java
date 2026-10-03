package translator.command;


import translator.ConsoleIO;

public class ExitCommand implements Command {

    private final AppContext context;
    private final ConsoleIO io;

    public ExitCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        io.say("Session ended.");
        context.stop();
    }
}
