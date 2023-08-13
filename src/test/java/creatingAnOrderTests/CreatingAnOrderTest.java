package creatingAnOrderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.order.CreateOrderModel;
import ru.praktikum_services.qa_scooter.order.CreatingOrderResult;
import ru.praktikum_services.qa_scooter.order.OrderSteps;

import java.util.Arrays;

public class CreatingAnOrderTest {
    private OrderSteps orderSteps;
    private CreateOrderModel createOrderModel;
    private CreatingOrderResult creatingOrderResult;

    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
        creatingOrderResult = new CreatingOrderResult();
    }

    @Test
    @DisplayName("Создание заказа с самокатами разных цветов")
    @Description("Цвет самоката - BLACK, GREY")
    public void createOrderColorBlackAndGrey(){
        createOrderModel = new CreateOrderModel((Arrays.asList("BLACK", "GREY")));
        ValidatableResponse createOrderColorBlackAndGrey = orderSteps.creatingOrder(createOrderModel);
        creatingOrderResult.creatingOrderResult(createOrderColorBlackAndGrey);
    }

    @Test
    @DisplayName("Создание заказа с самокатами разных цветов")
    @Description("Цвет самоката - BLACK")
    public void createOrderColorBlack(){
        createOrderModel = new CreateOrderModel((Arrays.asList("BLACK")));
        ValidatableResponse createOrderColorBlack = orderSteps.creatingOrder(createOrderModel);
        creatingOrderResult.creatingOrderResult(createOrderColorBlack);
    }

    @Test
    @DisplayName("Создание заказа с самокатами разных цветов")
    @Description("Цвет самоката - GREY")
    public void createOrderColorGrey(){
        createOrderModel = new CreateOrderModel((Arrays.asList("GREY")));
        ValidatableResponse createOrderColorGrey = orderSteps.creatingOrder(createOrderModel);
        creatingOrderResult.creatingOrderResult(createOrderColorGrey);
    }

    @Test
    @DisplayName("Создание заказа с самокатами разных цветов")
    @Description("Цвет самоката - не указан")
    public void createOrderNoColor(){
        createOrderModel = new CreateOrderModel((Arrays.asList()));
        ValidatableResponse createOrderNoColor = orderSteps.creatingOrder(createOrderModel);
        creatingOrderResult.creatingOrderResult(createOrderNoColor);
    }
}
