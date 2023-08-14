package ru.praktikum_services.qa_scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.greaterThan;

public class CreatingOrderResult {

    @Step("Успешное создание заказа")
    public int creatingOrderResult(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(SC_CREATED)
                .body("track", greaterThan(0))
                .extract()
                .path("track");
    }
}
