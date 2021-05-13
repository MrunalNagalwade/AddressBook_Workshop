package addressbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AddressBookJson {
    private static final String HOME = System.getProperty("user.dir");
    private static final String fileName = "AddressBookJson.json";
    private static final Path homePath = Paths.get(HOME);

    private static final Gson gson = new GsonBuilder().create();

    //Method for read persond details
    public boolean jsonReadData()
    {
        if (Files.exists(homePath))
        {
            Path filePath = Paths.get(HOME + "/" + fileName);
            try {
                if (Files.exists(filePath))
                {
                    BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filePath)));
                    Contact contactReader = gson.fromJson(reader, Contact.class);
                    return true;
                }
            } catch (IOException e)
            {
                return false;
            }
        }
        return true;
    }
    public boolean jsonWriteData(ArrayList<Contact> jsoncontact)
    {
        if (Files.exists(homePath))
        {
            Path filePath = Paths.get(HOME + "/" + fileName);
            try {

                if (Files.exists(filePath))
                {
                    String s = gson.toJson(jsoncontact);
                    FileWriter fileWriter = new FileWriter(String.valueOf(filePath));
                    fileWriter.write(s);
                    fileWriter.close();
                    return true;
                } else {
                    Files.createFile(filePath);
                    String s = gson.toJson(jsoncontact);
                    FileWriter fileWriter = new FileWriter(String.valueOf(filePath));
                    fileWriter.write(s);
                    fileWriter.close();
                    return true;
                }
            } catch (IOException e)
            {
                return false;
            }
        }
        return true;
    }


}
