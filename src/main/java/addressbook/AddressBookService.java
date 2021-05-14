package addressbook;

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
}
