import java.io.*;
import java.util.ArrayList;

public class Ticket implements Serializable {
    private static int lastId = 0;
    private int id;
    private ArrayList<Product> products;
    private double totalPrice = 0;

    static {
        lastId = loadLastIdTicket();
    }

    public Ticket() {
        this.id = ++lastId;
        this.products = new ArrayList<Product>();
        saveLastIdTicket();
    }

    public int getId() {
        return id;
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
    public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}




    public void addProduct(Product p) {
        this.products.add(p);
    }

    public void calculateFinalPrice() {
        this.totalPrice = this.products.stream().mapToDouble(Product::getPrice).sum();

    }
    private static void saveLastIdTicket(){
        try(DataOutputStream lastIdProduct = new DataOutputStream(new FileOutputStream("Data2/lastIdTicket.dat"))){
            lastIdProduct.writeInt(lastId);

        }catch (IOException e){
            System.out.println("Error al guardar el ultimo ID");
            e.printStackTrace();
        }
    }

    private static int loadLastIdTicket(){
        try(DataInputStream lastIdProduct =  new DataInputStream(new FileInputStream("Data2/lastIdTicket.dat"))){
            return lastIdProduct.readInt();

        }catch(FileNotFoundException e){
            System.out.println("Archivo error en cargar Id");
            return 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
