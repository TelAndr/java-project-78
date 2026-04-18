import hexlet.code.schemas.BaseSchema;
import hexlet.code.Validator;
//import hexlet.code.schemas.MapSchema;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {
    @Test
    void testNullInputFalseRequired() throws Exception {
        Map mapData = null;
        var v = new Validator();
        var schema = v.map().required();
        boolean actualResultWorkRequired = schema.isValid(mapData);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNullInputTrueRequired() throws Exception {
        Map mapData = null;
        var v = new Validator();
        var schema = v.map().required();
        boolean actualResultWorkRequired = !schema.isValid(mapData);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNullInputNoRequired() throws Exception {
        Map mapData = null;
        var v = new Validator();
        var schema = v.map();
        boolean actualResultWorkRequired = schema.isValid(mapData);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testEmptyInputFalseRequired() throws Exception {
        Map mapData = new HashMap<String, String>();
        var v = new Validator();
        var schema = v.map().required();
        boolean actualResultWorkRequired = schema.isValid(mapData);
        boolean expectedResultWorkRequired = false;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testEmptyInputTrueRequired() throws Exception {
        Map mapData = new HashMap<String, String>();
        var v = new Validator();
        var schema = v.map().required();
        boolean actualResultWorkRequired = !schema.isValid(mapData);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testNotEmptyInputTrueRequired() throws Exception {
        Map mapData = new HashMap<String, String>();
        mapData.put("testKeyOne", "testValueOne");
        var v = new Validator();
        var schema = v.map().required();
        boolean actualResultWorkRequired = schema.isValid(mapData);
        boolean expectedResultWorkRequired = true;
        assertEquals(expectedResultWorkRequired, actualResultWorkRequired);
    }
    @Test
    void testInpMapLowerMinValue() throws Exception {
        int lengthLowerMap = 2;
        Map mapData = new HashMap<String, String>();
        mapData.put("testKeyOne", "testValueOne");
        mapData.put("testKeyTwo", "testValueTwo");
        mapData.put("testKeyThree", "testValueThree");
        var v = new Validator();
        var schema = v.map().sizeof(lengthLowerMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = false;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testInpMapEqualMinValue() throws Exception {
        final int lengthEqualMap = 3;
        Map mapData = new HashMap<String, String>();
        mapData.put("testKeyOne", "testValueOne");
        mapData.put("testKeyTwo", "testValueTwo");
        mapData.put("testKeyThree", "testValueThree");
        var v = new Validator();
        var schema = v.map().sizeof(lengthEqualMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = true;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testInpMapUpperMinValue() throws Exception {
        final int lengthUpperMap = 4;
        Map mapData = new HashMap<String, String>();
        mapData.put("testKeyOne", "testValueOne");
        mapData.put("testKeyTwo", "testValueTwo");
        mapData.put("testKeyThree", "testValueThree");
        var v = new Validator();
        var schema = v.map().sizeof(lengthUpperMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = false;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testNotInputMapLowerMinValue() throws Exception {
        int lengthLowerMap = 2;
        Map mapData = new HashMap<String, String>();
        mapData.put("testKeyOne", "testValueOne");
        mapData.put("testKeyTwo", "testValueTwo");
        mapData.put("testKeyThree", "testValueThree");
        var v = new Validator();
        var schema = v.map().required().sizeof(lengthLowerMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = false;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testNotInputMapEqualMinValue() throws Exception {
        final int lengthEqualMap = 3;
        Map mapData = new HashMap<String, String>();
        mapData.put("testKeyOne", "testValueOne");
        mapData.put("testKeyTwo", "testValueTwo");
        mapData.put("testKeyThree", "testValueThree");
        var v = new Validator();
        var schema = v.map().required().sizeof(lengthEqualMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = true;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testDifferentsTypesKeysValuesMap() throws Exception {
        final int lengthEqualMap = 3;
        Map mapData = new HashMap<String, Integer>();
        final int mapDataVal1 = 1;
        final int mapDataVal2 = 2;
        final int mapDataVal3 = 3;
        mapData.put("testKeyOne", mapDataVal1);
        mapData.put("testKeyTwo", mapDataVal2);
        mapData.put("testKeyThree", mapDataVal3);
        var v = new Validator();
        var schema = v.map().required().sizeof(lengthEqualMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = true;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testCorrectWorkWithoutRestrictions() throws Exception {
        int lengthEqualMap = 0;
        Map mapData = new HashMap<String, Integer>();
        var v = new Validator();
        var schema = v.map().required().sizeof(lengthEqualMap);
        boolean actualResultWorkLowerMinValue = schema.isValid(mapData);
        boolean expectedResultWorkLowerMinValue = true;
        assertEquals(expectedResultWorkLowerMinValue, actualResultWorkLowerMinValue);
    }
    @Test
    void testCorrectSaveSchem() throws Exception {
        //Map mapData = new HashMap<String, Integer>();
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("typeTransport", v.string().required());
        schemas.put("modelTransport", v.string().required().minLength(2));
        schema.shape(schemas);
        Map<String, String> transp1 = new HashMap<>();
        transp1.put("typeTransport", "Car");
        transp1.put("modelTransport", "Audi");
        assertTrue(schema.isValid(transp1)); // true
    }
    @Test
    void testCorrectValidData() throws Exception {
        var v = new Validator();
        var schema = v.map();
        int minIntValue = 2;
        final int maxIntValue = 5;
        final int minVelocity = 10;
        final int maxVelocity = 60;
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put("typeTransport", v.number().required());
        schemas.put("modelTransport", v.number().required().range(minIntValue, maxIntValue));
        schema.shape(schemas);
        Map<String, Integer> velocityTransp = new HashMap<>();
        velocityTransp.put("bycicle", minVelocity);
        velocityTransp.put("car", maxVelocity);
        assertTrue(schema.isValid(velocityTransp)); // true
    }
    @Test
    void testMissingKeysData() throws Exception {
        var v = new Validator();
        var schema = v.map();
        int minIntValue = 2;
        final int maxIntValue = 5;
        final int minVelocity = 10;
        final int maxVelocity = 60;
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put(null, v.number().required());
        schemas.put("modelTransport", v.number().required().range(minIntValue, maxIntValue));
        schema.shape(schemas);
        Map<String, Integer> velocityTransp = new HashMap<>();
        velocityTransp.put("bycicle", minVelocity);
        velocityTransp.put("car", maxVelocity);
        assertTrue(schema.isValid(velocityTransp)); // true
    }
    @Test
    void testUnnecessaryKeysData() throws Exception {
        var v = new Validator();
        var schema = v.map();
        int minIntValue = 2;
        final int maxIntValue = 5;
        final int minVelocity = 10;
        final int maxVelocity = 60;
        final int maxVelocityPlain = 1000;
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put("typeTransport", v.number().required());
        schemas.put("modelTransport", v.number().required().range(minIntValue, maxIntValue));
        schema.shape(schemas);
        Map<String, Integer> velocityTransp = new HashMap<>();
        velocityTransp.put("bycicle", minVelocity);
        velocityTransp.put("car", maxVelocity);
        velocityTransp.put("Plain", maxVelocityPlain);
        assertTrue(schema.isValid(velocityTransp)); // true
    }
    @Test
    void testEmptyMapShape() throws Exception {
        var v = new Validator();
        var schema = v.map();
        int minIntValue = 2;
        final int maxIntValue = 5;
        final int minVelocity = 10;
        final int maxVelocity = 60;
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schema.shape(schemas);
        Map<String, Integer> velocityTransp = new HashMap<>();
        velocityTransp.put("bycicle", minVelocity);
        velocityTransp.put("car", maxVelocity);
        assertTrue(schema.isValid(velocityTransp)); // true
    }
    @Test
    void testCorrValidData() throws Exception {
        var v = new Validator();
        var schema = v.map();
        final int valMinLength = 3;
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("typeTransport", v.string().required());
        schemas.put("modelTransport", v.string().required().minLength(valMinLength).contains("Car"));
        schema.shape(schemas);
        Map<String, String> transp1 = new HashMap<>();
        transp1.put("typeTransport", "Car");
        transp1.put("modelTransport", "CarBMW");
        assertTrue(schema.isValid(transp1)); // true
    }
    @Test
    void testDopEmptyInputMap() throws Exception {
        var v = new Validator();
        var schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(new HashMap<>())).isFalse();
    }
}
