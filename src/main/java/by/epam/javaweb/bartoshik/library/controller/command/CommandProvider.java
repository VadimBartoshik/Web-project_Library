package by.epam.javaweb.bartoshik.library.controller.command;

import by.epam.javaweb.bartoshik.library.controller.command.impl.AddBookCommand;

import java.util.HashMap;
import java.util.Map;

import static by.epam.javaweb.bartoshik.library.controller.command.ServletPath.ADD_BOOK;

public class CommandProvider {
    private final Map<ServletPath, Command> commands = new HashMap<>();

    public Map<ServletPath, Command> create() {
        commands.put(ADD_BOOK, new AddBookCommand());
        return commands;
    }

    public Map<ServletPath, Command> getCommands() {
        return commands;
    }
}
