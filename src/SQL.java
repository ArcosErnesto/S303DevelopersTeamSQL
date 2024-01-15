import java.sql.*;
import java.util.ArrayList;

public class SQL {

    public static Connection connectDB() {
        Connection connection;
        String host = "jdbc:mysql://localhost/";
        String user = "root";
        String pass = "root";
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
        String loadTickets = "SELECT * FROM ticket";
        Statement st;
        ResultSet rs;
        int id;
        int idFloristShop;
        double totalPrice;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(loadTickets);
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

                if (idCategory == 1) {
                    double height = rsProducts.getDouble("height");
                    Tree tree = new Tree(id, name, price, height);
                    FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                    if (idTicket != 0) {
                        Ticket ticket = FloristShop.getTicket(floristShop.getTickets(), idTicket);
                        ticket.getProducts().add(tree);
                    } else {
                        floristShop.getStock().add(tree);
                    }
                } else if (idCategory == 2) {
                    String color = rsProducts.getString("color");
                    Flower flower = new Flower(id, name, price, color);
                    FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                    if (idTicket != 0) {
                        Ticket ticket = FloristShop.getTicket(floristShop.getTickets(), idTicket);
                        ticket.getProducts().add(flower);
                    } else {
                        floristShop.getStock().add(flower);
                    }
                } else if (idCategory == 3) {
                    String material = rsProducts.getString("material");
                    Decoration decoration = new Decoration(id, name, price, material);
                    FloristShop floristShop = Main.findIdFlowerShop(floristerias, idFloristShop);
                    if (idTicket != 0) {
                        Ticket ticket = FloristShop.getTicket(floristShop.getTickets(), idTicket);
                        ticket.getProducts().add(decoration);
                    } else {
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
        String insertFloristShop = "insert into floristshop (name) values ('" + name + "');";
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(insertFloristShop);
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

    public static void insertProduct(Connection connection, String nameTree, double priceTree, int idCategory, int idFloristShop) {
        String insertProduct = "insert into product (name, price, category_id, floristshop_id) values ('" + nameTree + "', " + priceTree + ", "+idCategory+", "
                + idFloristShop + ");";
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(insertProduct);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void insertTree(Connection connection, int id, double heightTree) {
        String insertTree = "insert into tree (product_id, height) values (" + id + ", " + heightTree +")" ;
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(insertTree);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void insertFlower(Connection connection, int id, String flowerColor) {
        String insertTree = "insert into flower (product_id, color) values (" + id + ", '" + flowerColor +"')" ;
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(insertTree);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void insertDecoration(Connection connection, int id, String material) {
        String insertTree = "insert into decoration (product_id, material) values (" + id + ", '" + material +"')" ;
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(insertTree);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void deleteProduct(Connection connection, int id) {
        String deleteProduct = "DELETE FROM product WHERE idproduct = '" +id+"'";
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(deleteProduct);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la eliminación: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Product lastProduct(Connection connection) {
        String sql = "SELECT * FROM product ORDER BY idproduct DESC LIMIT 1";
        Product product = null;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("idproduct");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                product = new Product(id, name, price);
            } else {
                System.out.println("No hay filas en el conjunto de resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return product;
    }

    public static void insertTicket(Connection connection, int idFloristShop ) {
        String insertFloristShop = "insert into ticket (floristshop_id) values (" + idFloristShop + ");";
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(insertFloristShop);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Ticket lastTicket(Connection connection) {
        String lastTicket = "SELECT * FROM ticket ORDER BY id DESC LIMIT 1";
        Ticket ticket = null;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(lastTicket);

            if (rs.next()) {
                int id = rs.getInt("id");

                ticket = new Ticket(id);
            } else {
                System.out.println("No hay filas en el conjunto de resultados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return ticket;
    }

    public static void setTicketProducts (Connection connection, Product product, Ticket ticket){
        String setTicketProducts = "UPDATE product SET ticket_id = "+ticket.getId()+" WHERE idproduct = "+product.getId();
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(setTicketProducts);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void setTicketPrice (Connection connection,Ticket ticket){
        String setTicketProducts = "UPDATE ticket SET total_price = "+ticket.getTotalPrice()+" WHERE id = "+ticket.getId();
        Statement st;

        try {
            st = connection.createStatement();
            st.executeUpdate(setTicketProducts);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
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
