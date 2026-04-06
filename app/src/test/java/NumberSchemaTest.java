import hexlet.code.Validator;
//import hexlet.code.StringSchema;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NumberSchemaTest {
    @Test
    void testNullInput() throws Exception {
        String curNumberNull = null;
        var v = new Validator();
        var schema = v.number().required();
        boolean actualResultWorkRequired = schema.isValid(curNumberNull);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNotEmptyInput() throws Exception {
        final Integer curNumberNotEmpty = 25;
        var v = new Validator();
        var schema = v.number().required();
        boolean actualResultWorkRequired = schema.isValid(curNumberNotEmpty);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testInpNumLowerMinValue() throws Exception {
        final int curInpNum = 12;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkLowerMinValue = schema.isValid(curInpNum);
        boolean expectedResultWorkLowerMinValue = false;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testInpNumEqualMinValue() throws Exception {
        final int curInpNum = 15;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkEqualMinValue = schema.isValid(curInpNum);
        boolean expectedResultWorkEqualMinValue = true;
        assertEquals(expectedResultWorkEqualMinValue, actualResultWorkEqualMinValue);
    }
    @Test
    void testInpNumUpperMinValue() throws Exception {
        final int curInpNum = 20;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkUpperMinValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMinValue = true;
        assertEquals(expectedResultWorkUpperMinValue, actualResultWorkUpperMinValue);
    }
    @Test
    void testInpNumLowerMaxValue() throws Exception {
        final int curInpNum = 20;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkLowerMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkLowerMaxValue = true;
        assertEquals(expectedResultWorkLowerMaxValue, actualResultWorkLowerMaxValue);
    }
    @Test
    void testInpNumEqualMaxValue() throws Exception {
        final int curInpNum = 25;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkEqualMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkEqualMaxValue = true;
        assertEquals(expectedResultWorkEqualMaxValue, actualResultWorkEqualMaxValue);
    }
    @Test
    void testInpNumUpperMaxValue() throws Exception {
        final int curInpNum = 30;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = false;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpNegativeValue() throws Exception {
        final int curInpNum = -10;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkNegativeValue = schema.isValid(curInpNum);
        boolean expectedResultWorkNegativeValue = false;
        assertEquals(expectedResultWorkNegativeValue, actualResultWorkNegativeValue);
    }
    @Test
    void TestInpZeroValue() throws Exception {
        int curInpNum = 0;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkZeroValue = schema.isValid(curInpNum);
        boolean expectedResultWorkZeroValue = false;
        assertEquals(expectedResultWorkZeroValue, actualResultWorkZeroValue);
    }
    @Test
    void TestInpPositiveValue() throws Exception {
        int curInpNum = 10;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkPositiveValue = schema.isValid(curInpNum);
        boolean expectedResultWorkPositiveValue = false;
        assertEquals(expectedResultWorkPositiveValue, actualResultWorkPositiveValue);
    }
    @Test
    void TestInpCycleValue() throws Exception {
        int curInpNumFirst = Integer.parseInt(null);
        String curInpNumSecond = "ten";
        int curInpNumThird = 15;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkFirstValue = schema.isValid(curInpNumFirst);
        boolean actualResultWorkSecondValue = schema.isValid(curInpNumSecond);
        boolean actualResultWorkThirdValue = schema.isValid(curInpNumThird);
        boolean expectedResultWorkThirdValue = true;
        assertEquals(expectedResultWorkThirdValue, actualResultWorkThirdValue);
    }
    @Test
    void TestNotNumberValue() throws Exception {
        String curInpValue = "Not number";
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkNotNumberValue = schema.isValid(curInpValue);
        boolean expectedResultWorkNotNumberValue = false;
        assertEquals(expectedResultWorkNotNumberValue, actualResultWorkNotNumberValue);
    }
}
