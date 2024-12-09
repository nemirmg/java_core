package exceptions;

public class AmountException extends RuntimeException {
    int amount;
    int bound;
    
    public AmountException(int amount, int bound) {
        super();
        this.amount = amount;
        this.bound = bound;
    }

    @Override
    public String getMessage() {
        return "\nОшибка! Введено неправильное количество: " + amount + "\nУкажите значение из интервала [0, " + bound + "]\n";
    }
}
