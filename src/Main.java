import Models.Book;
import Models.Magazine;
import Models.Student;
import Models.Teacher;
import Models.User;
import Services.LibraryManager;

import java.util.List;
import java.util.Scanner;

public final class Main {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LibraryManager LIBRARY_MANAGER = new LibraryManager();

    private Main() {
    }

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            clearConsole();
            printMenu();

            try {
                int choice = readInt("Choose: ");
                clearConsole();

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> addMagazine();
                    case 3 -> addStudent();
                    case 4 -> addTeacher();
                    case 5 -> showAllBooks();
                    case 6 -> showAllUsers();
                    case 7 -> searchBook();
                    case 8 -> borrowBook();
                    case 9 -> returnBook();
                    case 10 -> removeBook();
                    case 11 -> removeUser();
                    case 0 -> running = false;
                    default -> showError("Invalid menu option.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                showError(exception.getMessage());
            }

            if (running) {
                pause();
            }
        }

        SCANNER.close();
        System.out.println(GREEN + "Goodbye!" + RESET);
    }

    private static void printMenu() {
        System.out.println(CYAN + "================================================================================" + RESET);
        System.out.println(PURPLE + "                             LIBRARY MANAGEMENT" + RESET);
        System.out.println(CYAN + "================================================================================" + RESET);
        System.out.println("1. Add Book");
        System.out.println("2. Add Magazine");
        System.out.println("3. Add Student");
        System.out.println("4. Add Teacher");
        System.out.println("5. Show All Books");
        System.out.println("6. Show All Users");
        System.out.println("7. Search Book");
        System.out.println("8. Borrow Book");
        System.out.println("9. Return Book");
        System.out.println("10. Remove Book");
        System.out.println("11. Remove User");
        System.out.println("0. Exit");
        System.out.println(CYAN + "================================================================================" + RESET);
    }

    private static void addBook() {
        String title = readText("Title: ");
        String year = readText("Year: ");
        String author = readText("Author: ");
        int pageCount = readInt("Page count: ");

        LIBRARY_MANAGER.addBook(new Book(title, year, author, pageCount));
        showSuccess("Book added successfully.");
    }

    private static void addMagazine() {
        String title = readText("Title: ");
        String year = readText("Year: ");
        int issueNumber = readInt("Issue number: ");

        LIBRARY_MANAGER.addMagazine(new Magazine(title, year, issueNumber));
        showSuccess("Magazine added successfully.");
    }

    private static void addStudent() {
        String name = readText("Name: ");
        String gmail = readText("Gmail: ");
        int studentNumber = readInt("Student number: ");

        LIBRARY_MANAGER.addStudent(new Student(name, gmail, studentNumber));
        showSuccess("Student added successfully.");
    }

    private static void addTeacher() {
        String name = readText("Name: ");
        String gmail = readText("Gmail: ");
        String department = readText("Department: ");

        LIBRARY_MANAGER.addTeacher(new Teacher(name, gmail, department));
        showSuccess("Teacher added successfully.");
    }

    private static void showAllBooks() {
        List<Book> books = LIBRARY_MANAGER.getBooks();
        List<Magazine> magazines = LIBRARY_MANAGER.getMagazines();

        if (books.isEmpty() && magazines.isEmpty()) {
            System.out.println(YELLOW + "No books or magazines found." + RESET);
            return;
        }

        System.out.println(PURPLE + "BOOKS" + RESET);
        if (books.isEmpty()) {
            System.out.println(YELLOW + "No books found." + RESET);
        } else {
            books.forEach(book -> System.out.println(GREEN + book + RESET));
        }

        System.out.println();
        System.out.println(PURPLE + "MAGAZINES" + RESET);
        if (magazines.isEmpty()) {
            System.out.println(YELLOW + "No magazines found." + RESET);
        } else {
            magazines.forEach(magazine -> System.out.println(GREEN + magazine + RESET));
        }
    }

    private static void showAllUsers() {
        List<User> users = LIBRARY_MANAGER.getUsers();

        if (users.isEmpty()) {
            System.out.println(YELLOW + "No users found." + RESET);
            return;
        }

        users.forEach(user -> System.out.println(GREEN + user + RESET));
    }

    private static void searchBook() {
        System.out.println(PURPLE + "SEARCH BOOK" + RESET);
        System.out.println("1. Search by title");
        System.out.println("2. Search by ID");

        int searchType = readInt("Choose search type: ");
        clearConsole();

        if (searchType == 1) {
            String title = readText("Book title: ");
            List<Book> results = LIBRARY_MANAGER.searchBooks(title);
            printBookSearchResults(results);
            return;
        }

        if (searchType == 2) {
            printBooks();
            Book book = LIBRARY_MANAGER.searchBook(readInt("Book ID: "));
            System.out.println(PURPLE + "SEARCH RESULT" + RESET);
            System.out.println(GREEN + book + RESET);
            return;
        }

        throw new IllegalArgumentException("Invalid search type.");
    }

    private static void printBookSearchResults(List<Book> results) {
        if (results.isEmpty()) {
            System.out.println(YELLOW + "No books found." + RESET);
            return;
        }

        System.out.println(PURPLE + "SEARCH RESULTS" + RESET);
        results.forEach(book -> System.out.println(GREEN + book + RESET));
    }

    private static void borrowBook() {
        printBooks();
        printUsers();

        int bookId = readInt("Book ID: ");
        int userId = readInt("User ID: ");

        LIBRARY_MANAGER.borrowBook(bookId, userId);
        showSuccess("Book borrowed successfully.");
    }

    private static void returnBook() {
        printBooks();
        int bookId = readInt("Book ID: ");

        LIBRARY_MANAGER.returnBook(bookId);
        showSuccess("Book returned successfully.");
    }

    private static void removeBook() {
        printBooks();
        int bookId = readInt("Book ID: ");

        LIBRARY_MANAGER.removeBook(bookId);
        showSuccess("Book removed successfully.");
    }

    private static void removeUser() {
        printUsers();
        int userId = readInt("User ID: ");

        LIBRARY_MANAGER.removeUser(userId);
        showSuccess("User removed successfully.");
    }

    private static void printBooks() {
        List<Book> books = LIBRARY_MANAGER.getBooks();

        System.out.println(PURPLE + "AVAILABLE BOOKS" + RESET);
        if (books.isEmpty()) {
            System.out.println(YELLOW + "No books found." + RESET);
            return;
        }

        books.forEach(book -> System.out.println(GREEN + book + RESET));
        System.out.println();
    }

    private static void printUsers() {
        List<User> users = LIBRARY_MANAGER.getUsers();

        System.out.println(PURPLE + "AVAILABLE USERS" + RESET);
        if (users.isEmpty()) {
            System.out.println(YELLOW + "No users found." + RESET);
            return;
        }

        users.forEach(user -> System.out.println(GREEN + user + RESET));
        System.out.println();
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        String input = SCANNER.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Please enter a valid number.");
        }
    }

    private static void showSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }

    private static void showError(String message) {
        String safeMessage = message == null ? "Unknown error." : message;
        System.out.println(RED + "Error: " + safeMessage + RESET);
    }

    private static void pause() {
        System.out.println();
        System.out.print(YELLOW + "Press Enter to continue..." + RESET);
        SCANNER.nextLine();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
