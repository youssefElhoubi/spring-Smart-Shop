package com.smartshop.component;

import com.smartshop.entity.Promo;
import com.smartshop.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Random;
import java.util.Set;

@Component
public class PromoScheduler {

    @Autowired
    private PromoRepository promoRepository;

    // Define your fixed holidays here (Month, Day)
    private static final Set<MonthDay> FIXED_HOLIDAYS = Set.of(
            MonthDay.of(1, 1),   // New Year
            MonthDay.of(5, 1),   // Labour Day
            MonthDay.of(11, 18), // Independence Day (Morocco example)
            MonthDay.of(12, 25)  // Christmas
    );

    // Cron: At 00:00:00am every day
    @Scheduled(cron = "0 0 0 * * *")
    public void generateHolidayPromos() {
        LocalDate today = LocalDate.now();

        if (isHoliday(today)) {
            System.out.println("🎉 It's a holiday! Generating promos...");

            // Generate 10 codes for example
            for (int i = 0; i < 10; i++) {
                String code = generatePromoCode();

                // Ensure uniqueness
                if (!promoRepository.existsByCode(code)) {
                    Promo promo = Promo.builder()
                            .code(code)
                            .used(false)
                            .build();
                    promoRepository.save(promo);
                }
            }
        } else {
            System.out.println("📅 Today is not a holiday. No promos generated.");
        }
    }

    private boolean isHoliday(LocalDate date) {
        MonthDay currentMonthDay = MonthDay.from(date);
        return FIXED_HOLIDAYS.contains(currentMonthDay);
    }

    private String generatePromoCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder("PROMO-");
        Random random = new Random();

        // Generate exactly 4 random characters to match regex {4}
        for (int i = 0; i < 4; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}
