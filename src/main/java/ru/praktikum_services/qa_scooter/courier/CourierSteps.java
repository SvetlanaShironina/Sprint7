package ru.praktikum_services.qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.praktikum_services.qa_scooter.constant.ApiEndpoints;

import static io.restassured.RestAssured.given;
import static ru.praktikum_services.qa_scooter.constant.ApiEndpoints.*;

public class CourierSteps {
    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(ApiEndpoints.BASE_URL);
    }
    @Step("Регистрация нового курьера")
    public ValidatableResponse createCourier(CreateCourierModel createCourierModel){
        return requestSpec()
                .body(createCourierModel)
                .when()
                .post(COURIER_POST_CREATE)
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse loginCourier(LoginCourierModel loginCourierModel){
        return requestSpec()
                .body(loginCourierModel)
                .when()
                .post(COURIER_POST_LOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deletedCourier(int courierID){
        return requestSpec()
                .when()
                .delete(COURIER_DELETE + courierID)
                .then();
    }
}
