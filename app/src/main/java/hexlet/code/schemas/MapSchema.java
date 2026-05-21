package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

//public class MapSchema<T> {
//public class MapSchema<SELF extends MapSchema<SELF>> {
public class MapSchema<K, SELF extends MapSchema<K, SELF>> extends BaseSchema<Map<?, ?>> {
    public MapSchema(Class<Map<?, ?>> typeClass) {
        super(typeClass);
    }

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
        setValueRequired(true);
        addCheck("required", m -> m != null);
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
        addCheck("sizeof", m -> m.size() == size);
        return this;
    }
    /**
     * позволяет описывать валидацию для значений каждого ключа объекта Map
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param mapSchemas входной Map для задания схемы валидации входного значения
     * @return значение типа исходного класса.
     */
    public SELF shape(Map<K, BaseSchema<?>> mapSchemas) {
        if (mapSchemas != null) {
            this.propertySchemas.putAll(mapSchemas);
        }
        // добавим предикат, который проверяет каждое свойство по соответствующей схеме
        addCheck("shape", m -> {
            if (m == null) {
                return true; // если null — оставляем на required
            }
            for (Map.Entry<K, BaseSchema<?>> entry : propertySchemas.entrySet()) {
                Object key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object value = m.get(key);
                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
        return self();
    }
    private Map<K, BaseSchema<?>> propertySchemas = new HashMap<>();

}

