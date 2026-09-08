package Models;

public final class Magazine extends LibraryItem {

    private int issueNumber;

    public Magazine(String title, String year, int issueNumber) {
        super(title, year);

        if (issueNumber <= 0)
            throw new IllegalArgumentException("Issue number cannot be zero or negative!");

        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber <= 0)
            throw new IllegalArgumentException("Issue number cannot be zero or negative!");

        this.issueNumber = issueNumber;
    }

    @Override
    public void showInfo() {
        System.out.println("ID: " + getId() +
                " | Title: " + getTitle() +
                " | Year: " + getYear() +
                " | Issue Number: " + issueNumber);
    }
}