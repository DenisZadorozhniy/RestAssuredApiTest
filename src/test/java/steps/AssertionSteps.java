package steps;

import io.qameta.allure.Step;
import models.ColorsData;
import models.UserData;
import models.registration.SuccessfulRegistration;
import models.registration.UnsuccessfulRegistration;
import org.testng.Assert;
import utills.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AssertionSteps {

    @Step("Check user avatar contains id user")
    public void checkUserAvatarContainsIdUser(List<UserData> data) {
        data.forEach(o -> Assert.assertTrue(o.getAvatar().contains(o.getId().toString())));
    }

    @Step("Check email contains @reqres.in")
    public void checkEmailContainsEmailProvider(List<UserData> data, String emailProvider) {
        data.forEach(o -> Assert.assertTrue(o.getEmail().contains(emailProvider)));
        // Еще как вариант
        // Assert.assertTrue(data.stream().allMatch(o->o.getEmail().endsWith(emailProvider)));
    }

    @Step("Check successful registration")
    public void checkSuccessfulRegistration(SuccessfulRegistration successfulRegistrationClass,
                                            String id, String token) {
        Assert.assertEquals(id, successfulRegistrationClass.getId().toString());
        Assert.assertEquals(token, successfulRegistrationClass.getToken());
    }

    @Step("Check unsuccessful registration")
    public void checkUnsuccessfulRegistration(UnsuccessfulRegistration unsuccessfulRegistration,
                                              String expected_error) {
        Assert.assertEquals(expected_error, unsuccessfulRegistration.getError());
    }

    @Step("Check if the list is sorted")
    public void isListSorted(List<ColorsData> list) {
        List<Integer> years = list.stream().map(ColorsData::getYear).collect(Collectors.toList());
        Assert.assertTrue(CollectionUtils.isListSorted(years));
    }
}
