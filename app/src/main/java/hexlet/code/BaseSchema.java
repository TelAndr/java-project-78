package hexlet.code;

import java.util.Scanner;
public abstract class BaseSchema<T> {
    protected T castValue(Object value) {
        return null;
    }

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