package utils;

import domain.Adress;
import domain.Contact;
import domain.Phone;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class DomainUtils {

    public static Contact createContact(HttpServletRequest request){
        Contact contact = new Contact.Builder()
                .withName(request.getParameter("name")+" "+request.getParameter("surname")+" "+request.getParameter("patrynomic"))
                .withPlaceOfWork(request.getParameter("placeOfWork"))
                .withGender(request.getParameter("gender"))
                .withCitizenship(request.getParameter("nationality"))
                .withMarriageStatus(request.getParameter("mariageStatus"))
                .withWebSite(request.getParameter("webSite"))
                .withEmail(request.getParameter("eMail"))
                .withAdress(createAdress(request))
                .withList(makePhoneList(request))
                .build();
                if(request.getParameter("birthDate").equals("")){
                    return contact;
                } else {
                    contact.setBirthDate(request.getParameter("birthDate"));
                }
                return contact;
    }

    public static Contact createContact(HttpServletRequest request, int id){
        Contact contact = new Contact.Builder()
                .withId(Integer.parseInt(request.getParameter("id")))
                .withName(request.getParameter("name"))
                .withPlaceOfWork(request.getParameter("placeOfWork"))
                .withGender(request.getParameter("gender"))
                .withCitizenship(request.getParameter("nationality"))
                .withMarriageStatus(request.getParameter("mariageStatus"))
                .withWebSite( request.getParameter("webSite"))
                .withEmail(request.getParameter("eMail"))
                .withAdress(createAdress(request))
                .withList(makePhoneList(request))
                .build();
        if(request.getParameter("birthDate").equals("")){
            return contact;
        } else {
            contact.setBirthDate(request.getParameter("birthDate"));
        }

        return contact;
    }
    public static Adress createAdress(HttpServletRequest request){
        if(request.getParameter("country")!=null) {
            Adress adress = new Adress.Builder()
                    .withCountry(request.getParameter("country"))
                    .withCity(request.getParameter("city"))
                    .withStreet(request.getParameter("street"))
                    .build();
            if (!(request.getParameter("home").equals(""))) {
                adress.setHome(Integer.parseInt(request.getParameter("home")));
            }
            if (!(request.getParameter("apartment").equals(""))) {
                adress.setApartment(Integer.parseInt(request.getParameter("apartment")));
            }
            if (!(request.getParameter("postcode").equals(""))) {
                adress.setPostcode(Integer.parseInt(request.getParameter("postcode")));
            }
            return adress;
        }
        return null;
    }

    public static Phone createPhone(HttpServletRequest request, int id){
        Phone phone = new Phone.Builder()
                .withNumber(request.getParameter("number"))
                .withDescription(request.getParameter("description"))
                .withComment(request.getParameter("comment"))
                .withContactId(id)
                .build();
        return phone;
    }

    public static ArrayList<Phone> makePhoneList(HttpServletRequest request){
        ArrayList<Phone> list = new ArrayList<Phone>();

        String[] numbers = request.getParameterValues("numbers");
        String[] descriptions = request.getParameterValues("descriptions");
        String[] comments = request.getParameterValues("comments");

        if (numbers!=null) {
            for (int i = 0; i < numbers.length; i++) {
                list.add(new Phone.Builder()
                        .withNumber(numbers[i])
                        .withDescription(descriptions[i])
                        .withComment(comments[i])
                        .build());
            }
        }
        return list;
    }

}
