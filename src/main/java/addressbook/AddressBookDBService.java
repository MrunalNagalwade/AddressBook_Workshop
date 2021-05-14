package addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private static AddressBookDBService addressBookDBService;


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

    private List<Contact> getAddressbookContactData(ResultSet resultSet)
    {
        List<Contact> contactArrayList = new ArrayList<>();
        try{
            while (resultSet.next()) {
                String firstName = resultSet.getString("Name");
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
