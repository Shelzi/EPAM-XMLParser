package com.epam.library.entity;

public enum PublicationType {
    BOOK,
    NEWSPAPER;

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace("_","-");
    }
}
