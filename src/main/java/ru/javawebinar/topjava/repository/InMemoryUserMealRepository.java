package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserMealRepository implements UserMealRepository{
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    /*{
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510));
    }*/

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
//        System.out.println("Meal save="+meal.getId());
        return repository.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}
