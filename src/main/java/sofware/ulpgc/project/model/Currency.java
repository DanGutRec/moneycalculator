package sofware.ulpgc.project.model;

public class Currency{
    private final String code;
    private final String country;

    private Currency(String code, String country) {
        this.code = code;
        this.country = country;
    }

    public static Currency create(String code,String country){
        return  new Currency(code,country);
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }
}
