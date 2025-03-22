public class Book {
    public String title;
    public int releaseYear;
    public int pages;
    public Author author;

    public Book(String title, int releaseYear, int pages, Author author) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.pages = pages;
        this.author = author;
    }

    public boolean isBig() {
        return pages > 500;
    }

    public boolean matches(String word) {
        return title.toLowerCase().contains(word.toLowerCase()) || (author.name.toLowerCase().contains(word.toLowerCase()) && author.surname.toLowerCase().contains(word.toLowerCase()));
    }

    public int estimatePrice() {
        if (pages * 3 * Math.floor(Math.sqrt(author.rating)) > 250) {
            return pages * 3;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return title + " " + releaseYear + " " + pages + " " + author.name + " " + author.surname + " " + author.rating;
    }
}
