package addressbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVFile
{
    private static final String HOME = System.getProperty("user.dir");
    private static final String fName = "PersonDetail.csv";
    private static final Path homePath = Paths.get(HOME);

    //Method for read csv file
    public static boolean readCSVFile() throws IOException
    {
        if (Files.exists(homePath))
        {
            Path filePath = Paths.get(HOME + fName);
            try
            {
                if (Files.exists(filePath))
                {
                    FileReader filereader = new FileReader(String.valueOf(filePath));
                    CSVReader csvReader = new CSVReader(filereader);
                    String[] nextRecord;
                    while ((nextRecord = csvReader.readNext()) != null) {
                        for (String csvUser : nextRecord) {
                            System.out.print(csvUser + "\t");
                        }
                        System.out.println(" ");
                    }
                    return true;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean writeCSV(ArrayList<String[]> contactList) throws IOException
    {
        if (Files.exists(homePath))
        {
            Path filePath = Paths.get(HOME + "/" + fName);
            try {
                CSVWriter writer;
                if (Files.exists(filePath))
                {
                    FileWriter outputfile = new FileWriter(String.valueOf(filePath));
                    CSVWriter writeNext = new CSVWriter(outputfile);

                    for (String[] contact : contactList)
                    {
                        writeNext.writeNext(contact);
                    }

                    writeNext.flush();
                    writeNext.close();
                    return true;
                }
                else
                {
                    Files.createFile(filePath);
                    FileWriter outputfile = new FileWriter(String.valueOf(filePath));
                    CSVWriter writeNext = new CSVWriter(outputfile);
                    for (String[] contact : contactList)
                    {
                        writeNext.writeNext(contact);
                    }
                    writeNext.close();

                    return true;
                }
            }
            catch (IOException e)
            {
                return false;
            }
        }
        else
            return false;
    }
}