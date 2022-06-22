package testData;

import lombok.Builder;
import lombok.Getter;

/**
 * POJO class for a new test user.
 */
@Getter
@Builder
public class AccountInfoData {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String pass;

    @Override
    public String toString() {
        return "User:{" +
                "email:'" + email + '\'' +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", pass:'" + pass + '\'' +
                '}';
    }
}
