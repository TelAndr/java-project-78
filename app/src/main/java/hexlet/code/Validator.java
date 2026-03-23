package hexlet.code;

public class Validator {
    public StringSchema string() {
        return new StringSchema(String.class);
    }
    public NumberSchema number() { return new NumberSchema(Integer.class); }
    public MapSchema map() { return new MapSchema(); }
}