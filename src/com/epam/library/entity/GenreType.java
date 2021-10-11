package com.epam.library.entity;

public enum GenreType {
    ADVENTURES,
    DETECTIVE,
    FOLKLORE,
    NOVEL,
    FANTASY,
    SCIENCE,
    COMEDY,
    COOKING;

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace("_","-");
    }
}
