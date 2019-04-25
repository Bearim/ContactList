package domain;

import javax.validation.constraints.Pattern;

public class Adress {

    private int contact_id;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String country;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String city;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String street;

    private int home;
    private int apartment;
    private int postcode;

    public Adress(int contact_id, String country, String city, String street, int home, int apartment, int postcode) {
        this.contact_id = contact_id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
        this.postcode = postcode;
    }

    public Adress(String country, String city, String street, int home, int apartment, int postcode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
        this.postcode = postcode;
    }

    public Adress() {
    }

    public static class Builder{
        private Adress adress;

        public Builder(){
            adress = new Adress();
        }

        public Builder withContactId(int contactId){
            adress.contact_id = contactId;
            return this;
        }

        public Builder withCountry(String country){
            adress.country = country;
            return this;
        }

        public Builder withCity(String city){
            adress.city = city;
            return this;
        }

        public Builder withStreet(String street){
            adress.street = street;
            return this;
        }

        public Builder withHome(int home){
            adress.home = home;
            return this;
        }

        public Builder withApartment(int apartment){
            adress.apartment = apartment;
            return this;
        }

        public Builder withPostcode(int postcode){
            adress.postcode = postcode;
            return this;
        }

        public Adress build(){
            return adress;
        }
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int appartment) {
        this.apartment = appartment;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {

        String adress = "";
        if (!this.getCountry().equals("")){
            adress+=this.getCountry()+" ";
        }
        if (!this.getCity().equals("")){
            adress+=this.getCity()+" ";
        }
        if (!this.getStreet().equals("")){
            adress+=this.getStreet()+" ";
        }
        if (this.getHome()!=0){
            adress+=this.getHome()+" ";
        }
        if (this.getApartment()!=0){
            adress+=this.getApartment()+" ";
        }
        if (this.getPostcode()!=0){
            adress+= "Postcode: "+this.getPostcode();
        }
        return adress;
    }
}
