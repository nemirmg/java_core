package exceptions;

import model.Product;

public class ProductException extends RuntimeException {
    Product product;
    
    public ProductException(Product product) {
        super();
        this.product = product;
    }

    @Override
    public String getMessage() {
        return "\nОшибка! Товар не занесён в базу данных.\n" + product.toString() + "\n";
    }
}
