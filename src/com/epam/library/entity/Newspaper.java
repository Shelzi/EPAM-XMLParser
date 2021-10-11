package com.epam.library.entity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public class Newspaper extends Publication{
    private String publisher;
    private String website;
    private LocalDate date;

    public Newspaper(){

    }

    public Newspaper(String publisher, String website, LocalDate date){
        this.publisher = publisher;
        this.website = website;
        this.date = date;


    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public GenreType getGenre() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Newspaper newspaper = (Newspaper) o;
        return Objects.equals(publisher, newspaper.publisher) && Objects.equals(website, newspaper.website) && Objects.equals(date, newspaper.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publisher, website, date);
    }

    @Override
    public String toString() {
        return "Newspaper:\t" +
                super.getTitle() + ", "
                + publisher + ", "
                + website + ", "
                + date + ", pages - " +
                super.getPages();
    }
}
