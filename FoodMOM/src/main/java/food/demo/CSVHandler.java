package food.demo;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    public void readCsv(List<Food> foods) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/src/main/resources/foods.csv"));
        List<String[]> myEntries = reader.readAll();

        for(String[] s : myEntries){
            foods.add(new Food(s[1],"", ""));

        }
    }
}
