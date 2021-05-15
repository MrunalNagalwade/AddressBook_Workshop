package addressbook;

import java.sql.ResultSet;
import java.util.List;

public class AddressBookService {
    public enum IOService{DB_IO}
    private List<Contact> contactList;
    private AddressBookDBService addressBookDBService;
    public AddressBookService()
    {
        addressBookDBService = addressBookDBService.getInstance();
    }
    public List<Contact> readPersonDetailsDataDB(IOService ioService){
        if(ioService.equals( IOService.DB_IO ))
            this.contactList =  addressBookDBService.readData();
        return this.contactList;
    }
    public void updateState(String firstName, String State)
    {
        int result = addressBookDBService.updateState(firstName, State);
        if (result == 0) return;
        Contact person = this.getAddressBookData(firstName);
        if (person != null) person.state = State;
    }
    private Contact getAddressBookData(String firstName)
    {
        return this.contactList.stream()
                .filter(addressBookDataItem -> addressBookDataItem.firstName.equals(firstName))
                .findFirst()
                .orElse(null);
    }
    public boolean checkDataWithDB(String firstName) {
        List<Contact> addressBookDataList = addressBookDBService.getAddressbookContactData(firstName);
        return addressBookDataList.get(0).equals(getData(firstName));
    }

    private Contact getData(String firstName) {
        return this.contactList.stream().filter(contactDetails -> contactDetails.firstName.equals(firstName)).findFirst().orElse(null);
    }
    public List<Contact> getPersonCity(IOService ioService, String city)
    {
        return addressBookDBService.getPersonCity(city);
    }
    public List<Contact> getPersonState(IOService ioService, String state)
    {
        return addressBookDBService.getPersonState( state);
    }
}
