import org.w3c.dom.ls.LSOutput;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.management.ObjectInstance;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FloristShop {
    Connection connection = Menu.connection;
    private int id;
    private String name;
    ArrayList<Product> stock;
    ArrayList<Ticket> tickets;

    public FloristShop(int id, String name) {
        this.id = id;
        this.name = name;
        this.stock = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getStock() {
        return stock;
    }


    public ArrayList<Ticket> getTickets() {
        return tickets;
    }


    public Product findProduct(ArrayList<Product> stock, int id_product) {
        return stock.stream()
                .filter(n -> n.getId() == (id_product))
                .findFirst()
                .orElse(null);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void addTree(ArrayList<Product> stock) {
        String nameTree = Input.readString("Introduce el nombre del árbol: ");

        String nameTree = "";
        double priceTree = 0.0;
        boolean validPrice = false;
        int idCategory = 1;
        boolean validName = false;
        do{
            nameTree = Input.readString("Introduce el nombre del árbol: ");
            if (nameTree.equalsIgnoreCase("")){
                System.out.println("Opcion no valida");
            }
            else validName = true;
        }while(!validName);


        while (!validPrice) {
            try {
                priceTree = Input.readDouble("Introduce el precio del árbol: ");
                if (priceTree > 0) {
                    validPrice = true;
                } else {
                    System.out.println("El precio debe ser mayor a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para el precio.");
            }
        }

        double heightTree = 0.0;
        boolean validHeight = false;
        while (!validHeight) {
            try {
                heightTree = Input.readDouble("Introduce la altura del árbol: ");
                if (heightTree > 0) {
                    validHeight = true;
                } else {
                    System.out.println("La altura debe ser mayor a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para la altura.");
            }
        }
        SQL.insertProduct(connection, nameTree, priceTree, idCategory, id);
        Product product = SQL.lastProduct(connection);
        Tree tree = new Tree(product.getId(), product.getName(), product.getPrice(), heightTree);
        SQL.insertTree(connection, tree.getId(), heightTree);
        stock.add(tree);

        System.out.println("Árbol " + tree.getName() + " añadido con éxito en " + name + ".");
    }

    public void addFlower(ArrayList<Product> stock) {
        String nameFlower = Input.readString("Introduce el nombre de la flor: ");

        double priceFlower = 0.0;
        boolean validPrice = false;
        int idCategory = 2;

        do{
            nameFlower = Input.readString("Introduce el nombre de la flor: ");
            if (nameFlower.equalsIgnoreCase("")){
                System.out.println("Opcion no valida");
            }
            else validName = true;
        }while(!validName);

        while (!validPrice) {
            try {
                priceFlower = Input.readDouble(("Introduce el precio de la flor: "));
                if (priceFlower > 0) {
                    validPrice = true;
                } else {
                    System.out.println("El precio debe ser mayor a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para el precio.");
            }
        }

        String colorFlower = Input.readString("Introduce el color de la flor: ");

        SQL.insertProduct(connection, nameFlower, priceFlower, idCategory, id);
        Product product = SQL.lastProduct(connection);
        Flower flower = new Flower(product.getId(), product.getName(), product.getPrice(), colorFlower);
        SQL.insertFlower(connection, flower.getId(), colorFlower);
        stock.add(flower);

        System.out.println("Flor " + flower.getName() + " añadida con éxito en " + name + ".");
    }

    public void addDecoration(ArrayList<Product> stock) {
        String nameDecoration = Input.readString("Introduce el nombre de la decoración: ");

        double priceDecoration = 0.0;
        boolean validPrice = false;
        int idCategory = 3;
        String decorationMaterial = "";
        do{
            nameDecoration = Input.readString("Introduce el nombre de la decoración: ");
            if (nameDecoration.equalsIgnoreCase("")){
                System.out.println("Opcion no valida");
            }
            else validName = true;
        }while(!validName);

        while (!validPrice) {
            try {
                priceDecoration = Input.readDouble(("Introduce el precio de la decoración: "));
                if (priceDecoration > 0) {
                    validPrice = true;
                } else {
                    System.out.println("El precio debe ser mayor a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para el precio.");
            }
        }

        boolean exit = false;
        do {
            try {
                switch (Menu.selectMaterialMenu()) {
                    case 1 -> {
                        decorationMaterial = "Madera";
                        exit = true;
                    }
                    case 2 -> {
                        decorationMaterial = "Plástico";
                        exit = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor válido (número entero).");
            }
        } while (!exit);

        SQL.insertProduct(connection, nameDecoration, priceDecoration, idCategory, id);
        Product product = SQL.lastProduct(connection);
        Decoration decoration = new Decoration(product.getId(), product.getName(), product.getPrice(), decorationMaterial);
        SQL.insertDecoration(connection, decoration.getId(), decorationMaterial);
        System.out.println("Decoración " + decoration.getName() + " añadida con éxito en " + name + ".");
        stock.add(decoration);
    }

    public void getShopStock(ArrayList<Product> stock) {
        if (stock.isEmpty()) {
            System.out.println("No hay stock en la floristería " + name + ".");
        } else {
            System.out.println("\nEl stock disponible en la floristería " + name + " es:\n");
            stock.forEach(System.out::println);
        }
    }

    public void removeTree(Product producto) {
        if (producto instanceof Tree) {
            SQL.deleteProduct(connection, producto.getId());
            stock.remove(producto);
            System.out.println(producto.getName() + " ha sido eliminado Correctamente.");
        } else {
            System.out.println("El producto asignado no es un arbol.");
        }
    }

    public void removeFlower(Product producto) {
        if (producto instanceof Flower) {
            SQL.deleteProduct(connection, producto.getId());
            stock.remove(producto);
            System.out.println(producto.getName() + " ha sido eliminado Correctamente.");
        } else {
            System.out.println("El producto asignado no es una flor.");

        }

    }

    public void removeDecoration(Product producto) {
        if (producto instanceof Decoration) {
            SQL.deleteProduct(connection, producto.getId());
            stock.remove(producto);
            System.out.println(producto.getName() + " ha sido eliminado Correctamente.");
        } else {
            System.out.println("El producto asignado no es una decoracion.");
        }
    }

    public void getShopStockWithQuantity() {
        int indiceTree = 0;
        int indiceFlower = 0;
        int indiceDecoration = 0;

        for (Product p : stock) {
            if (p instanceof Tree) {
                indiceTree++;
            }
            if (p instanceof Flower) {
                indiceFlower++;
            }
            if (p instanceof Decoration) {

                indiceDecoration++;

            }
        }
        System.out.println("      --- STOCK --- ");
        System.out.println("--- Total arboles --- \n " + indiceTree);
        System.out.println("--- Total flores --- \n " + indiceFlower);
        System.out.println("--- Total decorados --- \n " + indiceDecoration);
    }

    public double getTotalValue(ArrayList<Product> stock) {
        return stock.stream().mapToDouble(Product::getPrice).sum();
    }

    public void createPurchaseTicket(ArrayList<Product> stock) {
        byte option;
        String yesNo = "";
        boolean endPurchase = false;
        Ticket ticket;

        if (!stock.isEmpty()) {
            SQL.insertTicket(connection, id);
            ticket = SQL.lastTicket(connection);
            do {
                System.out.println("Productos en stock: ");
                for (int i = 1; i <= stock.size(); i++) {
                    System.out.println(i + ". " + stock.get(i - 1).getName() + " " + stock.get(i - 1).getPrice() + " €");
                }
                do {
                    option = Input.readByte("Qué objeto quieres comprar?: ");
                    if (option < 1 || option > stock.size()) {
                        System.out.println("Opcion no valida.\n");
                    }
                } while (option < 1 || option > stock.size());
                Product product = stock.get(option - 1);
                ticket.addProduct(product);
                stock.remove(product);
                SQL.setTicketProducts(connection, product, ticket);
                System.out.println("Producto añadido.\n");
                if (stock.isEmpty()) {
                    System.out.println("No hay mas productos para comprar.");
                    endPurchase = true;
                } else {
                    do {
                        yesNo = Input.readString("Quieres seguir comprando? (S/N): ");
                    } while (!yesNo.equalsIgnoreCase("s") && !yesNo.equalsIgnoreCase("n"));

                    if (yesNo.equalsIgnoreCase("n")) {
                        endPurchase = true;
                    }
                }
            } while (!endPurchase);

            ticket.calculateFinalPrice();
            System.out.println("Precio total ticket: " + ticket.getTotalPrice() + " €");
            SQL.setTicketPrice(connection, ticket);
            addTicket(ticket);

        }else{
            System.out.println("Actualmente, la floristería no dispone de productos en stock.");
            System.out.println("Lamentamos las molestias. Vuelva otro día, gracias.");
        }

    }

    public void getPurchaseTickets(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("No hay ninguna compra en el histórico.");
        } else {
            for (Ticket t : tickets) {
                System.out.println("Ticket ID: " + t.getId() + " tiene los siguientes productos: ");
                t.getProducts().forEach((p) -> System.out.println("- " + p.getName()));
                System.out.println("Precio Total de la compra: " + t.getTotalPrice() + " €\n");
            }
        }

    }

    public double getSalesProfits(ArrayList<Ticket> tickets) {

        return tickets.stream().mapToDouble(Ticket::getTotalPrice).sum();
    }

    public void printInfoStock(Class<? extends Product> product) {
        stock.stream()
                .filter(product::isInstance)
                .forEach(System.out::println);

    }

    public static Ticket getTicket(ArrayList<Ticket> tickets, int id) {
        Ticket ticket = null;
        boolean fonundTicket = false;
        int i = 0;

        while (!fonundTicket && i < tickets.size()) {
            if (tickets.get(i).getId() == id) {
                ticket = tickets.get(i);
                fonundTicket = true;
            }
            i++;
        }
        return ticket;
    }

    @Override
    public String toString() {
        return "FloristShop{" +
                "id: " + id +
                ", name: " + name + '\'' +
                ", stock: " + stock +
                ", tickets: " + tickets +
                '}';
    }
}
