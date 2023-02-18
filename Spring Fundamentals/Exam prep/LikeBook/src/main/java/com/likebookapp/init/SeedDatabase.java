package com.likebookapp.init;

import com.likebookapp.service.MoodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDatabase implements CommandLineRunner {

    private final MoodService moodService;

    public SeedDatabase(MoodService moodService) {
        this.moodService = moodService;
    }

    @Override
    public void run(String... args) throws Exception {
        moodService.seedMoods();
    }
}
