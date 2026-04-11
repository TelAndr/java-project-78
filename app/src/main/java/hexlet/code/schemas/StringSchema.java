package hexlet.code.schemas;

import java.util.Scanner;
public class StringSchema extends BaseSchema<String> {
    public StringSchema(Class<String> type) {
        super(type);
    }
    /**
     * Преобразует тип выходного значения в строковый тип.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение строкового типа.
     */
    @Override
    protected String cast(Object value) {
        if (value instanceof String) {
            return (String) value;
        }
        throw new ClassCastException("Value is not a String");
    }
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public StringSchema required() {
        isRunRequired = true;
        isRequired = true;
        return this;
    }
    /**
     * добавляет в схему ограничение минимальной длины для строки.
     * Строка должна быть равна или длиннее указанного числа
     * Этот метод может быть пcastереопределен в подклассах для изменения поведения.
     *
     * @param valMinLength минимальное значение длины строки
     * @return значение типа исходного класса.
     */
    public StringSchema minLength(int valMinLength) {
        isRunMinLength = true;
        this.curValMinLength = valMinLength;
        isGreaterMinLength = true;
        return this;
    }
    /**
     * добавляет в схему ограничение для проверки вхождения исходной подстроки в строку.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param curSubstring строковое значение подстроки
     * @return значение типа исходного класса.
     */
    public StringSchema contains(String curSubstring) {
        isRunContains = true;
        this.innerSubstring = curSubstring;
        isCurContains = true;
        return this;
    }
    /**
     * выполняет проверки по сохранённым параметрам и возвращает true или false.
     *
     * @param curString преобразуемое значение
     * @return возвращает результат проверки
     */
    @Override
    protected boolean validate(String curString) {
        boolean result = true;
        if (isRequired && (curString == null || curString.isEmpty())) {
            result = false;
        }
        if (isGreaterMinLength &&  (curString.length() < curValMinLength)) {
            result = false;
        }
        if (isCurContains && (innerSubstring != null && !curString.contains(innerSubstring))) {
            result = false;
        }
        return result;
    }
    //public boolean isValid() {
    //    String input;
    //    while (true) {
    //        System.out.print("Введите данные (обязательно): ");
    //        input = scanner.nextLine().trim(); // Убираем пробелы по краям
    //        curString = input;
    //        if (validate(curString)) {
    //            return true;
    //        } else {
    //            System.out.println("Некорректный ввод. Повторите снова.");
    //        }
    //    }
    //}
    /**
     * принимает значение для проверки или использует уже введённое.
     *
     * @param value входное значение
     * @return возвращает результат проверки валидации
     */
    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired; // например, null допустим, если не required
        }
        if (!(value instanceof String)) {
            return false; // тип не совпадает
        }
        return validate((String) value);
    }
    //private String curString;
    private int curValMinLength;
    private String innerSubstring;
    private boolean isRequired;
    private boolean isGreaterMinLength;
    private boolean isCurContains;
    private boolean isRunRequired;
    private boolean isRunMinLength;
    private boolean isRunContains;
    private static Scanner scanner = new Scanner(System.in);
}
