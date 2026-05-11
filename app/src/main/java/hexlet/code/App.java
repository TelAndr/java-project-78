package hexlet.code;

import hexlet.code.schemas.BaseSchema;
//import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        final int valMinLength = 5;
        final int ageValuePerson = 20;
        final int ageValueBadPerson = 17;
        final int minAge = 18;
        final int maxAge = 100;
        System.out.printf("Hello git and welcome!");
        // Создаем валидатор
        Validator validator = new Validator();
        //////////////////////////////////////////////////////
        var strSchema = validator.string();
        boolean trueEmptyString = strSchema.isValid("");

        strSchema.required();
        boolean trueStringContFewWords = strSchema.isValid("what does the fox say");
        boolean trueStringContOneWord = strSchema.isValid("hexlet");
        boolean falseEmptyString = strSchema.isValid("");
        boolean falseNullString = strSchema.isValid(null);
        //////////////////////////////////////////////////////
        final int numValLoverMin = 4;
        final int numValContr = 5;
        final int numValNeg = -10;
        final int numValPozUpper = 10;
        var numSchema = validator.number();
        boolean trueNumSimpleDigit = numSchema.isValid(numValContr);
        boolean trueNullNum = numSchema.isValid(null);

        numSchema.positive();
        boolean truPozNullNum = numSchema.isValid(null);

        numSchema.required();
        boolean falsePozNullNum = numSchema.isValid(null);
        boolean falseValNegNum = numSchema.isValid(numValNeg);

        numSchema.range(numValContr, numValPozUpper);
        boolean trueRangeLowerVal = numSchema.isValid(numValContr);
        boolean trueRangeUpperVal = numSchema.isValid(numValPozUpper);
        boolean falseRangeLowerMinVal = numSchema.isValid(numValLoverMin);
        //////////////////////////////////////////////////////
        var schema = validator.map();

        boolean truValidNull = schema.isValid(null);
        boolean truValidEmptyMapInp = schema.isValid(new HashMap<>());

        schema.required();
        boolean falseValidNull = schema.isValid(null);
        boolean trueValidEmptyMapInp = schema.isValid(new HashMap<>());

        schema.sizeof(2);
        boolean falseValidEmptyMapInp = schema.isValid(new HashMap<>());
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        boolean falseValidKey1 = schema.isValid(actual1);
        actual1.put("key2", "value2");
        boolean trueValidKey2 = schema.isValid(actual1);
        //////////////////////////////////////////////////////
        // Создаем схему для строки с минимальной длиной 5 и обязательным полем
        StringSchema stringSchema = validator.string().required().minLength(valMinLength);

        // Проверка данных: правильная
        System.out.println(stringSchema.isValid("hello")); // true

        // Проверка данных: неправильная (короткая)
        System.out.println(stringSchema.isValid("hi")); // false

        // Демонстрация с null
        System.out.println(stringSchema.isValid(null)); // false

        // Создаем схему для Map
        var mapSchema = validator.map();

        // Настраиваем схему Map с shape()
        Map<String, BaseSchema<String>> schemaString = new HashMap<>();
        Map<String, BaseSchema<Integer>> schemaInteger = new HashMap<>();
        schemaString.put("name", validator.string().required());
        schemaInteger.put("age", validator.number().range(minAge, maxAge));

        mapSchema.shape(schemaString);
        mapSchema.shape(schemaInteger);
        // Тестовые объекты
        Map<String, Object> person = new HashMap<>();
        person.put("name", "Alex");
        person.put("age", ageValuePerson);

        System.out.println(mapSchema.isValid(person)); // true

        Map<String, Object> badPerson = new HashMap<>();
        badPerson.put("name", "Al");
        badPerson.put("age", ageValueBadPerson);

        System.out.println(mapSchema.isValid(badPerson)); // false
    }
}
