package hexlet.code;

import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        int valMinLength = 5;
        int ageValuePerson = 20;
        int ageValueBadPerson = 17;
        System.out.printf("Hello git and welcome!");
        // Создаем валидатор
        hexlet.code.Validator validator = new hexlet.code.Validator();

        // Создаем схему для строки с минимальной длиной 5 и обязательным полем
        var stringSchema = validator.string().required().minLength(valMinLength);

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
        int minAge = 18;
        int maxAge = 100;
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
