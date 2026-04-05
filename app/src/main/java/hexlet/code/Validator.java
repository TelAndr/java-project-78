package hexlet.code;

public class Validator {
    /**
     * определяет схему StringSchema. Эта схема используется для валидации строк.
     *
     * @return возвращает объект StringSchema
     */
    public StringSchema string() {
        return new StringSchema(String.class);
    }
    /**
     * определяет схему NumberSchema. Эта схема используется для валидации чисел.
     *
     * @return возвращает объект NumberSchema
     */
    public NumberSchema number() { return new NumberSchema(Integer.class); }
    /**
     * определяет схему MapSchema. Эта схема используется для валидации объектов типа Map.
     *
     * @return возвращает объект MapSchema
     */
    public MapSchema map() { return new MapSchema(); }
}
