package com.bibliotheque.API.Entity;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire")

public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String edition;
    public boolean available;

    @ManyToOne
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
