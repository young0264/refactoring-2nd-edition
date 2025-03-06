package young.refactoring.ch7.encapsulateRecord;

public class Organization {
    private String name;
    private String country;

    public Organization(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter & Setter for Country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // toString() 오버라이드 (객체 정보를 보기 쉽게 출력)
    @Override
    public String toString() {
        return "Organization{name='" + name + "', country='" + country + "'}";
    }

    // equals() 오버라이드 (객체 비교를 위해)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Organization that = (Organization) obj;
        return name.equals(that.name) && country.equals(that.country);
    }

    // hashCode() 오버라이드 (객체 해시값 비교를 위해)
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}