package by.epam.javaweb.bartoshik.library.model.entity;

import by.epam.javaweb.bartoshik.library.model.dao.base.Identified;

public class User implements Identified<Integer> {
    private int id;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
