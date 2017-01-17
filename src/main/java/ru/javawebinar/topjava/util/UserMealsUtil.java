package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static final List<UserMeal> MEAL_LIST = Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
    );

    public static void main(String[] args) {
        List<UserMealWithExceed> filteredMealsWithExceeded = getWithExceeded(MEAL_LIST, 2000);

//        реализация при помощи цикла
        getFilteredWithExceededByCycle(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(m-> System.out.println(m));
    }

    public static List<UserMealWithExceed> getWithExceeded(Collection<UserMeal> mealList, int CaloriesPerDay){
        return getFilteredWithExceeded((List<UserMeal>) mealList, LocalTime.MIN, LocalTime.MAX, CaloriesPerDay);
    }

    //Реализация используя strim
    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));
        return mealList.stream()
                .filter(um->TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDateTime()) > caloriesPerDay))
                .collect(Collectors.toList());

    }

    public static UserMealWithExceed createWithExceed(UserMeal um, boolean exceeded){
        return new UserMealWithExceed(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories(), exceeded);
    }


    //Реализация используя цикл
    public static List<UserMealWithExceed>  getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();

        for(UserMeal meal: mealList){
            caloriesSumPerDate.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
        }

        List<UserMealWithExceed> mealExceeded = new ArrayList<>();
        for(UserMeal meal: mealList){
            LocalDateTime dateTime = meal.getDateTime();
            if(TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)){
                mealExceeded.add(createWithExceed(meal, caloriesSumPerDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
    return mealExceeded;
    }
}
