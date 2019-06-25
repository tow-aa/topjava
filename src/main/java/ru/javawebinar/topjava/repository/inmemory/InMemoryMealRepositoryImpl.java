package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        if (meal.getUserId() != SecurityUtil.authUserId()) return null;
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        if (repository.get(id).getUserId() != SecurityUtil.authUserId()) return false;
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        if (repository.get(id).getUserId() != SecurityUtil.authUserId()) return null;
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> userMeal = new ArrayList<>();
    return repository.values().stream().filter(user -> user.getUserId() == SecurityUtil.authUserId())
            .sorted((o1,o2) -> -o1.getDate().compareTo(o2.getDate())).collect(Collectors.toList());
    }
}

