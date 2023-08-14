package ru.praktikum_services.qa_scooter.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.praktikum_services.qa_scooter.constant.ApiEndpoints.*;

public class OrderSteps {

    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }

    @Step("Создание заказа")
    public ValidatableResponse creatingOrder(CreateOrderModel createOrderModel){
        return requestSpec()
                .body(createOrderModel)
                .when()
                .post(ORDER_POST_CREATE)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return requestSpec()
                .when()
                .get(ORDER_GET_LIST)
                .then();
    }
}
