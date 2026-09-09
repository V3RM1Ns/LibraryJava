package Models;

import Extensions.NullCheckExtensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Library {
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(NullCheckExtensions.isValidClass(book, "Book"));
    }

    public void removeBook(int id) {
        NullCheckExtensions.isValidId(id);
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Book with ID " + id + " not found!");
        }
    }

    public void addUser(User user){
        users.add(NullCheckExtensions.isValidClass(user, "User"));
    }

    public void removeUser(int id){
        NullCheckExtensions.isValidId(id);
        boolean removed = users.removeIf(user -> user.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("User with ID " + id + " not found!");
        }
    }

    public void showAllUsers() {
        if (users == null || users.isEmpty()) {
            System.out.println("There are no users in the list.");
            return;
        }

        users.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    public void showAllBooks() {
        if (books == null || books.isEmpty()) {
            System.out.println("There are no books in the list.");
            return;
        }

        books.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    public void borrowBook(int bookId, int userId) {
        int validBookId = NullCheckExtensions.isValidId(bookId, "Book ID");
        int validUserId = NullCheckExtensions.isValidId(userId, "User ID");

        var book = books.stream()
                .filter(b -> b.getId() == validBookId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error: Book not found with ID: " + validBookId));

        var user = users.stream()
                .filter(u -> u.getId() == validUserId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error: User not found with ID: " + validUserId));

        if (book.isBorrowed()) {
            throw new IllegalStateException("Error: Book is already borrowed by someone else!");
        }

        book.borrow(user);

        System.out.println(book + " Borrowed by:" + user);
    }

    public void returnBook(int bookId){
        int validBookId = NullCheckExtensions.isValidId(bookId, "Book ID");

        var book = books.stream()
                .filter(b -> b.getId() == validBookId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error: Book not found with ID: " + validBookId));
        if(book.isBorrowed()){
            System.out.println("Book is not borrowed!");
            return;
        }

        book.returnItem();
        System.out.println("Book returned successfully!");

    }

    public Book searchBook(int bookId){
        int validBookId = NullCheckExtensions.isValidId(bookId, "Book ID");

        var book = books.stream()
                .filter(b -> b.getId() == validBookId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error: Book not found with ID: " + validBookId));

        return book;
    }


    public Book searchBook(String title) {
        String validBookTitle = NullCheckExtensions.isValidString(title, "Book Title").trim().toLowerCase();

        return books.stream()
                .filter(b -> b.getTitle() != null && b.getTitle().toLowerCase().contains(validBookTitle))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Error: Book not found with title containing: '" + title + "'"));
    }

}