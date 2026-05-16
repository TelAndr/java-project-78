package hexlet.code.schemas;

//import java.util.Scanner;
//public class NumberSchema extends BaseSchema<Integer> {
public class NumberSchema extends BaseSchema<Integer> {
    //public NumberSchema(Class<Integer> type) {
    //    super(type);
    //}
    public NumberSchema() {
        super(Integer.class);
    }
    /**
     * преобразует переданный объект value в тип Integer.
     *
     * @param value преобразуемое значение
     * @return возвращает результат преобразования в тип Integer
     */
    @Override
    protected Integer cast(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        throw new ClassCastException("Value is not a Number");
    }
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public NumberSchema required() {
        setValueRequired(true);
        addCheck("required", n -> n != null);
        return this;
    }
    /**
     * добавляет в схему ограничение, которое позволяет использовать только положительные числа в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public NumberSchema positive() {
        addCheck("positive", n -> n > 0);
        return this;
    }
    /**
     * добавляет в схему ограничение, которое позволяет использовать только в диапазоне от lowerRange до upperRange
     * числа в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param lowerRange минимальное значение для прохождения валидации
     * @param upperRange максимальное значение для прохождения валидации
     * @return значение типа исходного класса.
     */
    public  NumberSchema range(int lowerRange, int upperRange) {
        addCheck("range", n -> n >= lowerRange && n <= upperRange);
        return this;
    }
}
