import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Product implements Serializable{
    private static ArrayList<Integer> idProduct = new ArrayList<>();
    private static int lastId = 0;
    private int id;
    private String name;
    private Double price;

    static{
        lastId = loadLastId();
    }

    public Product(String name, double price) {
        this.id = ++lastId;
        this.name = name;
        this.price = price;
        idProduct.add(lastId);
        saveLastId();


    }

    public long getId() {
        return id;
    }

    public long getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        Product.lastId = lastId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private static void saveLastId(){
        try(DataOutputStream lastIdProduct = new DataOutputStream(new FileOutputStream("Data2/lastId.dat"))){
            lastIdProduct.writeInt(lastId);

        }catch (IOException e){
            System.out.println("Error al guardar el ultimo ID");
            e.printStackTrace();
        }
    }
    private static int loadLastId(){
        try(DataInputStream lastIdProduct =  new DataInputStream(new FileInputStream("Data2/lastId.dat"))){
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
        return "Product{" + "id=" + id + ", name='" + name + ", price=" + price + '}';
    }
}
