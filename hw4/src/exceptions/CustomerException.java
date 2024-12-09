package exceptions;

import model.Customer;

public class CustomerException extends RuntimeException {
    Customer customer;
    public CustomerException(Customer customer) {
        super();
        this.customer = customer;
    }

    @Override
    public String getMessage() {
        return "\nОшибка! Покупатель не занесён в базу данных.\n" + customer.toString() + "\n";
    }
}
