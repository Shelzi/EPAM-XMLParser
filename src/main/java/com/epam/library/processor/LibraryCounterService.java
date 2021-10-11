package main.java.com.epam.library.processor;

import main.java.com.epam.library.entity.Book;
import main.java.com.epam.library.entity.GenreType;
import main.java.com.epam.library.entity.Publication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryCounterService {

    public LibraryCounterService() {
    }

    public Map<GenreType, Integer> countGenreType(List<Publication> publicationList) {
        Map<GenreType, Integer> genreTypeCountMap = new HashMap<>();
        for (Publication publication : publicationList) {
            if (publication.getClass() == Book.class) {
                if (!genreTypeCountMap.containsKey(((Book) publication).getGenre())) {
                    GenreType genreType = ((Book) publication).getGenre();
                    genreTypeCountMap.put(genreType, 1);
                } else {
                    GenreType genreType = ((Book) publication).getGenre();
                    genreTypeCountMap.put(genreType, genreTypeCountMap.get(genreType) + 1);
                }
            }
        }
        return genreTypeCountMap;
    }

    public int countBooks(List<Publication> publicationList) {
        int bookCount = 0;
        for (Publication publication : publicationList) {
            if (publication.getClass() == Book.class) {
                bookCount++;
            }
        }
        return bookCount;
    }

    public int countAllPages(List<Publication> publicationList) {
        int pagesCount = 0;
        for (Publication publication : publicationList) {
            pagesCount += publication.getPages();
        }
        return pagesCount;
    }
}
