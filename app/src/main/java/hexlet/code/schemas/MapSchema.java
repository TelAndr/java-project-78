package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

//public class MapSchema<T> {
//public class MapSchema<SELF extends MapSchema<SELF>> {
public class MapSchema<K, SELF extends MapSchema<K, SELF>> {
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    @SuppressWarnings("unchecked")
    protected SELF self() {
        return (SELF) this;
    }
    /**
     * добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @return значение типа исходного класса.
     */
    public MapSchema required() {
        isRunRequired = true;
        isRequired = true;
        return this;
    }
    /**
     * добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param size размер Map
     * @return значение типа исходного класса.
     */
    public MapSchema sizeof(int size) {
        isRunSizeof = true;
        isSizeof = true;
        this.curValSizeMap = size;
        return this;
    }
    /**
     * позволяет описывать валидацию для значений каждого ключа объекта Map
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param mapSchemas входной Map для задания схемы валидации входного значения
     * @return значение типа исходного класса.
     */
    //public MapSchema shape(Map<T, BaseSchema<?>> mapSchemas) {
    //    this.propertySchemas.putAll(mapSchemas);
    //    return this;
    //}
    //public SELF shape(Map<T, BaseSchema<?, ?>> mapSchemas) {
    //    this.propertySchemas.putAll(mapSchemas);
    //    return self();
    //}
    public SELF shape(Map<K, BaseSchema<?>> mapSchemas) {
        this.propertySchemas.putAll(mapSchemas);
        return self();
    }
    /**
     * принимает значение для проверки или использует уже введённое.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param input входной объект для проверки валидности
     * @return значение типа исходного класса.
     */
    public boolean isValid(Object input) {
        if (input == null) {
            // Если верхний Map не обязателен — возвращаем true
            // Если обязательный — false
            return !isRequired;
        }

        if (!(input instanceof Map<?, ?>)) {
            // Если передали не Map — возвращается false
            return false;
        }

        Map<?, ?> mapInput = (Map<?, ?>) input;

        //if (mapInput.isEmpty()) {
        //    return true;
        //}
        // Проверка ограничения "обязательность"
        //if (isRunRequired && isRequired && mapInput.isEmpty()) {
        //    return false;
        //}
        if (isRunSizeof && isSizeof && mapInput.isEmpty()) {
            return false;
        }
        // Проверка размера
        if (isRunSizeof && isSizeof && (mapInput.size() != curValSizeMap)) {
            return false;
        }

        // Проверка схем для каждого свойства
        for (Map.Entry<K, BaseSchema<?>> entry : propertySchemas.entrySet()) {
            K key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();

            Object value = mapInput.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }

        return true; // все проверки прошли
    }
    private boolean isRequired;
    private boolean isSizeof;
    private int curValSizeMap;
    private boolean isRunRequired;
    private boolean isRunSizeof;
    //private Map<T, BaseSchema<?, ?>> propertySchemas = new HashMap();
    private Map<K, BaseSchema<?>> propertySchemas = new HashMap<>();

}

