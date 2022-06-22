package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum with default time out
 */
@AllArgsConstructor
public enum TimeOuts {
    DEFAULT_TIMEOUT_IN_SECONDS(20);

    @Getter
    private long timeOutValue;
}
