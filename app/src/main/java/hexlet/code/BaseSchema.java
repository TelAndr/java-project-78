package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    /**
     * определяет является ли строка непустой или ненулевой.
     *
     * @return определяет значение предиката
     */
    public Predicate<String> detectNotEmpty() {
        return s -> s != null && !s.isEmpty();
    }
    /**
     *
     * проверяет на нулевое значение и на пустоту.
     *
     * @return определяет значение предиката
     */
    public Predicate<String> detectRequired() {
        return s -> (s == null || s.isEmpty());
    }
    /**
     * проверяет на соответствие минимальной длины строки.
     *
     * @param minLen значение минимальной длины
     * @return определяет значение предиката
     */
    public Predicate<String> detectMinLength(int minLen) {
        return s -> s.length() > minLen;
    }
    /**
     * проверяет на соответствие положительному значению числа.
     *
     * @return определяет значение предиката
     */
    public Predicate<Integer> detectPositive() {
        return x -> x > 0;
    }
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
     * @return создает Map<String, Predicate<T>> checks c ключём name и значением validate
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }
    /**
     * преобразует переданный объект value в тип T.
     *
     * @param value преобразуемое значение
     * @return возвращает результат преобразования в конкретный тип
     */
    protected T castValue(Object value) {
        return null;
    }
    /**
     * выполняет проверки по сохранённым параметрам и возвращает true или false.
     *
     * @param value преобразуемое значение
     * @return возвращает результат проверки
     */
    protected boolean validate(T value) {
        return false;
    }

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
        try {
            T castedValue = castValue(curValue);
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
    protected abstract T cast(Object value);
    private static Scanner scanner = new Scanner(System.in);
    private T curValue;
    private final Class<T> type;
}
