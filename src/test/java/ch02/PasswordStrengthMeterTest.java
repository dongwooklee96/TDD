package ch02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    // 조건
    // 1. 길이가 8글자 이상
    // 2. 0 ~ 9 사이의 숫자를 포함
    // 3. 대문자 포함

    @DisplayName("입력이 NULL인 경우 테스트")
    @Test
    void nullInputThenInvalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @DisplayName("입력이 비어있는 경우 테스트")
    @Test
    void emptyInputThenInvalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @DisplayName("모든 조건을 충족하는 경우")
    @Test
    void meetsAllCriteriaThenStrong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @DisplayName("길이만 8글자 미만이고, 나머지 조건은 충족하는 경우")
    @Test
    void meetsOtherCriteriaExceptLengthThenNormal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고, 나머지 조건은 충족하는 경우")
    @Test
    void meetsOtherCriteriaExceptForNumberThenNormal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @DisplayName("대문자를 포함하지 않고, 나머지 조건을 충족하는 경우")
    @Test
    void meetsOtherCriteriaExceptForUppercaseThenNormal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
    @Test
    void meetsOnlyLengthCriteriaThenWeek() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @DisplayName("숫자 포함 조건만 충족하는 경우")
    @Test
    void meetsOnlyNumCriteriaThenWeek() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @DisplayName("대문자 포함 조건만 충족하는 경우")
    @Test
    void meetsOnlyUpperCriteriaThenWeak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @DisplayName("아무 조건도 충족하지 않은 경우")
    @Test
    void meetsNoCriteriaThenWeek() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
