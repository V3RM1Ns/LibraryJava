package Services;

import Extensions.NullCheckExtensions;
import Models.Book;
import Models.Magazine;
import Models.Student;
import Models.Teacher;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class LibraryManager {

    private final List<Book> books = new ArrayList<>();
    private final List<Magazine> magazines = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(NullCheckExtensions.isValidClass(book, "Book"));
    }

    public void addMagazine(Magazine magazine) {
        magazines.add(NullCheckExtensions.isValidClass(magazine, "Magazine"));
    }

    public void addStudent(Student student) {
        users.add(NullCheckExtensions.isValidClass(student, "Student"));
    }

    public void addTeacher(Teacher teacher) {
        users.add(NullCheckExtensions.isValidClass(teacher, "Teacher"));
    }

    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    public List<Magazine> getMagazines() {
        return List.copyOf(magazines);
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public List<Book> searchBooks(String title) {
        String query = NullCheckExtensions.isValidString(title, "Book title")
                .trim()
                .toLowerCase(Locale.ROOT);

        return books.stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).contains(query))
                .toList();
    }

    public Book searchBook(int bookId) {
        return findBook(bookId);
    }

    public void borrowBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book.isBorrowed()) {
            throw new IllegalStateException(
                    "Book is already borrowed by " + book.getBorrowedBy().getName() + "!"
            );
        }

        book.borrow(user);
    }

    public void returnBook(int bookId) {
        Book book = findBook(bookId);

        if (!book.isBorrowed()) {
            throw new IllegalStateException("Book is not currently borrowed!");
        }

        book.returnItem();
    }

    public void removeBook(int bookId) {
        int validId = NullCheckExtensions.isValidId(bookId, "Book ID");
        boolean removed = books.removeIf(book -> book.getId() == validId);

        if (!removed) {
            throw new IllegalArgumentException("Book with ID " + validId + " not found!");
        }
    }

    public void removeUser(int userId) {
        int validId = NullCheckExtensions.isValidId(userId, "User ID");
        boolean removed = users.removeIf(user -> user.getId() == validId);

        if (!removed) {
            throw new IllegalArgumentException("User with ID " + validId + " not found!");
        }
    }

    private Book findBook(int bookId) {
        int validId = NullCheckExtensions.isValidId(bookId, "Book ID");
        return books.stream()
                .filter(book -> book.getId() == validId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Book with ID " + validId + " not found!"
                ));
    }

    private User findUser(int userId) {
        int validId = NullCheckExtensions.isValidId(userId, "User ID");
        return users.stream()
                .filter(user -> user.getId() == validId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + validId + " not found!"
                ));
    }
}
