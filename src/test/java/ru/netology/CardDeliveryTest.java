package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {

    private String date(long days, String format) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(format));
    }

    @Test
    public void succsessCardDelivery() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String valueDate = date(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(valueDate);
        $("[data-test-id='name'] input").setValue("Егоров Сергей");
        $("[data-test-id='phone'] input").setValue("+79262665676");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + valueDate));


    }
}
