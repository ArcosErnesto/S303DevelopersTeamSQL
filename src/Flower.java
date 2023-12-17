public class Flower extends Product{
    private String color;

    public Flower(int id, String name, double price, String color) {
        super(id, name, price);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flower{" + "id=" + super.getId() + ", name='" + super.getName() + ", price=" + super.getPrice() +", color=" + color + '}';
    }
}
