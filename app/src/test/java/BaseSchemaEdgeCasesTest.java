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
        String curInpString = "This is test string";
        String curInpSubString = "test";
        final int inpStrLength = 25;
        final int sizeChecksExpected = 3;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        int sizeChecksActual = schema.getChecks().size();
        assertEquals(sizeChecksExpected, sizeChecksActual);
    }
}
