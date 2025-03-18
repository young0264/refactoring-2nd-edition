package young.refactoring.ch9.renameField;

public class Organization {
    private String name;
    private String country;

    public Organization(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

}
