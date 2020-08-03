package by.epam.javaweb.bartoshik.library.controller.command;

import by.epam.javaweb.bartoshik.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.javaweb.bartoshik.library.controller.command.ServletPath.*;

public class CommandProvider {
    private final Map<ServletPath, Command> commands = new HashMap<>();

    public Map<ServletPath, Command> create() {
        commands.put(ADD_BOOK, new AddBookCommand());
        commands.put(DELETE_BOOK, new DeleteBookCommand());
        commands.put(GET_ALL_BOOK, new GetAllBookCommand());
        commands.put(TAKE_BOOK, new TakeBookCommand());
        commands.put(RETURN_BOOK, new ReturnBookCommand());
        commands.put(AUTHORIZE_LOGIN, new AuthorizeCommand());
        commands.put(LANGUAGE, new LanguageCommand());
        commands.put(REGISTER, new RegisterCommand());

        return commands;
    }

    public Map<ServletPath, Command> getCommands() {
        return commands;
    }
}

