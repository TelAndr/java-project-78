package hexlet.code;

import java.util.Scanner;
public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema(Class<Integer> type) {
        super(type);
    }

    @Override
    protected Integer cast(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        throw new ClassCastException("Value is not a Number");
    }
    public NumberSchema required() {
        isRunRequired = true;
        isRequired = true;
        return this;
    }
    public NumberSchema positive() {
        isRunPositive = true;
        isPositive = true;
        return this;
    }
    public  NumberSchema range(int lowerRange, int upperRange) {
        isRunRange = true;
        this.curLowerRange = lowerRange;
        this.curUpperRange = upperRange;
        isFallsWithinRange = true;
        return this;
    }
    @Override
    protected boolean validate(Integer curNumber) {
        boolean result = true;
        Integer val = curNumber.intValue();
        if (isRequired && ((curNumber == null) )) {
            result = false;
        }
        if (isPositive &&  (val <= 0)) {
            result = false;
        }
        if (isFallsWithinRange && (val < curLowerRange || val > curUpperRange)) {
            result = false;
        }
        return result;
    }
    //public boolean isValid() {
    //    int input;
    //    while (true) {
    //        System.out.print("Введите данные (обязательно): ");
    //        input = scanner.nextInt(); // Убираем пробелы по краям
    //        curNumber = input;
    //        if (validate(curNumber)) {
    //            return true;
    //        } else {
    //            System.out.println("Некорректный ввод. Повторите снова.");
    //        }
    //    }
    //}
    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired; // например, null допустим, если не required
        }
        if (!(value instanceof Integer)) {
            return false; // тип не совпадает
        }
        return validate((Integer) value);
    }
    private Integer curNumber;
    private boolean isRequired;
    private boolean isPositive;
    private Integer curLowerRange;
    private Integer curUpperRange;
    private boolean isFallsWithinRange;
    private boolean isRunRequired;
    private boolean isRunPositive;
    private boolean isRunRange;
    private static Scanner scanner = new Scanner(System.in);
}