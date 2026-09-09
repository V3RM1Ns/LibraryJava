package Models;

import Extensions.NullCheckExtensions;
import Interfaces.ILendable;

public final class Book extends LibraryItem implements ILendable {

    private String author;
    private int pageCount;
    private User borrowedBy;
    private boolean isBorrowd;

    public Book(String title, String year, String author, int pageCount) {
        super(title, year);

        this.author = NullCheckExtensions.isValidString(author, "Author").trim();
        if (pageCount <= 0)
            throw new IllegalArgumentException("Page count must be greater than zero");

        this.pageCount = pageCount;
        this.borrowedBy = null;
        this.isBorrowd=false;
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
        this.author = NullCheckExtensions.isValidString(author, "Author").trim();
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
        user = NullCheckExtensions.isValidClass(user, "User");

        if (borrowedBy != null) {
            System.out.println("ERROR: '" + getTitle() + "' is already borrowed by " + borrowedBy.getName() + "!");
            return;
        }

        this.borrowedBy = user;
        this.isBorrowd= true;
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
        this.isBorrowd=false;
    }

    @Override
    public String toString() {
        String borrowerInfo = (borrowedBy != null) ? borrowedBy.getName() : "None";
        return "ID: " + getId() +
                " | Title: " + getTitle() +
                " | Year: " + getYear() +
                " | Author: " + author +
                " | Page Count: " + pageCount +
                " | Borrowed By: " + borrowerInfo;
    }
}