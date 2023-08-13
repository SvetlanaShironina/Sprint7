package creatingAnOrderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.order.OrderSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GettingListOrdersTest {
    private OrderSteps orderSteps;

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяем, что список заказов успешно получен, что он не пустой")
    public void orderList() {
        orderSteps = new OrderSteps();
        ValidatableResponse orderList = orderSteps.getOrderList();
        orderList.assertThat()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}
