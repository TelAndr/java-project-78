package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
//public abstract class BaseSchema<T, S extends BaseSchema<T, S>> {
    private Map<String, Predicate<T>> checks = new HashMap<>();
    /**
     * делает возможным использование Map<String, Predicate<T>> checks извне класса BaseSchema<T>.
     *
     * @return возвращает значение Map<String, Predicate<T>> checks
     */
    Map<String, Predicate<T>> getChecks() {
        return checks;
    }
    private boolean required = false;
    /**
     * делает возможным использование required извне класса BaseSchema<T>.
     *
     * @return возвращает значение required
     */
    boolean getValueRequired() {
        return required;
    }
    /**
     * добавляет проверку с конкретным названием и конкретный предикат.
     *
     * @param name название проверки
     * @param validate конкретный предикат
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }
    //protected  abstract T castValue(Object value);
    /**
     * выполняет проверки по сохранённым параметрам и возвращает true или false.
     *
     * @param value преобразуемое значение
     * @return возвращает результат проверки
     */
    protected abstract boolean validate(T value);

    public BaseSchema(Class<T> type) {
        this.type = type;
    }
    /**
     * читает значение из консольного ввода и определяет его тип.
     *
     * @return возвращает тип значения из консольного ввода
     */
    public T readInput() {
        if (type == String.class) {
            return type.cast(scanner.nextLine());
        } else if (type == Integer.class) {
            return type.cast(scanner.nextInt());
        } else if (type == Double.class) {
            return type.cast(scanner.nextDouble());
        } else if (type == Boolean.class) {
            return type.cast(scanner.nextBoolean());
        } else {
            throw new IllegalArgumentException("Тип не поддерживается: " + type);
        }
    }
    /**
     * принимает значение для проверки или использует уже введённое.
     *
     * @param value входное значение
     * @return возвращает результат проверки валидации
     */
    public boolean isValid(Object value) {
        //if (value == null) {
        //    return !isRequired; // nullable, если не обязательно
        //}
        if (value == null) {
            return !required; // или вернем false, если обязательно
        }
        for (Predicate<T> check : checks.values()) {
            if (!check.test((T) value)) {
                return false;
            }
        }
        try {
            T castedValue = cast(value);
            return validate(castedValue);
        } catch (ClassCastException e) {
            return false; // неправильный тип
        }
        //while (true) {
        //    System.out.print("Введите данные (обязательно): ");
        //    T input = readInput();
        //    curValue = input;
        //    if (validate(curValue)) {
        //        return true;
        //    } else {
        //        System.out.println("Некорректный ввод. Повторите снова.");
        //    }
        //}
    }
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public BaseSchema<T> required() { // S
        this.required = true;
        //return self();
        return this;
        //if (type == String.class) {
        //    return new StringSchema(String.class);
        //} else if (type == Integer.class) {
        //    return new NumberSchema(Integer.class);
        //}
    }
    //protected abstract BaseSchema<T> self();
    //protected abstract S self();
    protected abstract T cast(Object value);
    private static Scanner scanner = new Scanner(System.in);
    private T curValue;
    private final Class<T> type;
}
