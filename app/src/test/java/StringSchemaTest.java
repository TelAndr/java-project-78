import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.BaseSchema;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class StringSchemaTest {
    @Test
    void testEmptyInput() throws Exception {
        String curStringEmpty = "";
        var v = new Validator();
        var schema = v.string().required();
        boolean actualResultWorkRequired = schema.isValid(curStringEmpty);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNullInput() throws Exception {
        String curStringNull = null;
        var v = new Validator();
        var schema = v.string().required();
        boolean actualResultWorkRequired = schema.isValid(curStringNull);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNotEmptyInput() throws Exception {
        String curStringNotEmpty = "not empty string";
        var v = new Validator();
        var schema = v.string().required();
        boolean actualResultWorkRequired = schema.isValid(curStringNotEmpty);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testInpStrLowerControlStr() throws Exception {
        String curInpString = "This is test string";
        final int inpStrLength = 15;
        var v = new Validator();
        var schema = v.string().minLength(inpStrLength);
        boolean actualResultWorkMinLength = schema.isValid(curInpString);
        boolean expectedResultWorkMinLength = false;
        assertEquals(expectedResultWorkMinLength, actualResultWorkMinLength);
    }
    @Test
    void testInpStrUpperControlStr() throws Exception {
        String curInpString = "This is test string";
        final int inpStrLength = 25;
        var v = new Validator();
        var schema = v.string().minLength(inpStrLength);
        boolean actualResultWorkMinLength = schema.isValid(curInpString);
        boolean expectedResultWorkMinLength = true;
        assertEquals(expectedResultWorkMinLength, actualResultWorkMinLength);
    }
    @Test
    void testInpStrNullControlStr() throws Exception {
        String curInpString = "This is test string";
        final int inpStrLength = Integer.parseInt(null);
        var v = new Validator();
        var schema = v.string().minLength(inpStrLength);
        boolean actualResultWorkMinLength = schema.isValid(curInpString);
        boolean expectedResultWorkMinLength = true;
        assertEquals(expectedResultWorkMinLength, actualResultWorkMinLength);
    }
    @Test
    void testContainsSubstringInString() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = "test";
        var v = new Validator();
        var schema = v.string().contains(curInpSubString);
        boolean actualResultWorkContains = schema.isValid(curInpString);
        boolean expectedResultWorkContains = true;
        assertEquals(expectedResultWorkContains, actualResultWorkContains);
    }
    @Test
    void testNotContainsSubstringInString() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = "tess";
        var v = new Validator();
        var schema = v.string().contains(curInpSubString);
        boolean actualResultWorkContains = schema.isValid(curInpString);
        boolean expectedResultWorkContains = false;
        assertEquals(expectedResultWorkContains, actualResultWorkContains);
    }
    @Test
    void testContainsNullSubstringInString() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = null;
        var v = new Validator();
        var schema = v.string().contains(curInpSubString);
        boolean actualResultWorkContains = schema.isValid(curInpString);
        boolean expectedResultWorkContains = false;
        assertEquals(expectedResultWorkContains, actualResultWorkContains);
    }
    @Test
    void testTrueMinLengthFalseContains() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = "tess";
        final int inpStrLength = 25;
        var v = new Validator();
        var schema = v.string().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
    @Test
    void testNotEmptyTrueMinLengthTrueContains() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = "test";
        final int inpStrLength = 25;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = true;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
    @Test
    void testNotEmptyFalseMinLengthTrueContains() throws Exception {
        String curInpString = "This is test string";
        String curInpSubString = "test";
        final int inpStrLength = 15;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredMinLengthContains, actualResultWorkRequiredMinLengthContains);
    }
    @Test
    void testEmptyTrueMinLengthTrueContains() throws Exception {
        String curInpString = "";
        String curInpSubString = "tess";
        final int inpStrLength = 25;
        var v = new Validator();
        var schema = v.string().required().minLength(inpStrLength).contains(curInpSubString);
        boolean actualResultWorkRequiredFalseMinLengthContains = schema.isValid(curInpString);
        boolean expectedResultWorkRequiredFalseMinLengthContains = false;
        assertEquals(expectedResultWorkRequiredFalseMinLengthContains, actualResultWorkRequiredFalseMinLengthContains);
    }
    @Test
    void testInpStrEqualControlStr() throws Exception {
        String curInpString = "This is test string";
        final int inpStrLength = 19;
        var v = new Validator();
        var schema = v.string().minLength(inpStrLength);
        boolean actualResultWorkMinLength = schema.isValid(curInpString);
        boolean expectedResultWorkMinLength = true;
        assertEquals(expectedResultWorkMinLength, actualResultWorkMinLength);
    }
    @Test
    void testNullInputNoRequired() throws Exception {
        String curStringNull = null;
        var v = new Validator();
        var schema = v.string();
        boolean actualResultWorkRequired = schema.isValid(curStringNull);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void setValueRequired_setsRequiredTrue() {
        StringSchema sSch = new StringSchema(String.class);
        boolean expectedRequired = true;
        sSch.setValueRequired(true);
        assertEquals(sSch.getValueRequired(), expectedRequired);
    }
    @Test
    void setValueRequired_setsRequiredFalse() {
        StringSchema sSch = new StringSchema(String.class);
        boolean expectedRequired = false;
        sSch.setValueRequired(false);
        assertEquals(sSch.getValueRequired(), expectedRequired);
    }
}
