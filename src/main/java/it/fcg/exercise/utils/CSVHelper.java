package it.fcg.exercise.utils;

import it.fcg.exercise.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static final String TYPE = "text/csv";
    static final String[] HEADERs = { "id", "name", "surname", "email", "city", "address", "zip_code" };

    //controlla se un file ha il formato CSV o meno
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    //lettura di un file, restituzione di un elenco di User InputStream
    public static List<User> csvToUser(InputStream input) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<User> userList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                User user = new User(
                        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("name"),
                        csvRecord.get("surname"),
                        csvRecord.get("email"),
                        csvRecord.get("city"),
                        csvRecord.get("address"),
                        csvRecord.get("zipCode")
                );

                userList.add(user);
            }

            return userList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
