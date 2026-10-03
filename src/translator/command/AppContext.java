package translator.command;

/**
 * A command that needs to shut the program down asks the context to do
 * it, rather than the main loop special-casing "the exit command" by name.
 */
public interface AppContext {
    void stop();
}
