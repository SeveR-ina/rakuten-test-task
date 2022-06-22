package testData;

import static net.andreinc.mockneat.types.enums.PassStrengthType.STRONG;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

/**
 * Class for generating a new test user randomly.
 */
public class UserGenerator {

    /**
     * Builds a new user.
     */
    public static AccountInfoData getRandomUser(String domain) {
        return AccountInfoData.builder()
                .email(emails().domain(domain).get())
                .firstName(names().first().get())
                .lastName(names().last().get())
                .pass("A1" + passwords().type(STRONG).get() + passwords().type(STRONG).get())
                .build();
    }
}
