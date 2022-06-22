package testData;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountInfoData {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String pass;
}
