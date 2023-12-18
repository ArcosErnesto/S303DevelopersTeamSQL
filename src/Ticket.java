import java.util.ArrayList;

public class Ticket {
    private int id;
    private ArrayList<Product> products;
    private double totalPrice = 0;

    public Ticket() {
        this.products = new ArrayList<Product>();
    }

    public Ticket(int id) {
        this.id = id;
        this.products = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    public void calculateFinalPrice() {
        this.totalPrice = this.products.stream().mapToDouble(Product::getPrice).sum();

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
