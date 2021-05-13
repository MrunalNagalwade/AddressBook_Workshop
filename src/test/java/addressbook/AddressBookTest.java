package addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AddressBookTest {
    AddressBook addressBook = new AddressBook();
    ArrayList<Contact> contact = new ArrayList<>();

    @Test
    public void whenPersonDetails_Added_ShouldReturn_True() {

        Contact person = new Contact("Mrunal", "nagalwade",
                "Ram nagar", "Bhandara", "Maharashtra", "mrun@gmail.com",
                9089987898L, 664011
        );
        contact.add(person);
        System.out.println(contact.toString());
        boolean result = addressBook.addContact(contact);
        Assertions.assertTrue(result);
    }
}
