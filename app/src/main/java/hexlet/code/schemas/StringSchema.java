package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
//public class StringSchema extends BaseSchema<String, StringSchema> {
    //public StringSchema(Class<String> type) {
    //    super(type);
    public StringSchema() {
        super(String.class);
    }
    /**
     * Преобразует тип выходного значения в строковый тип.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param value искомое значение для преобразования в String
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
    public StringSchema required() { // S
        setValueRequired(true);
        addCheck("required", s -> s != null && !s.isEmpty());
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
}
