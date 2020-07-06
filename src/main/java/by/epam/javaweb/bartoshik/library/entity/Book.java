package by.epam.javaweb.bartoshik.library.entity;

public class Book {
    private int id;
    private String title;
    private String author;
    private int userId;

    public Book(){

    }

    public Book(int id, String title, String author, int userId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.userId = userId;
    }
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
