package steps;

import api.Specification;
import models.registration.Register;
import models.registration.SuccessfulRegistration;
import models.registration.UnsuccessfulRegistration;
import utills.LoadFromProperties;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DataSteps {
    private final static String URL = LoadFromProperties.getProperties("url.base_url");
    private final static String REGISTER_USER_PATH = LoadFromProperties.getProperties("url.register_user");


    public <T> List<T> getValue(Class<T> className, String path, String titleList) {
        Specification.installSpecification(Specification.requestSpecification(URL),
                Specification.responseSpec(200));

        return given()
                .when()
                .get(path)
                .then()
                .extract().body().jsonPath().getList(titleList, className);
    }

    public SuccessfulRegistration getSuccessfulRegistration(Register registerClass) {
        Specification.installSpecification(Specification.requestSpecification(URL),
                Specification.responseSpec(200));

        return given()
                .body(registerClass)
                .when()
                .post(REGISTER_USER_PATH)
                .then()
                .extract()
                .as(SuccessfulRegistration.class);
    }

    public UnsuccessfulRegistration getUnsuccessfulRegistration(Register unRegisterClass) {
        Specification.installSpecification(Specification.requestSpecification(URL),
                Specification.responseSpec(400));
        return given()
                .body(unRegisterClass)
                .when()
                .post(REGISTER_USER_PATH)
                .then()
                .extract()
                .as(UnsuccessfulRegistration.class);
    }

    public void deleteUser(String delete_user_path) {
        Specification.installSpecification(Specification.requestSpecification(URL),
                Specification.responseSpec(204));
        given()
                .when()
                .delete(delete_user_path);
    }
}
