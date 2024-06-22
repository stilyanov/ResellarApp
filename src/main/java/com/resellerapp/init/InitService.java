package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InitService implements CommandLineRunner {

    private final Map<ConditionEnum, String> descriptions = Map.of(
            ConditionEnum.EXCELLENT, "In perfect condition",
            ConditionEnum.GOOD, "Some signs of wear and tear or minor defects",
            ConditionEnum.ACCEPTABLE, "The item is fairly worn but continues to function properly"
    );

    private final ConditionRepository conditionRepository;

    public InitService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (conditionRepository.count() > 0) {
            return;
        }

        List<Condition> toInsert = Arrays.stream(ConditionEnum.values())
                .map(condition -> new Condition(condition, descriptions.get(condition)))
                .collect(Collectors.toList());

        conditionRepository.saveAll(toInsert);
    }
}
