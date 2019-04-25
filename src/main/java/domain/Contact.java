package domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

public class Contact {
    private int id;

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z ]+$", message = "Wrong name")
    private String name;

    @Past(message = "Birth date should be past")
    @Pattern(regexp = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))", message = "Date format is yyyy-mm-dd")
    private String birthDate;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Wrong gender")
    private String gender;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Wrong nationality")
    private String citizenship;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Wrong marriage status")
    private String marriageStatus;

    @Pattern(regexp = "(http|https)://.+", message = "Wrong web site")
    private String webSite;

    @Email(message = "Wrong email")
    private String eMail;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z ]+$", message = "Wrong place of work")
    private String placeOfWork;

    private Adress adress;

    private Phone phone;

    private ArrayList<Phone> list;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(int id, String name, String birthDate, String gender, String citizenship,
                   String marriageStatus, String webSite, String eMail, String placeOfWork, Adress adress) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.citizenship = citizenship;
        this.marriageStatus = marriageStatus;
        this.webSite = webSite;
        this.eMail = eMail;
        this.placeOfWork = placeOfWork;
        this.adress = adress;
    }

    public Contact(String name, String birthDate, String gender, String citizenship, String marriageStatus, String webSite, String eMail, String placeOfWork, Adress adress) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.citizenship = citizenship;
        this.marriageStatus = marriageStatus;
        this.webSite = webSite;
        this.eMail = eMail;
        this.placeOfWork = placeOfWork;
        this.adress = adress;
    }

    public Contact(int id, String name, String birthDate, String gender, String citizenship, String marriageStatus, String webSite, String eMail, String placeOfWork, Adress adress, Phone phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.citizenship = citizenship;
        this.marriageStatus = marriageStatus;
        this.webSite = webSite;
        this.eMail = eMail;
        this.placeOfWork = placeOfWork;
        this.adress = adress;
        this.phone = phone;
    }

    public Contact(String name, String birthDate, String gender, String citizenship, String marriageStatus, String webSite, String eMail, String placeOfWork, Adress adress, Phone phone) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.citizenship = citizenship;
        this.marriageStatus = marriageStatus;
        this.webSite = webSite;
        this.eMail = eMail;
        this.placeOfWork = placeOfWork;
        this.adress = adress;
        this.phone = phone;
    }

    public static class Builder{
        private Contact contact;

        public Builder(){
            contact = new Contact();
        }

        public Builder withId(int id){
            contact.id = id;
            return this;
        }

        public Builder withName(String name){
            contact.name = name;
            return this;
        }

        public Builder withBirthDate(String birthDate){
            contact.birthDate = birthDate;
            return this;
        }

        public Builder withGender(String gender){
            contact.gender = gender;
            return this;
        }

        public Builder withCitizenship(String nationality){
            contact.citizenship = nationality;
            return this;
        }

        public Builder withMarriageStatus(String marriageStatus){
            contact.marriageStatus = marriageStatus;
            return this;
        }

        public Builder withWebSite(String webSite){
            contact.webSite = webSite;
            return this;
        }

        public Builder withEmail(String email){
            contact.eMail = email;
            return  this;
        }

        public Builder withPlaceOfWork(String placeOfWork){
            contact.placeOfWork = placeOfWork;
            return this;
        }

        public Builder withAdress(Adress adress){
            contact.adress = adress;
            return this;
        }

        public Builder withList(ArrayList<Phone> list){
            contact.list = list;
            return this;
        }
        public Contact build(){
            return contact;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public ArrayList<Phone> getList() {
        return list;
    }

    public void setList(ArrayList<Phone> list) {
        this.list = list;
    }

}
