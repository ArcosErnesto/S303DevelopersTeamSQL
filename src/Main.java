import java.sql.Connection;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

        ArrayList<FloristShop> floristShops = new ArrayList<>();
        Connection connection = Menu.connection;
        SQL.loadFloristShop(connection, floristShops);
        SQL.loadTickets(connection, floristShops);
        SQL.loadProducts(connection, floristShops);
        Menu.getMainMenu(floristShops);
        SQL.disconnectDB(connection);
    }

    public static FloristShop findFlowerShop(ArrayList<FloristShop> floristShops,String inputName) {
        int i = 0;
        FloristShop floristShop = null;
        while (floristShop == null && i<floristShops.size()){
            if (floristShops.get(i).getName().equalsIgnoreCase(inputName)){
                floristShop = floristShops.get(i);
            }
            i++;
        }
        return floristShop;
    }

    public static FloristShop findIdFlowerShop(ArrayList<FloristShop> floristShops, int id) {
        int i = 0;
        FloristShop floristShop = null;
        while (floristShop == null && i<floristShops.size()){
            if (floristShops.get(i).getId() == id){
                floristShop = floristShops.get(i);
            }
            i++;
        }
        return floristShop;
    }

    public static String nameFloristShop (){
        return Input.readString("Introduce el nombre de la floristeria: ");
    }

}