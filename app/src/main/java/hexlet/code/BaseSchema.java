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
    Map<String, Predicate<T>> getChecks() {
        return checks;
    }
    private boolean required = false;
    boolean getValueRequired() {
        return required;
    }

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
