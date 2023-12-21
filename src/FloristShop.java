import org.w3c.dom.ls.LSOutput;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.management.ObjectInstance;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FloristShop implements Serializable {
    private static int lastId = 0;
    private int id;
    private String name;
    ArrayList<Product> stock;
    ArrayList<Ticket> tickets;

    public FloristShop(String name) {
        this.id = ++lastId;
        this.name = name;
        this.stock = new ArrayList<Product>();
        this.tickets = new ArrayList<Ticket>();
    }

    public FloristShop(String name, ArrayList<Product> stock, ArrayList<Ticket> tickets) {
        this.name = name;
        this.stock = stock;
        this.tickets = tickets;
    }
    public FloristShop(){

    }

    public static void setLastId(int lastId) {
      FloristShop.lastId = lastId;
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

        String nameTree = "";
        double priceTree = 0.0;
        boolean validPrice = false;
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
                if (priceTree >= 0) {
                    validPrice = true;
                } else {
                    System.out.println("El precio debe ser mayor o igual a cero.");
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
                if (heightTree >= 0) {
                    validHeight = true;
                } else {
                    System.out.println("La altura debe ser mayor o igual a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para la altura.");
            }
        }

        Tree tree = new Tree(nameTree, priceTree, heightTree);
        stock.add(tree);

        System.out.println("Árbol " + tree.getName() + " añadido con éxito en " + name + ".");
    }

    public void addFlower(ArrayList<Product> stock) {
        String nameFlower;
        boolean validName = false;
        double priceFlower = 0.0;
        boolean validPrice = false;

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
                if (priceFlower >= 0) {
                    validPrice = true;
                } else {
                    System.out.println("El precio debe ser mayor o igual a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para el precio.");
            }
        }

        String colorFlower;

        boolean validcolor = false;
        do{
            colorFlower = Input.readString("Introduce el color de la flor: ");
            if (colorFlower.equalsIgnoreCase("")){
                System.out.println("Opcion no valida");
            }
            else validcolor = true;
        }while(!validcolor);

        Flower flower = new Flower(nameFlower, priceFlower, colorFlower);
        stock.add(flower);

        System.out.println("Flor " + flower.getName() + " añadido con éxito en " + name + ".");
    }

    public void addDecoration(ArrayList<Product> stock) {
        String nameDecoration ;
        boolean validName = false;

        double priceDecoration = 0.0;
        boolean validPrice = false;
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
                if (priceDecoration >= 0) {
                    validPrice = true;
                } else {
                    System.out.println("El precio debe ser mayor o igual a cero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido para el precio.");
            }
        }

        boolean exit = false;
        do {
            try {
                switch (Menu.selectMaterialMenu()) {
                    case 1:
                        Decoration decoration = new Decoration(nameDecoration, priceDecoration, "Madera");
                        System.out.println("Decoración " + decoration.getName() + " añadido con éxito en " + name + ".");
                        stock.add(decoration);
                        exit = true;
                        break;
                    case 2:
                        decoration = new Decoration(nameDecoration, priceDecoration, "Plástico");
                        System.out.println("Decoración " + decoration.getName() + " añadido con éxito en " + name + ".");
                        stock.add(decoration);
                        exit = true;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor válido (número entero).");
            }
        } while (!exit);

    }

    public void getShopStock(ArrayList<Product> stock) {
        if (stock.isEmpty()) {
            System.out.println("No hay stock en la floristería " + name + ".");
        } else {
            System.out.println("\nEl stock disponible en la floristería " + name + " es:\n");
            stock.forEach(product -> System.out.println(product));
        }
    }

    public void removeTree(Product producto) {
        if (producto instanceof Tree) {
            stock.remove(producto);
            System.out.println(producto.getName() + " ha sido eliminado Correctamente.");
        } else {
            System.out.println("El producto asignado no es un arbol.");
        }
    }

    public void removeFlower(Product producto) {
        if (producto instanceof Flower) {
            stock.remove(producto);
            System.out.println(producto.getName() + " ha sido eliminado Correctamente.");
        } else {
            System.out.println("El producto asignado no es una flor.");

        }

    }

    public void removeDecoration(Product producto) {
        if (producto instanceof Decoration) {
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

        double totalValue = stock.stream().mapToDouble(Product::getPrice).sum();

        return totalValue;
    }

    public void createPurchaseTicket(ArrayList<Product> stock) {
        byte option = -1;
        String yesNo = "";
        boolean endPurchase = false;
        double precioTicket = 0;
        Ticket ticket = new Ticket();

        if (!stock.isEmpty()) {
            do {
                do {
                    System.out.println("Productos en stock: ");
                    for (int i = 1; i <= stock.size(); i++) {
                        System.out.println(i + ". " + stock.get(i - 1).getName() + " " + stock.get(i - 1).getPrice() + " €");
                    }

                        try{
                            option = Input.readByte("Qué objeto quieres comprar?: ");
                            if (option < 1 || option > stock.size()) {
                                System.out.println("Opcion no valida.\n");
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Opcion no valida\n");
                        }

                } while (option < 1 || option > stock.size());

                ticket.addProduct(stock.get(option - 1));
                stock.remove(stock.get(option - 1));
                System.out.println("Producto añadido.\n");
                if (stock.isEmpty()) {
                    System.out.println("Actualmente, la floristería no dispone de más productos en stock.");
                    endPurchase = true;
                } else {
                    do {
                        yesNo = Input.readString("Quieres seguir comprando? (S/N): ");
                    } while (!yesNo.equalsIgnoreCase("s") && !yesNo.equalsIgnoreCase("n"));

                    if (yesNo.equalsIgnoreCase("n")) {
                        endPurchase = true;
                    }
                }
            } while (endPurchase == false);
            ticket.calculateFinalPrice();
            System.out.println("Compra realizada con exito");
            System.out.println("Precio total ticket: " + ticket.getTotalPrice() + " €");
            addTicket(ticket);

        }else{
            System.out.println("Actualmente, la floristería no dispone de productos en stock.");
            System.out.println("Lamentamos las molestias. Vuelva otro día, gracias.");

        }

    }

    public void getPurchaseTickets(ArrayList<Ticket> tickets) {

        for (Ticket t : tickets) {
            System.out.println("\nTicket ID: " + t.getId() + " tiene los siguientes productos: ");
            t.getProducts().forEach((p) -> System.out.println("- " + p.getName()));
            System.out.println("Precio Total de la compra: " + t.getTotalPrice() + " €\n");
        }

    }

    public double getSalesProfits(ArrayList<Ticket> tickets) {
        double totalEarns = tickets.stream().mapToDouble(Ticket::getTotalPrice).sum();
        return totalEarns;
    }

    public void printInfoStock(Class<? extends Product> product) {
        stock.stream()
                .filter(p -> product.isInstance(p))
                .forEach(System.out::println);

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
