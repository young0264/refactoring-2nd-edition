package young.refactoring.ch9.renameField;

public class Organization {
    private String title;
    private String country;

    public Organization(String title, String country) {
        this.title = title;
        this.country = country;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCountry() {
        return this.country;
    }

}
