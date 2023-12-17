public class Tree extends Product{
    private double height;

    public Tree(int id, String name, double price, double height) {
        super(id, name, price);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Tree{" + "id: " + super.getId() + ", name: " + super.getName() + ", price: " + super.getPrice() +", height: " + height + '}';
    }
}
