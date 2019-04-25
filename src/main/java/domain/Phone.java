package domain;

import javax.validation.constraints.Pattern;

public class Phone {
    private int contact_id;

    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$", message = "Wrong phone number")
    private String number;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Wrong phone description")
    private String description;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z ]+$", message = "Wrong symbols comment")
    private String comment;

    public Phone(int contact_id, String number, String description, String comment) {
        this.contact_id = contact_id;
        this.number = number;
        this.description = description;
        this.comment = comment;
    }

    public Phone(String number, String description, String comment) {
        this.number = number;
        this.description = description;
        this.comment = comment;
    }

    public Phone() {
    }

    public static class Builder{
        private Phone phone;

        public Builder(){
            phone = new Phone();
        }

        public Builder withContactId(int contactId){
            phone.contact_id = contactId;
            return this;
        }

        public Builder withNumber(String number){
            phone.number = number;
            return this;
        }

        public Builder withDescription(String description){
            phone.description = description;
            return this;
        }

        public Builder withComment(String comment){
            phone.comment = comment;
            return this;
        }

        public Phone build(){
            return phone;
        }
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String phone) {
        this.number = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
