package addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Test
    public void givenCSVFile_WhenRead_ShouldReturn_True() throws IOException
    {
        OpenCSVFile openCSVReader = new OpenCSVFile();
        Boolean result = openCSVReader.readCSVFile();
        Assertions.assertTrue(result);
    }
    @Test
    public void givenCSVFile_WhenWritten_ShouldReturn_True() throws IOException {
        ArrayList<String[]> contactdetail = new ArrayList<>();
        String[] person =
                {"puja", "Nagalwade", "Shivaji nagar", "Pune", "Maharashtra", "puja@gmail.com", "9087897898", "660010"
                };
        String[] person2 =
                {"Riya", "Urkude", "Shivaji nagar", "Pune", "Maharashtra", "puja@gmail.com", "9087897898", "660010"
                };

        contactdetail.add(person);
        contactdetail.add(person2);
        System.out.println(contactdetail.add(person));

        OpenCSVFile  openCSVReader = new OpenCSVFile();
        Boolean result = openCSVReader.writeCSV(contactdetail);
        Assertions.assertTrue(result);
    }
    @Test
    public void whenPersonDetails_Added_ShouldReturnTRUE()
    {
        ArrayList<Contact> jsoncontact = new ArrayList<>();
        Contact jsoncontact1 = new Contact("Subodh", "Nag", "Dombiovali", "Mumbai", "Maharashtra", "subodh@gmail.com", 9089898989L, 660078
                );
        Contact jsoncontact2 = new Contact ("Raghav", "Pandey", "Dombiovali", "Mumbai", "Maharashtra", "subodh@gmail.com", 9089898989L, 660078
                );
        jsoncontact.add(jsoncontact1);
        jsoncontact.add(jsoncontact2);
        AddressBookJson jsonfile = new AddressBookJson();
        System.out.println(jsoncontact);
        boolean jsonResult = jsonfile.jsonWriteData(jsoncontact);
        Assertions.assertTrue(jsonResult);
    }

    @Test
    public void whenJsonFile_Added_ShouldReturnTRUE()
    {
        AddressBookJson addressBookJson = new AddressBookJson();
        boolean jsonResult1 = addressBookJson.jsonReadData();
        Assertions.assertTrue(jsonResult1);
    }
    @Test
    public void givenAddressBookPersonsDetailsDB_WhenRetrieved_ShouldMatchCount()
    {
        AddressBookService addressBookService = new AddressBookService();
        List<Contact> contactList = addressBookService.readPersonDetailsDataDB(AddressBookService.IOService.DB_IO);
        System.out.println(contactList.toString());
        Assertions.assertEquals(7,contactList.size());
    }
    @Test
    public void givenAddressBookDetails_WhenUpdated_ShouldReturnTrue()
    {
        AddressBookService addressBookService = new AddressBookService();
        List<Contact> personList = addressBookService.readPersonDetailsDataDB(AddressBookService.IOService.DB_IO);
        addressBookService.updateState("Radha","Karnataka");
        boolean updateresult = addressBookService.checkDataWithDB("Radha");
        Assertions.assertTrue(updateresult);

    }
    @Test
    public void whenStateFromDB_CountRetrieved_ShouldReturnTrue()
    {
        AddressBookService addressBookService = new AddressBookService();
        List<Contact> addressBookCityList =
                addressBookService.getPersonCity(AddressBookService.IOService.DB_IO,
                        "Bhandara");
        System.out.println(addressBookCityList.toString());
        List<Contact> addressBookStateList =
                addressBookService.getPersonState(AddressBookService.IOService.DB_IO, "MH");
        System.out.println(addressBookStateList.toString());
        Assertions.assertEquals(1, addressBookCityList.size());
        Assertions.assertEquals(2, addressBookStateList.size());
    }
    @Test
    public void given4Entries_WhenAdded_ShouldGetAdded()
    {
        Contact[] arrayOfContactOne = new Contact[]{
                new Contact("Sayi", "Verma", "Shiv nagar",
                        "Gondia", "MH", "s@gmail.com", 8909878987L, 611000, LocalDate.now()),
                new Contact("Sonali", "Wani", "Amaravati",
                        "Amaravati ", "Mh", "a@gmail.com", 9087897867L, 611001, LocalDate.now()),

        };
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.readPersonDetailsDataDB(AddressBookService.IOService.DB_IO);
        Instant start = Instant.now();
        addressBookService.multipleDataAdded(Arrays.asList(arrayOfContactOne));
        Instant end = Instant.now();
        System.out.println("Time Took: " + Duration.between(start, end));
        boolean status = addressBookService.checkDataInDB("Sonali");
        Assertions.assertTrue(status);
    }

}
