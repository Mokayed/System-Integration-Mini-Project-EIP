package rpc;

import entities.Food;
import java.util.List;

public class MessageHandler {
    private static String categories(List<Food> foods) {

        String message = "";
        for (Food food : foods) {
            if (!message.contains(food.getFood_category())) {

                message += food.getFood_category() + "\n";
            }

        }
        return message;
    }

    private static String pickFoodCategory(String category, List<Food> foods) {
        String foodList = "";
        for (Food food : foods) {
            if (food.getFood_category().equals(category)) {
                foodList += food.getName() + "\n";
            }
        }
        if (!foodList.isEmpty()) {
            foodList += "please pick one of the foods";
        }
        return foodList;
    }

    private static String chooseFood(String foodChoice, List<Food> foods) {
        String foodMessage = "";
        for (Food food : foods) {
            if (food.getName().equals(foodChoice)) {
                foodMessage += food.getDescription() + "\n type 'pay' if you wish to purchase,type 'cancel' if you do not " + foodChoice;
            }
        }
        return foodMessage;
    }

    public String handleMessages(String message, List<Food> foods) {
        String response = "";
        if(message.equals("getCategories")){

            response+=categories(foods);
        } else {
            if(message.contains("pay") || message.contains("cancel")){
                if(message.contains("pay")){
                    System.out.println("message was: " + message);
                    response+= "you have paid for: " + message.split(" ")[1];
                    return response;
                }
                if(message.contains("cancel")){
                    response+= "you canceled purchase for " + message.split(" ")[1];
                    return response;
                }
            }
            if(pickFoodCategory(message,foods).equals("")){
                if(chooseFood(message,foods).equals("")){
                    response+= "no such food/category exists";
                }
            }
            if(!pickFoodCategory(message,foods).equals("") && !chooseFood(message,foods).equals("")){
                response+= pickFoodCategory(message,foods);
            } else {
                if(!pickFoodCategory(message,foods).equals("")){
                    response+= pickFoodCategory(message,foods);
                }
                if(!chooseFood(message,foods).equals("")){
                    response+= chooseFood(message,foods);
                }
            }
        }
        return response;
    }
}