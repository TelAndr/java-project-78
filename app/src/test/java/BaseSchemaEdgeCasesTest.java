//import hexlet.code.schemas.BaseSchema;
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

public class BaseSchemaEdgeCasesTest {
    @Test
    void classCastExceptionHandled() {
        // создаём StringSchema, но передаём Integer - проверяем, что не выбрасывается исключение, а возвращается false
        final int valMinLength = 1;
        final int numValCheck = 123;
        StringSchema schema = new StringSchema(String.class).minLength(valMinLength);
        assertFalse(schema.isValid(numValCheck)); // должен вернуть false, не бросать
    }
    @Test
    void nullAndNotRequiredBehavior() {
        StringSchema schema = new StringSchema(String.class);
        assertTrue(schema.isValid(null)); // по умолчанию required == false
    }
    @Test
    void testCheckisValidRequiredTrueValueNull() throws Exception {
        String curStringNotEmpty = null;
        var v = new Validator();
        var schema = v.string().required();
        boolean actualResultWorkRequired = schema.isValid(curStringNotEmpty);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testAccumulatePredicatesChecks() throws Exception {
        String curInpSubString = "test";
        final int inpStrLength = 25;
        final int sizeChecksExpected = 3;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        int sizeChecksActual = schema.getChecks().size();
        assertEquals(sizeChecksExpected, sizeChecksActual);
    }
    @Test
    void testNotEmptyTrueMinLengthFalseContains() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = "tess";
        final int inpStrLength = 25;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
    @Test
    void testErrorTypeInput() throws Exception {
        final int curIntValue = 15;
        var v = new Validator();
        var schema = v.string().required();
        boolean actualResultWorkRequired = schema.isValid(curIntValue);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testCheckSetGetValueRequired() throws Exception {
        var v = new Validator();
        var schema = v.string();
        boolean expectedRequired = true;
        schema.setValueRequired(true);
        boolean actualRequired = schema.getValueRequired();
        assertEquals(expectedRequired, actualRequired);
    }
    @Test
    void testAllPredicatesWork() throws Exception {
        String curInpString = "hexlet";
        String curInpSubString = "hex";
        final int inpStrLength = 5;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
    @Test
    void testTwoPredicatesWorkOneNotWork() throws Exception {
        String curInpString = "hello";
        String curInpSubString = "hex";
        final int inpStrLength = 5;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
    @Test
    void testAllPredicatesNotWork() throws Exception {
        String curInpString = "";
        String curInpSubString = "hex";
        final int inpStrLength = 5;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
}
