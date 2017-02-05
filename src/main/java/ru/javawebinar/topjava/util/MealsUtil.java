package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {

        List<MealWithExceed> mealsWithExceeded = getWithExceeded(MEALS, DEFAULT_CALORIES_PER_DAY);
//        mealsWithExceeded.forEach(System.out::println);

//      реализация при помощи цикла
//        System.out.println(getFilteredWithExceededByCycle(MEALS, LocalTime.of(7, 0), LocalTime.of(12, 0), DEFAULT_CALORIES_PER_DAY));
    }



    public static List<MealWithExceed> getWithExceeded(Collection<Meal> mealList, int CaloriesPerDay){
//        mealList.forEach(m-> System.out.println(m));
        return getFilteredWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, CaloriesPerDay);
    }

    //Реализация используя stream
    public static List<MealWithExceed>  getFilteredWithExceeded(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumByDate = meals.stream().
                collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }


    //Реализация используя цикл
    public static List<MealWithExceed>  getFilteredWithExceededByCycle(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();

        for(Meal meal: mealList){
            caloriesSumPerDate.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
        }

        List<MealWithExceed> mealExceeded = new ArrayList<>();
        for(Meal meal: mealList){
            LocalDateTime dateTime = meal.getDateTime();
            if(DateTimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)){
                mealExceeded.add(createWithExceed(meal, caloriesSumPerDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
    return mealExceeded;
    }
}
