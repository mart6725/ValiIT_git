package ee.bcs.valiit.NOTES;

public class requestBodyDTO {
    //DTO data trannsfer object , all private ja getter setter
    //access modifiers

    //public (igalt poolt) and private (ainult samas klassis)

    // N2iDE*****************************

    private String name;
    private String author;
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
