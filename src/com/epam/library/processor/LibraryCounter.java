package com.epam.library.processor;

import com.epam.library.entity.Book;
import com.epam.library.entity.GenreType;
import com.epam.library.entity.Publication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryCounter {

    public LibraryCounter() {
    }

    public static Map<GenreType, Integer> countGenreType(List<Publication> publicationList) {
        Map<GenreType, Integer> genreTypeCountMap = new HashMap<>();
        for (Publication publication : publicationList) {
            if (publication.getClass() == Book.class) {
                if (!genreTypeCountMap.containsKey(publication.getGenre())) {
                    GenreType genreType = publication.getGenre();
                    genreTypeCountMap.put(genreType, 1);
                } else {
                    GenreType genreType = publication.getGenre();
                    genreTypeCountMap.put(genreType, genreTypeCountMap.get(genreType) + 1);
                }
            }
        }
        return genreTypeCountMap;
    }

    public static int countBooks(List<Publication> publicationList) {
        int bookCount = 0;
        for (Publication publication : publicationList) {
            if (publication.getClass() == Book.class) {
                bookCount++;
            }
        }
        return bookCount;
    }

    public static int countAllPages(List<Publication> publicationList){
        int pagesCount = 0;
        for (Publication publication : publicationList) {
            pagesCount += publication.getPages();
        }
        return pagesCount;
    }
}
