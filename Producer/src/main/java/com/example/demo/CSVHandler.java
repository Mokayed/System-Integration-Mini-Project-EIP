package com.example.demo;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVHandler {

    public static void main(String[] args) throws IOException {
        CSVHandler c = new CSVHandler();

    }


    public void readCsv(List<Food> foods) throws IOException {
        CSVReader reader = new CSVReader(new FileReader("/home/hvn15/IdeaProjects/System-Integration-Mini-Project-EIP/Producer/src/main/resources/foods.csv"));
        List<String[]> myEntries = reader.readAll();

        for(String[] s : myEntries){
            foods.add(new Food(s[1],"", ""));

        }
    }

}
