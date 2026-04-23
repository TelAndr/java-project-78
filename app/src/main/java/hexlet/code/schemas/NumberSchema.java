package hexlet.code.schemas;

import java.util.Scanner;
//public class NumberSchema extends BaseSchema<Integer> {
public class NumberSchema extends BaseSchema<Integer, NumberSchema> {
    public NumberSchema(Class<Integer> type) {
        super(type);
    }
    @Override
    protected NumberSchema self() {
        return this;
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

    //public NumberSchema required() {
    //    isRunRequired = true;
    //    isRequired = true;
    //    return this;
    //}
    /**
     * добавляет в схему ограничение, которое позволяет использовать только положительные числа в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public NumberSchema positive() {
        isRunPositive = true;
        isPositive = true;
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
        isRunRange = true;
        this.curLowerRange = lowerRange;
        this.curUpperRange = upperRange;
        isFallsWithinRange = true;
        return this;
    }
    /**
     * выполняет проверки по сохранённым параметрам и возвращает true или false.
     *
     * @param curNumber преобразуемое значение
     * @return возвращает результат проверки
     */
    @Override
    protected boolean validate(Integer curNumber) {
        boolean result = true;
        Integer val = curNumber.intValue();
        if (isRequired && ((curNumber == null))) {
            result = false;
        }
        if (isPositive &&  (val <= 0)) {
            result = false;
        }
        if (isFallsWithinRange && (val < curLowerRange || val > curUpperRange)) {
            result = false;
        }
        return result;
    }
    //public boolean isValid() {
    //    int input;
    //    while (true) {
    //        System.out.print("Введите данные (обязательно): ");
    //        input = scanner.nextInt(); // Убираем пробелы по краям
    //        curNumber = input;
    //        if (validate(curNumber)) {
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
        if (!(value instanceof Integer)) {
            return false; // тип не совпадает
        }
        return validate((Integer) value);
    }
    //private Integer curNumber;
    private boolean isRequired;
    private boolean isPositive;
    private Integer curLowerRange;
    private Integer curUpperRange;
    private boolean isFallsWithinRange;
    private boolean isRunRequired;
    private boolean isRunPositive;
    private boolean isRunRange;
    private static Scanner scanner = new Scanner(System.in);
}
