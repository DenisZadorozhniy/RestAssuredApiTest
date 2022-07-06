import models.ColorsData;
import models.UserData;
import models.registration.Register;
import models.registration.SuccessfulRegistration;
import models.registration.UnsuccessfulRegistration;
import org.testng.annotations.Test;
import steps.AssertionSteps;
import steps.DataSteps;
import utills.LoadFromProperties;

import java.util.List;

public class TestApi {
    private final DataSteps dataSteps = new DataSteps();
    private final AssertionSteps assertionSteps = new AssertionSteps();

    private final String emailProvider = LoadFromProperties.getConfigProperties("email_provider");
    private final String registration_email = LoadFromProperties.getConfigProperties("registration_email");
    private final String registration_password = LoadFromProperties.getConfigProperties("registration_password");
    private final String expected_id = LoadFromProperties.getConfigProperties("expected_id");
    private final String expected_token = LoadFromProperties.getConfigProperties("expected_token");
    private final String expected_error = LoadFromProperties.getConfigProperties("expected_error");
    private final String users_path = LoadFromProperties.getProperties("url.users_url");
    private final String title_list = LoadFromProperties.getConfigProperties("title_list");
    private final String unknown_path = LoadFromProperties.getProperties("url.unknown");
    private final String delete_user_path = LoadFromProperties.getProperties("url.delete_user");

    @Test
    public void test() {
        List<UserData> dataList = dataSteps.getValue(UserData.class, users_path, title_list);
        assertionSteps.checkUserAvatarContainsIdUser(dataList);
        assertionSteps.checkEmailContainsEmailProvider(dataList, emailProvider);

        Register registerClass = new Register(registration_email, registration_password);
        SuccessfulRegistration successfulRegistration = dataSteps.getSuccessfulRegistration(registerClass);
        assertionSteps.checkSuccessfulRegistration(successfulRegistration, expected_id, expected_token);
        Register unRegister = new Register(registration_email, "");
        UnsuccessfulRegistration unsuccessfulRegistration = dataSteps.getUnsuccessfulRegistration(unRegister);
        assertionSteps.checkUnsuccessfulRegistration(unsuccessfulRegistration, expected_error);

        List<ColorsData> colorsDataList = dataSteps.getValue(ColorsData.class, unknown_path, title_list);
        assertionSteps.isListSorted(colorsDataList);

        dataSteps.deleteUser(delete_user_path);
    }
}
