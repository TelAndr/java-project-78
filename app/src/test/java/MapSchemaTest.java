import hexlet.code.BaseSchema;
import hexlet.code.Validator;
import hexlet.code.MapSchema;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        int lengthEqualMap = 3;
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
        int lengthUpperMap = 4;
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
        int lengthEqualMap = 3;
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
        int lengthEqualMap = 3;
        Map mapData = new HashMap<String, Integer>();
        mapData.put("testKeyOne", 1);
        mapData.put("testKeyTwo", 2);
        mapData.put("testKeyThree", 3);
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
        Map<String, hexlet.code.BaseSchema<String>> schemas = new HashMap<>();
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
        int maxIntValue = 5;
        int minVelocity = 10;
        int maxVelocity = 60;
        Map<String, hexlet.code.BaseSchema<Integer>> schemas = new HashMap<>();
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
        int maxIntValue = 5;
        int minVelocity = 10;
        int maxVelocity = 60;
        Map<String, hexlet.code.BaseSchema<Integer>> schemas = new HashMap<>();
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
        int maxIntValue = 5;
        int minVelocity = 10;
        int maxVelocity = 60;
        int maxVelocityPlain = 1000;
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
        int maxIntValue = 5;
        int minVelocity = 10;
        int maxVelocity = 60;
        Map<String, hexlet.code.BaseSchema<Integer>> schemas = new HashMap<>();
        schema.shape(schemas);
        Map<String, Integer> velocityTransp = new HashMap<>();
        velocityTransp.put("bycicle", minVelocity);
        velocityTransp.put("car", maxVelocity);
        assertTrue(schema.isValid(velocityTransp)); // true
    }
    @Test
    void testCorrectValidDataCombiСonditionsString() throws Exception {
        var v = new Validator();
        var schema = v.map();
        Map<String, hexlet.code.BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("typeTransport", v.string().required());
        schemas.put("modelTransport", v.string().required().minLength(2).contains("Car"));
        schema.shape(schemas);
        Map<String, String> transp1 = new HashMap<>();
        transp1.put("typeTransport", "Car");
        transp1.put("modelTransport", "CarBMW");
        assertTrue(schema.isValid(transp1)); // true
    }

}