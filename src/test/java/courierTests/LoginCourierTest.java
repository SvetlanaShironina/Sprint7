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

public class LoginCourierTest {
    private CourierSteps courierSteps;
    private CreateCourierModel createCourierModel;
    private LoginCourierModel loginCourierModel;
    private CreateAndLoginCourierResult createAndLoginCourierResult;
    int courierID;

    @Before
    @Step("Создание тестовых данных курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        createCourierModel = new CreateCourierModel("13p3pp", "2pppp", "q22");
        loginCourierModel = LoginCourierModel.from(createCourierModel);
        createAndLoginCourierResult = new CreateAndLoginCourierResult();
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Проверяем, что курьер может авторизоваться")
    public void loginCourier(){
        ValidatableResponse responseLoginCourier = courierSteps.loginCourier(loginCourierModel);
        createAndLoginCourierResult.loginCourierOk(responseLoginCourier);
        courierID = responseLoginCourier.extract().path("id");
    }

    @Test
    @DisplayName("Логин курьера с пустым полем логина")
    @Description("Проверяем, что курьер не может войти в систему без логина")
    public void loginCourierWithoutLogin(){
        loginCourierModel.setLogin(null);
        ValidatableResponse loginCourierWithoutLogin = courierSteps.loginCourier(loginCourierModel);
        createAndLoginCourierResult.loginCourierError(loginCourierWithoutLogin);
    }

    @Test
    @DisplayName("Логин курьера с пустым полем пароля")
    @Description("Проверяем, что курьер не может войти в систему без пароля")
    public void loginCourierWithoutPassword(){
        loginCourierModel.setPassword("");
        ValidatableResponse loginCourierWithoutPassword = courierSteps.loginCourier(loginCourierModel);
        createAndLoginCourierResult.loginCourierError(loginCourierWithoutPassword);
    }

    @Test
    @DisplayName("Логин курьера с пустыми полями пароля и логина")
    @Description("Проверяем, что курьер не может войти в систему без пароля и логина")
    public void loginCourierWithoutPasswordAndLogin(){
        loginCourierModel.setLogin(null);
        loginCourierModel.setPassword("");
        ValidatableResponse loginCourierWithoutPasswordAndLogin = courierSteps.loginCourier(loginCourierModel);
        createAndLoginCourierResult.loginCourierError(loginCourierWithoutPasswordAndLogin);
    }
    @Test
    @DisplayName("Логин курьера с несуществующей парой логин-пароль")
    @Description("Проверяем, что курьер не может войти в систему с несуществующей парой логин-пароль")
    public void loginCourierNonExistingData(){
        loginCourierModel.setLogin("Aqaqa");
        loginCourierModel.setPassword("Aqaqa");
        ValidatableResponse loginCourierNonExistingData = courierSteps.loginCourier(loginCourierModel);
        createAndLoginCourierResult.loginCourierNonExistingData(loginCourierNonExistingData);
    }

    @After
    @Step("Удаление курьера")
    public void deleteCourier() {
        if (courierID != 0) {
            courierSteps.deletedCourier(courierID);
        }
    }
}
