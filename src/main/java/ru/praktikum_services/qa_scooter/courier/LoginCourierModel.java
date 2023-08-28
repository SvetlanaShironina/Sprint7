package ru.praktikum_services.qa_scooter.courier;

public class LoginCourierModel {
    private String login;
    private String password;

    public LoginCourierModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginCourierModel from(CreateCourierModel createCourierModel){
        return new LoginCourierModel(createCourierModel.getLogin(), createCourierModel.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void getPassword(String password) {
        this.password = password;
    }
}
