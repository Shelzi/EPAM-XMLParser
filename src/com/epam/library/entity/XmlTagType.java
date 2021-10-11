package com.epam.library.entity;

public enum XmlTagType {
    AUTHOR,
    BOOK,
    DATE,
    GENRE,
    LIBRARY,
    PAGES,
    PRICE,
    PUBLISHER,
    TITLE,
    WEBSITE,
    YEAR;


    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace("_", "-");
    }
}
