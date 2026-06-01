//import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
