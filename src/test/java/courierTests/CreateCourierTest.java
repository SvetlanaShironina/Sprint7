package courierTests;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
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

import java.util.Locale;

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
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String loginRandom = fakeValuesService.bothify("????###");

        createCourierModel.setLogin(loginRandom);
        ValidatableResponse createCourierOk = courierSteps.createCourier(createCourierModel);
        createAndLoginCourierResult.createCourierOk(createCourierOk);
        LoginCourierModel loginCourierModel = LoginCourierModel.from(createCourierModel);
        courierID = courierSteps.loginCourier(loginCourierModel).extract().jsonPath().getInt("id");
    }

    @Test
    @DisplayName("Создание курьера с уже существующими данными")
    @Description("Проверяем, что курьера нельзя создать с уже существующими данными")
    public void createCourierExistingData() {
        courierSteps.createCourier(createCourierModel);
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