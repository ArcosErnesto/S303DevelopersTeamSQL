import java.sql.*;
import java.util.ArrayList;

public class SQL {

    public static Connection connectDB() {
        Connection connection;
        String host = "jdbc:mysql://localhost/";
        String user = "root";
        String pass = "Calavera33";
        String bd = "florist_shop";

        System.out.println("Conectando...");

        try {
            connection = DriverManager.getConnection(host + bd, user, pass);
            System.out.println("Concectado a BBDD " + bd + ".");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void loadFloristShop(Connection connection, ArrayList<FloristShop> floristerias) {
        String loadFlorist = "SELECT * FROM floristshop";
        Statement st;
        ResultSet rs;
        int id;
        String name;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(loadFlorist);
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");

                FloristShop floristShop = new FloristShop(id, name);
                floristerias.add(floristShop);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void loadTickets(Connection connection, ArrayList<FloristShop> floristerias) {
        String loadFlorist = "SELECT * FROM ticket";
        Statement st;
        ResultSet rs;
        int id;
        int idFloristShop;
        Double totalPrice;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(loadFlorist);
            while (rs.next()) {
                id = rs.getInt("id");
                idFloristShop = rs.getInt("floristshop_id");
                totalPrice = rs.getDouble("total_price");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setTotalPrice(totalPrice);
                FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                floristShop.getTickets().add(ticket);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void loadProducts(Connection connection, ArrayList<FloristShop> floristerias) {
        String loadProducts = "SELECT p.*, t.height, f.color, d.material " +
                "FROM product p " +
                "LEFT JOIN florist_shop.tree t ON p.idproduct = t.product_id " +
                "LEFT JOIN florist_shop.flower f ON p.idproduct = f.product_id " +
                "LEFT JOIN florist_shop.decoration d ON p.idproduct = d.product_id";

        try (Statement stProducts = connection.createStatement();
             ResultSet rsProducts = stProducts.executeQuery(loadProducts)) {

            while (rsProducts.next()) {
                int id = rsProducts.getInt("idproduct");
                String name = rsProducts.getString("name");
                double price = rsProducts.getDouble("price");
                int idCategory = rsProducts.getInt("category_id");
                int idFloristShop = rsProducts.getInt("floristshop_id");
                int idTicket = rsProducts.getInt("ticket_id");
                //System.out.println(idTicket);
                if (idCategory == 1) {
                    if (idTicket != 0) {
                        double height = rsProducts.getDouble("height");
                        Tree tree = new Tree(id, name, price, height);
                        FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                        Ticket ticket = FloristShop.getTicket(floristShop.getTickets(), idFloristShop);
                        ticket.getProducts().add(tree);
                    } else {
                        double height = rsProducts.getDouble("height");
                        Tree tree = new Tree(id, name, price, height);
                        FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                        floristShop.getStock().add(tree);
                    }
                } else if (idCategory == 2) {
                    if (idTicket != 0) {
                        String color = rsProducts.getString("color");
                        Flower flower = new Flower(id, name, price, color);
                        FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                        Ticket ticket = FloristShop.getTicket(floristShop.getTickets(), idFloristShop);
                        ticket.getProducts().add(flower);
                    } else {
                        String color = rsProducts.getString("color");
                        Flower flower = new Flower(id, name, price, color);
                        FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                        floristShop.getStock().add(flower);
                    }
                } else if (idCategory == 3) {
                    if (idTicket != 0) {
                        String material = rsProducts.getString("material");
                        Decoration decoration = new Decoration(id, name, price, material);
                        FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                        Ticket ticket = FloristShop.getTicket(floristShop.getTickets(), idFloristShop);
                        ticket.getProducts().add(decoration);
                    } else{
                        String material = rsProducts.getString("material");
                        Decoration decoration = new Decoration(id, name, price, material);
                        FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                        floristShop.getStock().add(decoration);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void insertFloristShop(Connection connection, String name) {
        String añadir = "insert into floristshop (name) values ('" + name + "');";
        Statement st;
        int result;

        try {
            st = connection.createStatement();
            result = st.executeUpdate(añadir);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static FloristShop lastFloristShop(Connection connection) {
        String sql = "SELECT * FROM floristshop ORDER BY id DESC LIMIT 1";
        FloristShop floristShop = null;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                floristShop = new FloristShop(id, name);
            } else {
                System.out.println("No hay filas en el conjunto de resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return floristShop;
    }

    public static void disconnectDB(Connection connection) {
        try {
            connection.close();
            System.out.println("Desconectando de BBDD...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
