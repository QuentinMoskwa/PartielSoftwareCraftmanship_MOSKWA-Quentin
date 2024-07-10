public class OrderProcessor {
    private final Database database;
    private final EmailService emailService;
    private final InventorySystem inventorySystem;

    public OrderProcessor(Database database, EmailService emailService, InventorySystem inventorySystem) {
        this.database = database;
        this.emailService = emailService;
        this.inventorySystem = inventorySystem;
    }

    public void processOrder(Order order) {
        if (!areItemsAvailable(order.getItems())) {
            throw new RuntimeException("Item not available in inventory");
        }
        
        applyDiscountIfLoyalCustomer(order);
        database.saveOrder(order);
        sendOrderConfirmation(order);
        updateInventory(order.getItems());
    }

    private boolean areItemsAvailable(List<Item> items) {
        for (Item item : items) {
            if (!inventorySystem.isItemAvailable(item)) {
                return false;
            }
        }
        return true;
    }

    private void sendOrderConfirmation(Order order) {
        String message = "Your order has been received and is being processed.";
        emailService.sendEmail(order.getCustomerEmail(), "Order Confirmation", message);
    }

    private void updateInventory(List<Item> items) {
        for (Item item : items) {
            inventorySystem.updateInventory(item, item.getQuantity() * -1);
        }
    }

    private void applyDiscountIfLoyalCustomer(Order order) {
        if (order instanceof LoyalCustomerOrder) {
            LoyalCustomerOrder loyalCustomerOrder = (LoyalCustomerOrder) order;
            loyalCustomerOrder.applyDiscount();
        }
    }
}
public class LoyalCustomerOrder extends Order {
    @Override
    public void applyDiscount() 
    {
        // Appliquer une remise de 10%
        setTotalPrice(getTotalPrice() * 0.9);
    }
}
