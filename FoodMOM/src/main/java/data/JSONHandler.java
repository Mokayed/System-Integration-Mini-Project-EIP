package data;

import entities.Food;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

public class JSONHandler {

    public static void main(String[] args) {
        JSONHandler j = new JSONHandler();
    }

    @SuppressWarnings("unchecked")
    public void readJson(List<Food> foods) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    System.getProperty("user.dir") + "/src/main/resources/foods.json"));
            JSONArray jsonArray = (JSONArray) obj;
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject next = iterator.next();
                for (Food food : foods) {
                    if (food.getName().equals(next.get("name"))) {
                        food.setDescription((String) next.get("description"));
                        food.setFood_category((String) next.get("food_group"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}