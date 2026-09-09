package Models;

import Extensions.NullCheckExtensions;

public abstract class LibraryItem {

    private static int _id = 1;
    private final int id;
    private String title;
    private String year;

    public LibraryItem(String title, String year) {
        this.title = NullCheckExtensions.isValidString(title, "Title").trim();
        this.year = NullCheckExtensions.isValidString(year, "Year").trim();
        this.id = _id++;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = NullCheckExtensions.isValidString(title, "Title").trim();
    }

    public void setYear(String year) {
        this.year = NullCheckExtensions.isValidString(year, "Year").trim();
    }

    public abstract void showInfo();
}