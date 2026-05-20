package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
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
    private final Class<T> type;
    /**
     * делает возможным использование required извне класса BaseSchema<T>.
     *
     * @return возвращает значение required
     */
    public boolean getValueRequired() {
        return required;
    }
    /**
     * устанавливает required в конкретное извне класса BaseSchema<T>.
     *
     * @param valRequired входное значеие для установки required
     */
    public void setValueRequired(boolean valRequired) {
        this.required = valRequired;
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

    public BaseSchema(Class<T> typeClass) {
        this.type = typeClass;
    }
    /**
     * принимает значение для проверки или использует уже введённое.
     *
     * @param value входное значение
     * @return возвращает результат проверки валидации
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !required; // или вернем false, если обязательно
        }
        // Если ожидаемый тип задан (type != null), проверяем соответствие типа
        if (type != null && !type.isInstance(value)) {
            // Особый случай: для MapSchema мы храним тип как Map<?, ?>,
            // но проверка type.isInstance обрабатывает это корректно
            // Если тип не совпадает — это невалидно
            return false;
        }
        for (Predicate<T> check : checks.values()) {
            try {
                if (!check.test((T) value)) {
                    return false;
                }
            } catch (ClassCastException e) {
                return false;
            }
        }
        return true;
    }
}
