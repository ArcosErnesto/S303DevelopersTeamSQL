public class Decoration extends Product{
    private String material;

    public Decoration(String name, double price, String material) {
        super(name, price);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    
  

    @Override
    public String toString() {
        return "Decoration{" + "id: " + super.getId() + ", name: " + super.getName() + ", price:" + super.getPrice() +", material: " + material + '}';
    }
}