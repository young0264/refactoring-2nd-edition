package young.refactoring.ch9.renameField;

public class Organization {
    private String title;
    private String country;

    public Organization(String name, String country) {
        this.title = name;
        this.country = country;
    }

    public String getName() {
        return this.title;
    }

    public String getCountry() {
        return this.country;
    }

}
