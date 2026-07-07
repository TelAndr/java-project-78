import hexlet.code.Validator;
//import hexlet.code.schemas.StringSchema;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NumberSchemaTest {
    @Test
    void testNullInput() throws Exception {
        String curNumberNull = null;
        var v = new Validator();
        var schema = v.number().required();
        boolean actualResultWorkRequired = schema.isValid(Integer.valueOf(curNumberNull));
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNullObjectInput() throws Exception {
        Object curNumberNull = null;
        var v = new Validator();
        var schema = v.number().required();
        boolean actualResultWorkRequired = schema.isValid((Integer) curNumberNull);
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
    void testInpNumNullValue() throws Exception {
        final int curInpNum = Integer.parseInt(null);
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().range(valLowerRange, valUpperRange);
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = true;
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
    void testInpZeroValue() throws Exception {
        int curInpNum = 0;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkZeroValue = schema.isValid(curInpNum);
        boolean expectedResultWorkZeroValue = false;
        assertEquals(expectedResultWorkZeroValue, actualResultWorkZeroValue);
    }
    @Test
    void testInpMinPozValue() throws Exception {
        int curInpNum = 1;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkZeroValue = schema.isValid(curInpNum);
        boolean expectedResultWorkZeroValue = true;
        assertEquals(expectedResultWorkZeroValue, actualResultWorkZeroValue);
    }
    @Test
    void testInpPositiveValue() throws Exception {
        final int curInpNum = 10;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkPositiveValue = schema.isValid(curInpNum);
        boolean expectedResultWorkPositiveValue = true;
        assertEquals(expectedResultWorkPositiveValue, actualResultWorkPositiveValue);
    }
    @Test
    void testInpCycleValue() throws Exception {
        int curInpNumFirst;
        try {
            curInpNumFirst = Integer.parseInt(null);
        } catch (NumberFormatException | NullPointerException e) {
            curInpNumFirst = 0;
        }
        String curInpNumSecond = "ten";
        final int curInpNumThird = 15;
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkFirstValue = schema.isValid(curInpNumFirst);
        boolean actualResultWorkSecondValue = schema.isValid(Integer.valueOf(curInpNumSecond));
        boolean actualResultWorkThirdValue = schema.isValid(curInpNumThird);
        boolean expectedResultWorkThirdValue = true;
        assertEquals(expectedResultWorkThirdValue, actualResultWorkThirdValue);
    }
    @Test
    void testNotNumberValue() throws Exception {
        String curInpValue = "Not number";
        var v = new Validator();
        var schema = v.number().positive();
        boolean actualResultWorkNotNumberValue = schema.isValid(Integer.valueOf(curInpValue));
        boolean expectedResultWorkNotNumberValue = false;
        assertEquals(expectedResultWorkNotNumberValue, actualResultWorkNotNumberValue);
    }
    @Test
    void testNullInputNoRequired() throws Exception {
        int curNumberNull = Integer.parseInt(null);
        var v = new Validator();
        var schema = v.number();
        boolean actualResultWorkRequired = schema.isValid(curNumberNull);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testInpRequiredPositiveValue() throws Exception {
        final int curInpNum = 10;
        var v = new Validator();
        var schema = v.number().required().positive();
        boolean actualResultWorkPositiveValue = schema.isValid(curInpNum);
        boolean expectedResultWorkPositiveValue = true;
        assertEquals(expectedResultWorkPositiveValue, actualResultWorkPositiveValue);
    }
    @Test
    void testInpRequiredRangeValue() throws Exception {
        final int curInpNum = 20;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().required().range(valLowerRange, valUpperRange);
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = true;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpPositiveRangeValue() throws Exception {
        final int curInpNum = 20;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().positive().range(valLowerRange, valUpperRange);
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = true;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpRequiredPositiveRangeValue() throws Exception {
        final int curInpNum = 20;
        final int valLowerRange = 15;
        final int valUpperRange = 25;
        var v = new Validator();
        var schema = v.number().required().positive().range(valLowerRange, valUpperRange);
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = true;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpRepeatRangeValue() throws Exception {
        final int curInpNum = 15;
        final int valLowerRangeOne = 10;
        final int valUpperRangeOne = 20;
        final int valLowerRangeTwo = 30;
        final int valUpperRangeTwo = 40;
        var v = new Validator();
        var schema = v.number().range(valLowerRangeOne, valUpperRangeOne).range(valLowerRangeTwo, valUpperRangeTwo);
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = false;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpRepeatPositiveValue() throws Exception {
        final int curInpNum = 15;
        var v = new Validator();
        var schema = v.number().positive().positive();
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = true;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpRequiredNotNullValue() throws Exception {
        final int curInpNum = 15;
        var v = new Validator();
        var schema = v.number().required();
        boolean actualResultWorkUpperMaxValue = schema.isValid(curInpNum);
        boolean expectedResultWorkUpperMaxValue = true;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpNotCorrectTypeStringNotRequiredValue() throws Exception {
        final String curInpValue = "10";
        var v = new Validator();
        var schema = v.number();
        boolean actualResultWorkUpperMaxValue = schema.isValid(Integer.valueOf(curInpValue));
        boolean expectedResultWorkUpperMaxValue = false;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
    @Test
    void testInpNotCorrectTypeObjectNotRequiredValue() throws Exception {
        final double curInpValue = 10.5;
        var v = new Validator();
        var schema = v.number();
        boolean actualResultWorkUpperMaxValue = schema.isValid((int) curInpValue);
        boolean expectedResultWorkUpperMaxValue = false;
        assertEquals(expectedResultWorkUpperMaxValue, actualResultWorkUpperMaxValue);
    }
}
