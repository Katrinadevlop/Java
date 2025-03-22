public class Main {
    public static void main(String[] args) {
        Author author = new Author("Эрих Мария", "Ремарк", 10);
        Book book = new Book("Три товарища", 1936, 501, author);
        System.out.println(book.toString());
        System.out.println(book.isBig());
        System.out.println(book.matches("Мар"));
        System.out.println(book.estimatePrice());

        Author authorTwo = new Author("Ким Джи", "Юн", 9);
        Book bookTwo = new Book("Прачечная стирающая печали", 2024, 304, authorTwo);
        System.out.println(bookTwo.toString());
        System.out.println(bookTwo.isBig());
        System.out.println(bookTwo.matches("тираж"));
        System.out.println(bookTwo.estimatePrice());
    }
}