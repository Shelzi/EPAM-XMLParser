package com.epam.library.entity;

import java.time.Year;
import java.util.Objects;

public class Book extends Publication{
    private String author;
    private GenreType genre;
    private Year year;

    public Book(String author, GenreType genre, Year year) {
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    public Book(){

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && genre == book.genre && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, genre, year);
    }

    @Override
    public String toString() {
        return "Book:\t" +
                super.getTitle() + ", "
                + author + ", "
                + genre + ", "
                + year + ", pages - " +
                super.getPages();
    }
}
