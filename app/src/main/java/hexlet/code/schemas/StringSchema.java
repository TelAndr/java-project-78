package hexlet.code.schemas;

import java.util.Scanner;
public class StringSchema extends BaseSchema<String> {
//public class StringSchema extends BaseSchema<String, StringSchema> {
    //public StringSchema(Class<String> type) {
    //    super(type);
public StringSchema() {
    super(String.class);
    }
    /**
     * Возвращает текущий экземпляр объекта, приведённый к типу StringSchema.
     * Этот метод используется для правильного возвращения типа при цепочечных вызовах методов.
     *
     * @return текущий объект в точном типе StringSchema
     */
    //@Override
    //protected StringSchema self() {
    //    return this;
    //}
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

    //public StringSchema required() {
    //    this.isRunRequired = true;
    //    this.isRequired = true;
    //    return this;
    //}
    //public StringSchema minLength(int valMinLength) {
    //    isRunMinLength = true;
    //    this.curValMinLength = valMinLength;
    //    isGreaterMinLength = true;
    //    return this;
    //}
    //public StringSchema contains(String curSubstring) {
    //    isRunContains = true;
    //    this.innerSubstring = curSubstring;
    //    isCurContains = true;
    //    return this;
    //}
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public StringSchema notEmpty() {
        addCheck("notEmpty", s -> s != null && !s.isEmpty());
        return this;
    }
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public StringSchema required() { // S
        setValueRequired(true);
        //this.required = true;
        //return self();
        return this;
    }
    /**
     * добавляет в схему ограничение минимальной длины для строки.
     * Строка должна быть равна или длиннее указанного числа
     * Этот метод может быть пcastереопределен в подклассах для изменения поведения.
     *
     * @param length минимальное значение длины строки
     * @return значение типа исходного класса.
     */
    public StringSchema minLength(int length) {
        addCheck("minLength", s -> s != null && s.length() >= length);
        return this;
    }
    /**
     * добавляет в схему ограничение для проверки вхождения исходной подстроки в строку.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param substring строковое значение подстроки
     * @return значение типа исходного класса.
     */
    public StringSchema contains(String substring) {
        addCheck("contains", s -> s != null && s.contains(substring));
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
        boolean isRequired = getValueRequired();
        if (isRequired && (curString == null || curString.isEmpty())) {
            result = false;
        }
        if (isGreaterMinLength &&  (curString.length() < curValMinLength)) {
            result = false;
        }
        if (isCurContains && (innerSubstring != null && !curString.contains(innerSubstring))) {
            result = false;
        }
        ///////////////////////////////////////////////////////////
        //String checkRequired = "isRequired";
        //if (isRequired) {
        //    addCheck(checkRequired, detectRequiredString());
        //    result = false;
        //}
        //if (isGreaterMinLength &&  (curString.length() < curValMinLength)) {
        //    result = false;
        //}
        //if (isCurContains && (innerSubstring != null && !curString.contains(innerSubstring))) {
        //    result = false;
        //}
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

    //@Override
    //public boolean isValid(Object value) {
    //    if (value == null) {
    //        return !isRequired; // например, null допустим, если не required
    //    }
    //    if (!(value instanceof String)) {
    //        return false; // тип не совпадает
    //    }
    //    return validate((String) value);
    //}
    //private String curString;
    private int curValMinLength;
    private String innerSubstring;
    //private boolean isRequired = false;
    private boolean isGreaterMinLength = false;
    private boolean isCurContains;
    private boolean isRunRequired;
    private boolean isRunMinLength;
    private boolean isRunContains;
    private static Scanner scanner = new Scanner(System.in);
}
