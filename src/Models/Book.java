package Models;

import Interfaces.ILendable;

public final class Book extends LibraryItem implements ILendable {

    private String author;
    private int pageCount;
    private User borrowedBy;

    public Book(String title, String year, String author, int pageCount) {
        super(title, year);

        if (author == null || author.trim().isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");
        if (pageCount <= 0)
            throw new IllegalArgumentException("Page count must be greater than zero");

        this.author = author.trim();
        this.pageCount = pageCount;
        this.borrowedBy = null;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public boolean isBorrowed() {
        return borrowedBy != null;
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author.trim();
        }
    }

    public void setPageCount(int pageCount) {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        }
    }

    @Override
    public void showInfo() {
        String borrowerInfo = (borrowedBy != null) ? borrowedBy.getName() : "None";
        System.out.println("ID: " + getId() +
                " | Title: " + getTitle() +
                " | Year: " + getYear() +
                " | Author: " + author +
                " | Page Count: " + pageCount +
                " | Borrowed By: " + borrowerInfo);
    }
    @Override
    public void borrow(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }

        if (borrowedBy != null) {
            System.out.println("ERROR: '" + getTitle() + "' is already borrowed by " + borrowedBy.getName() + "!");
            return;
        }

        this.borrowedBy = user;
        System.out.println("'" + getTitle() + "' has been successfully borrowed by " + user.getName() + ".");
    }

    @Override
    public void returnItem() {
        if (borrowedBy == null) {
            System.out.println("ERROR: '" + getTitle() + "' is already in the library!");
            return;
        }

        System.out.println("'" + getTitle() + "' has been successfully returned by " + borrowedBy.getName() + ".");
        this.borrowedBy = null;
    }
}