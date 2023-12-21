import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    private String filePath;
    private boolean append;

    public FileManager(String filePath, boolean append) {

        this.filePath = filePath;
        this.append= append;
    }

    public void saveToFile(String data) {
        try {
            FileWriter filewriter = new FileWriter(filePath, append);
            BufferedWriter writer = new BufferedWriter(filewriter);
            writer.write(data);
            writer.close();

        }catch(IOException e) {

            System.out.println("IOException: Error the file can't to save.");
            e.printStackTrace();
        }

    }

    public String readFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                content.append(line).append("\n");

            }
            return content.toString();
        }catch(IOException e) {

            System.out.println("IOEXception: Error reading the file");
            e.printStackTrace();
            return null;
        }
    }

    public  void saveLastId(FloristShop shop, String filepath){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath))){
            ArrayList<Product> products = shop.getStock();
            Product p = products.get(-1);
            out.writeObject(p.getLastId());
            System.out.println("List of shops serialized and saved in " + filepath);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadLastId(String filePath){

    }

    public void serializeObject(FloristShop shops, String filepath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath))){
            out.writeObject(shops);
        } catch(IOException e) {
            System.out.println("Error al serializar la floristshop:");
            e.printStackTrace();
        }
    }



    public Object desSerializeObject(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))){
            FloristShop shops = (FloristShop) in.readObject();
            return shops;
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar la floristshop:");
            e.printStackTrace();

            return null;
        }
    }

}

