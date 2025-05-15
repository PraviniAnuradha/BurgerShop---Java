import java.util.*;

public class BurgerShop {
    private static final double BURGER_PRICE = 500.0;
    private static final Scanner input = new Scanner(System.in);
    private static final List<Order> orders = new ArrayList<>();
    private static final Map<String, Customer> customers = new HashMap<>();
    private static int orderCount = 1;

    public static void main(String[] args) {
        while (true) {
            clearConsole();
            showMainMenu();
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== iHungry Burger Shop ===");
        System.out.println("1. Place Order");
        System.out.println("2. Search Best Customer");
        System.out.println("3. Search Order");
        System.out.println("4. Search Customer");
        System.out.println("5. View Orders");
        System.out.println("6. Update Order");
        System.out.println("7. Exit");
        System.out.print("Enter option: ");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                placeOrder();
                break;
            case 2:
                searchBestCustomer();
                break;
            case 3:
                searchOrder();
                break;
            case 4:
                searchCustomer();
                break;
            case 5:
                viewOrders();
                break;
            case 6:
                updateOrder();
                break;
            case 7:
                exitApp();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void placeOrder() {
        clearConsole();
        System.out.println("\n--- Place Order ---");
        String orderId = String.format("B%04d", orderCount++);

        System.out.print("Enter Customer Phone Number: ");
        String phone = input.next();
        Customer customer = customers.getOrDefault(phone, promptCustomerName(phone));
        customers.putIfAbsent(phone, customer);

        System.out.print("Enter quantity: ");
        int qty = input.nextInt();
        double total = qty * BURGER_PRICE;

        Order order = new Order(orderId, customer, qty, total, OrderStatus.PREPARING);
        orders.add(order);

        System.out.println("Order placed. ID: " + orderId + ", Total: Rs. " + total);
        pause();
    }

    private static Customer promptCustomerName(String phone) {
        System.out.print("Enter Customer Name: ");
        String name = input.next();
        return new Customer(phone, name);
    }

    private static void searchBestCustomer() {
        Map<String, Double> totalSpent = new HashMap<>();
        for (Order order : orders) {
            String phone = order.getCustomer().getPhone();
            totalSpent.put(phone, totalSpent.getOrDefault(phone, 0.0) + order.getValue());
        }

        if (totalSpent.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            String bestCustomer = Collections.max(totalSpent.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("\nBest Customer: " + customers.get(bestCustomer).getName() +
                    " (" + bestCustomer + ") - Total Spent: Rs. " + totalSpent.get(bestCustomer));
        }
        pause();
    }

    private static void searchOrder() {
        System.out.print("Enter Order ID: ");
        String id = input.next();
        orders.stream().filter(o -> o.getOrderId().equals(id)).findFirst()
            .ifPresentOrElse(Order::display, () -> System.out.println("Order not found."));
        pause();
    }

    private static void searchCustomer() {
        System.out.print("Enter Customer Phone: ");
        String phone = input.next();
        Customer customer = customers.get(phone);
        if (customer != null) {
            System.out.println("Customer Name: " + customer.getName());
            orders.stream().filter(o -> o.getCustomer().getPhone().equals(phone)).forEach(Order::display);
        } else {
            System.out.println("Customer not found.");
        }
        pause();
    }

    private static void viewOrders() {
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println("\n--- " + status + " ORDERS ---");
            orders.stream().filter(o -> o.getStatus() == status).forEach(Order::display);
        }
        pause();
    }

    private static void updateOrder() {
        System.out.print("Enter Order ID to update: ");
        String id = input.next();
        Order order = orders.stream().filter(o -> o.getOrderId().equals(id)).findFirst().orElse(null);
        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println("1. Change Quantity\n2. Update Status");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter new quantity: ");
                    int qty = input.nextInt();
                    order.setQty(qty);
                    order.setValue(qty * BURGER_PRICE);
                    break;
                case 2:
                    System.out.println("Choose status: 1. PREPARING 2. DELIVERED 3. CANCEL");
                    int s = input.nextInt();
                    if (s >= 1 && s <= 3) {
                        order.setStatus(OrderStatus.values()[s - 1]);
                    } else {
                        System.out.println("Invalid status choice.");
                    }
                    break;
                default:
                    System.out.println("Invalid update option.");
            }
        }
        pause();
    }

    private static void exitApp() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause() {
        System.out.print("\nPress Enter to continue...");
        try { System.in.read(); } catch (Exception ignored) {}
    }
}

// Customer
class Customer {
    private final String phone;
    private final String name;

    public Customer(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() { return phone; }
    public String getName() { return name; }
}

// Order
class Order {
    private final String orderId;
    private final Customer customer;
    private int qty;
    private double value;
    private OrderStatus status;

    public Order(String orderId, Customer customer, int qty, double value, OrderStatus status) {
        this.orderId = orderId;
        this.customer = customer;
        this.qty = qty;
        this.value = value;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public int getQty() { return qty; }
    public double getValue() { return value; }
    public OrderStatus getStatus() { return status; }

    public void setQty(int qty) { this.qty = qty; }
    public void setValue(double value) { this.value = value; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public void display() {
        System.out.printf("Order ID: %s | Customer: %s | Qty: %d | Total: Rs. %.2f | Status: %s\n",
                orderId, customer.getName(), qty, value, status);
    }
}

// OrderStatus
enum OrderStatus {
    PREPARING,
    DELIVERED,
    CANCEL
}
