package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public class MapSchema<T> {
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
    public MapSchema shape(Map<T, BaseSchema<T>> mapSchemas) {
        this.propertySchemas.putAll(mapSchemas);
        return this;
    }
    /**
     * выполняет проверки по сохранённым параметрам и возвращал true или false.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param curMap входной Map для проверки валидности ключей и значений
     * @return значение типа исходного класса.
     */
    public boolean validate(Map curMap) {
        boolean result = true;
        if (isRequired && (curMap == null || curMap.isEmpty())) {
            result = false;
        }
        if (isSizeof &&  (curMap.size() != curValSizeMap)) {
            result = false;
        }
        return result;
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
        if (isRunRequired && isRequired && !(mapInput.isEmpty())) {
            return false;
        }
        if (isRunSizeof && isSizeof && mapInput.isEmpty()) {
            return false;
        }
        // Проверка размера
        if (isRunSizeof && isSizeof && (mapInput.size() != curValSizeMap)) {
            return false;
        }

        // Проверка схем для каждого свойства
        for (Map.Entry<T, BaseSchema<?>> entry : propertySchemas.entrySet()) {
            T key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();

            Object value = mapInput.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }

        return true; // все проверки прошли
    }
    //public boolean isValid(Map<?, ?> inputMap) {
        //curMap = new HashMap<>();
        //Map<String, String> input = new HashMap<>();
        //while (true) {
        //    System.out.print("Введите данные (обязательно): ");
            //input = scanner.nextInt().trim(); // Убираем пробелы по краям
            //Map<String, MutableObject> deepCopiedMap = new HashMap<>();
        //    for (Map.Entry<String, MutableObject> entry : input.entrySet()) {
        //        curMap.put(entry.getKey(), entry.getValue().copy());
        //    }
        //    if (validate(curMap)) {
        //        return true;
        //    } else {
        //        System.out.println("Некорректный ввод. Повторите снова.");
        //    }
        //}
    //    if (inputMap == null) {
    //        if (isRequired) {
    //            return false;
    //        } else {
                // не обязательно, значит и null —валид
    //            return true;
    //        }
    //    }
        // Проверка ограничений
    //    if (isRunRequired && isRequired && (inputMap == null || inputMap.isEmpty())) {
    //        return false;
    //    }

    //    if (isRunSizeof && isSizeof && (inputMap.size() != curValSizeMap)) {
    //        return false;
    //    }
    //    return true;
    //}
    //public boolean isValid(Map<?, ?> data) {
    //    if (data == null) {
            // В зависимости от логики, если require() — дань,
            // можно возвращать false, если data null и нужно требовать непустой объект.
            // Предположим, что null считается валидным, если не задано required()
    //        if (isRequired) {
    //            return false;
    //        }
    //        return true;
    //    }
    //    for (Map.Entry<String, BaseSchema<?>> entry : propertySchemas.entrySet()) {
    //        String key = entry.getKey();
    //        hexlet.code.schemas.BaseSchema<?> schema = entry.getValue();

    //        Object value = data.get(key);
            // Передача value в isValid схему, если она существует
    //        if (!schema.isValid(value)) {
    //            return false;
    //        }
    //    }
    //    return true;
    //}
    //private Map<String, String> curMap;
    private boolean isRequired;
    private boolean isSizeof;
    private int curValSizeMap;
    private boolean isRunRequired;
    private boolean isRunSizeof;
    private Map<T, BaseSchema<?>> propertySchemas = new HashMap();
}

