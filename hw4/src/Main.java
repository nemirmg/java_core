import java.time.LocalDate;
import java.util.Arrays;

import exceptions.AmountException;
import exceptions.CustomerException;
import exceptions.ProductException;
import model.Customer;
import model.Gender;
import model.Holidays;
import model.Order;
import model.Product;

public class Main {
    private static final int BOUND = 100;
    private static int count = 0;
    
    private static final Customer[] customers = {
        new Customer("Иванов", "Пётр", "Сергеевич", LocalDate.of(1985, 2, 3), "+7 888 888-88-88", Gender.MALE),
        new Customer("Петрова", "Анна", "Витальевна", LocalDate.of(1994, 7, 28), "+7 905 123-45-67", Gender.FEMALE)
    };

    private static final Product[] products = {
        new Product("Стиральный порошок", 509.99),
        new Product("Пылесос", 7800.00),
        new Product("Обои", 381.70),
        new Product("Скотч", 175.00),
        new Product("Набор ножей", 2400)
    };

    private static Order[] orders = new Order[5];

    private static boolean isInArray(Object o, Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(o)) { return true; }
        }
        return false;
    }

    private static Order buy(Customer customer, Product product, int amount) {
        if (!isInArray(customer, customers)) {
            throw new CustomerException(customer);
        }
        if (!isInArray(product, products)) {
            throw new ProductException(product);
        }
        if (amount < 0 || amount > BOUND) {
            throw new AmountException(amount, BOUND);
        }
        return new Order(customer, product, amount);
    }

    private static void addOrder(Customer customer, Product product, int amount) {
        try {
            orders[count] = buy(customer, product, amount);
            count++;
        } catch (CustomerException e) {
            throw new RuntimeException(e);
        } catch (ProductException e) {
            System.out.println(e.getMessage());
        } catch (AmountException e) {
            orders[count++] = buy(customer, product, 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Заказов в базе данных: " + count);
        }
    }

    private static void sendCongratulations(LocalDate date) {
        for (Customer customer : customers) {
            switch (Holidays.getHoliday(date)) {
                case Holidays.NEW_YEAR: 
                    System.out.println("С наступающим Новым Годом, " + customer.getName() + "!");
                    break;
                case Holidays.MARCH_8:
                    if (customer.getGender() == Gender.FEMALE) {
                        System.out.println("С 8 марта, " + customer.getName() + "!");
                    }
                    break;
                case Holidays.FEBRUARY_23:
                if (customer.getGender() == Gender.MALE) {
                    System.out.println("С 23 февраля, " + customer.getName() + "!");
                }
                break;
                default : return;
            }
        }
    }
    
    public static void main(String[] args) {
        Customer newCustomer = new Customer("Соболев", "Николай", "Игоревич", LocalDate.of(1999, 12, 12), "+7 555 900-00-00", Gender.MALE);
        Product newProduct = new Product("Ластик", 74.8);

        sendCongratulations(LocalDate.now());

        addOrder(customers[0], products[0], 5);
        addOrder(customers[0], products[1], 6);
        addOrder(customers[1], products[3], 7);
        addOrder(customers[1], newProduct, 2); // товар не из БД
        addOrder(customers[1], products[2], -5); // отрицательное количество
        addOrder(customers[0], products[2], 101); // количество товаров превышает max
        addOrder(customers[1], products[2], 99); // превышение max количества заказов
        
        System.out.println("\nВсе заказы:\n===========");
        Arrays.stream(orders).forEach(System.out::println);
        
        addOrder(newCustomer, products[1], 10); // покупатель не из БД
    }
}
