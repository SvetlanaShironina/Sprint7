package courierTests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.courier.CourierSteps;
import ru.praktikum_services.qa_scooter.courier.CreateAndLoginCourierResult;
import ru.praktikum_services.qa_scooter.courier.CreateCourierModel;
import ru.praktikum_services.qa_scooter.courier.LoginCourierModel;

public class CreateCourierTest {
    private CourierSteps courierSteps;
    private CreateCourierModel createCourierModel;
    private CreateAndLoginCourierResult createAndLoginCourierResult;

    int courierID;

    @Before
    @Step("Создание тестовых данных курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        createCourierModel = new CreateCourierModel("Login33", "2pppp", "Maks");
        createAndLoginCourierResult = new CreateAndLoginCourierResult();
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверяем, что курьера можно создать")
    public void createCourierOk() {
        ValidatableResponse createCourierOk = courierSteps.createCourier(createCourierModel);
        courierID = courierSteps.loginCourier(LoginCourierModel.from(createCourierModel)).extract().path("id");
        createAndLoginCourierResult.createCourierOk(createCourierOk);
    }

    @Test
    @DisplayName("Создание курьера с уже существующими данными")
    @Description("Проверяем, что курьера нельзя создать с уже существующими данными")
    public void createCourierExistingData() {
        courierSteps.createCourier(createCourierModel);
        courierID = courierSteps.loginCourier(LoginCourierModel.from(createCourierModel)).extract().path("id");
        ValidatableResponse createCourierExistingData = courierSteps.createCourier(createCourierModel);
        createAndLoginCourierResult.createCourierExistingData(createCourierExistingData);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем логина")
    @Description("Проверяем, что курьера нельзя создать без логина")
    public void createCourierWithoutLogin() {
        createCourierModel.setLogin(null);
        ValidatableResponse createCourierWithoutLogin = courierSteps.createCourier(createCourierModel);
        createAndLoginCourierResult.createCourierError(createCourierWithoutLogin);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем пароля")
    @Description("Проверяем, что курьера нельзя создать без пароля")
    public void createCourierWithoutPassword() {
        createCourierModel.setPassword(null);
        ValidatableResponse createCourierWithoutPassword = courierSteps.createCourier(createCourierModel);
        createAndLoginCourierResult.createCourierError(createCourierWithoutPassword);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем пароля")
    @Description("Проверяем, что курьера нельзя создать без пароля")
    public void createCourierWithoutPasswordAndLogin() {
        createCourierModel.setLogin(null);
        createCourierModel.setPassword(null);
        ValidatableResponse createCourierWithoutPasswordAndLogin = courierSteps.createCourier(createCourierModel);
        createAndLoginCourierResult.createCourierError(createCourierWithoutPasswordAndLogin);
    }

    @After
    @Step("Удаление тестовых данных курьера")
    public void deletedCourier(){
        if(courierID != 0) {
            courierSteps.deletedCourier(courierID);
        }
    }
}