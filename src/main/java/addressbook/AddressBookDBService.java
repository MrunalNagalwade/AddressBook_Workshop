package addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private static AddressBookDBService addressBookDBService;
    private PreparedStatement addressBookDataStatement;


    public static AddressBookDBService getInstance()
    {
        if(addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    private Connection getConnection() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/Address_book_serviceDB";
        String username = "root";
        String password = "MrunalNagalwade123";
        Connection connection;
        System.out.println("Connecting to database:" + url);
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection is successful!!!" + connection);
        return connection;
    }

    public List<Contact> readData()
    {
        String sql = "SELECT * FROM  address_book_service;";
        return this.getContacttDetailDatabase(sql);
    }

    private List<Contact> getContacttDetailDatabase(String query)
    {
        List<Contact> contactListdb = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            contactListdb = this.getAddressbookContactData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactListdb;
    }

    List<Contact> getAddressbookContactData(ResultSet resultSet)
    {
        List<Contact> contactArrayList = new ArrayList<>();
        try{
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("Lastname");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                int zip = resultSet.getInt("Zip");
                long mobileNo = resultSet.getLong("Phone_number");
                String email = resultSet.getString("Email");
                contactArrayList.add(new Contact(firstName, lastName, address, city, state, email, mobileNo, zip));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return contactArrayList;
    }
    public List<Contact>getAddressbookContactData(String firstName)
    {
        List<Contact> personList = null;
        if (this.addressBookDataStatement == null)
            this.prepareStatementForAddressBookData();
        try {
            addressBookDataStatement.setString(1, firstName);
            ResultSet resultSet = addressBookDataStatement.executeQuery();
            personList = this.getAddressbookContactData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }
    private void prepareStatementForAddressBookData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM address_book_service WHERE firstName = ?";
            addressBookDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int updateState(String firstName, String State)
    {
        return this.updateAddressBookDataUsingStatement(firstName, State);
    }
    private int updateAddressBookDataUsingStatement(String firstName, String State)
    {
        String sql = String.format("update address_book_service set State = '%s' where firstName = '%s';", State, firstName);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/Address_book_serviceDB";
        String username = "root";
        String password = "MrunalNagalwade123";
        Connection connection;
        System.out.println("Connecting to database:" + url);
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection is successful!!!" + connection);
    }
}
