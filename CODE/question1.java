public class OrderSystem {
    private Map<Integer, List<String>> orderList = new HashMap<>();

    public void addNewOrder(Integer customerID, String itemName) {
        List<String> items = orderList.getOrDefault(customerID, new ArrayList<>());
        items.add(itemName);
        orderList.put(customerID, items);
    }

    public void modifyOrder(Integer customerID, Integer itemIndex, String newItemName) {
        List<String> items = orderList.get(customerID);
        if (items != null && itemIndex >= 0 && itemIndex < items.size()) {
            items.set(itemIndex, newItemName);
        } else {
            System.out.println("Invalid item index or customer ID.");
        }
    }

    public void removeOrder(Integer customerID, Integer itemIndex) {
        List<String> items = orderList.get(customerID);
        if (items != null && itemIndex >= 0 && itemIndex < items.size()) {
            items.remove(itemIndex);
        } else {
            System.out.println("Invalid item index or customer ID.");
        }
    }

    public void printOrders() {
        for (Map.Entry<Integer, List<String>> entry : orderList.entrySet()) {
            System.out.println("Customer ID: " + entry.getKey());
            System.out.println("Items: " + String.join(", ", entry.getValue()));
            System.out.println();
        }
    }
}
