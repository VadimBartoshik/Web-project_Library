package by.epam.javaweb.bartoshik.library.controller.command;

public enum ServletPath {
    ADD_BOOK("/addBook"),
    DELETE_BOOK("/deleteBook"),
    GET_ALL_BOOK("/getAllBook"),
    RETURN_BOOK("/returnBook"),
    TAKE_BOOK("/takeBook");

    private final String path;

    ServletPath(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
