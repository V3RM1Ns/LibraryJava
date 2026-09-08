package Models;

public abstract class LibraryItem {

    private static int _id = 1;
    private final int id;
    private String title;
    private String year;

    public LibraryItem(String title, String year) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be empty");
        if (year == null || year.trim().isEmpty())
            throw new IllegalArgumentException("Year cannot be empty");

        this.title = title.trim();
        this.year = year.trim();
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
        if (title != null && !title.trim().isEmpty()) {
            this.title = title.trim();
        }
    }

    public void setYear(String year) {
        if (year != null && !year.trim().isEmpty()) {
            this.year = year.trim();
        }
    }

    public abstract void showInfo();
}