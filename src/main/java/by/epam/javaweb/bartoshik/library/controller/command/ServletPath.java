package by.epam.javaweb.bartoshik.library.controller.command;

public enum ServletPath {
    ADD_BOOK("/addBook"),
    DELETE_BOOK("/deleteBook"),
    GET_ALL_BOOK("/getAllBook"),
    RETURN_BOOK("/returnBook"),
    TAKE_BOOK("/takeBook"),
    AUTHORIZE_LOGIN("/authorizeLogin"),
    LANGUAGE("/language"),
    REGISTER("/register");

    private final String path;

    ServletPath(final String path) {
        this.path = path;
    }

    public static ServletPath getServletPathByName(String name) {
        ServletPath mCurrency = null;
        for (ServletPath sp : ServletPath.values()) {
            if (sp.path.equals(name)) {
                mCurrency = sp;
                break;
            }

        }
        return mCurrency;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}
