import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu {
    static Connection connection = SQL.connectDB();


    public static void getMainMenu(ArrayList<FloristShop> floristShops) {
        boolean exit = false;
        FloristShop floristShop;
        String shopName = "";
        boolean continueLoop;

        do {
            try {
                switch (menu()) {
                    case 1 -> {
                        toWatchFloristShop(floristShops);
                        createFloristShop(floristShops);
                    }
                    case 2 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.addTree(floristShop.getStock());
                        }
                    }
                    case 3 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.addFlower(floristShop.getStock());
                        }
                    }
                    case 4 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.addDecoration(floristShop.getStock());
                        }
                    }
                    case 5 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.getShopStock(floristShop.getStock());
                        }
                    }
                    case 6 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        continueLoop = false;
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            long treeCount = floristShop.getStock().stream()
                                    .filter(p -> p instanceof Tree)
                                    .count();
                            if (treeCount == 0) {
                                System.out.println("En estos momentos no hay arboles en el Stock");

                            } else {
                                do {
                                    System.out.println("  --- Stock --- ");
                                    floristShop.printInfoStock(Tree.class);
                                    System.out.println();//

                                    try {
                                        int idProduct = Input.readInt("Dime el Id del árbol que quieres eliminar: ");
                                        Product producto = floristShop.findProduct(floristShop.getStock(), idProduct);

                                        if (producto == null) {
                                            System.out.println("Producto no encontrado con el Id: " + idProduct);
                                        } else {
                                            System.out.println("Estás a punto de eliminar el árbol: " + producto.getName());
                                            String confirm = Input.readString("¿Estás seguro de eliminarlo? Si/No ");

                                            if (confirm.equalsIgnoreCase("si")) {
                                                floristShop.removeTree(producto);
                                                continueLoop = true;
                                            } else if (confirm.equalsIgnoreCase("no")) {
                                                System.out.println("Operación cancelada");
                                                continueLoop = true;
                                            } else {
                                                System.out.println("Opción no válida");
                                            }
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Error: Ingresa un valor válido para el Id.");
                                    }
                                } while (!continueLoop);
                            }
                        }
                    }
                    case 7 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        continueLoop = false;
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            long flowerCount = floristShop.getStock().stream()
                                    .filter(p -> p instanceof Flower)
                                    .count();

                            if (flowerCount == 0) {
                                System.out.println("En estos momentos no hay flores en el stock.");
                            } else {
                                do {
                                    try {
                                        System.out.println("  --- Stock --- ");
                                        floristShop.printInfoStock(Flower.class);
                                        System.out.println();
                                        int idProduct = Input.readInt("Dime el Id de la flor que quieres eliminar: ");
                                        Product producto = floristShop.findProduct(floristShop.getStock(), idProduct);

                                        if (producto == null) {
                                            System.out.println("Producto no encontrado con el Id: " + idProduct);
                                        } else {
                                            System.out.println("Estás a punto de eliminar la flor: " + producto.getName());
                                            String confirm = Input.readString("¿Estás seguro de eliminarlo? Si/NO ");
                                            if (confirm.equalsIgnoreCase("si")) {
                                                floristShop.removeFlower(producto);
                                                continueLoop = true;
                                            } else if (confirm.equalsIgnoreCase("no")) {
                                                System.out.println("Operación cancelada");
                                                continueLoop = true;
                                            } else {
                                                System.out.println("Opción no válida");
                                            }
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Error: Ingresa un valor válido para el Id.");
                                    }
                                } while (!continueLoop);
                            }
                        }
                    }
                    case 8 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            long decorationCount = floristShop.getStock().stream()
                                    .filter(p -> p instanceof Decoration)
                                    .count();
                            if (decorationCount == 0) {
                                System.out.println("En estos momentos no hay decoracion en el Stock");
                            } else {
                                continueLoop = false;
                                do {
                                    try {
                                        System.out.println("  --- Stock --- ");
                                        floristShop.printInfoStock(Decoration.class);
                                        System.out.println();//
                                        int idProduct = Input.readInt("Dime el Id de la decoracion que quieres eliminiar: ");
                                        Product producto = floristShop.findProduct(floristShop.getStock(), idProduct);

                                        if (producto == null) {
                                            System.out.println("Producto no encontrado con el Id: " + idProduct);

                                        } else {

                                            System.out.println("Estas a punto de eliminar la decoracion: " + producto.getName());
                                            String confirm = Input.readString("¿Estas seguro de eliminarlo? Si/NO ");
                                            if (confirm.equalsIgnoreCase("si")) {
                                                floristShop.removeDecoration(producto);
                                                continueLoop = true;
                                            } else if (confirm.equalsIgnoreCase("no")) {
                                                System.out.println("Operacion cancelada");
                                                continueLoop = true;
                                            } else {
                                                System.out.println("Opcion no valida");
                                            }
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Error: Ingresa un valor válido para el Id.");
                                    }
                                } while (!continueLoop);
                            }
                        }
                    }
                    case 9 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.getShopStockWithQuantity();
                        }
                    }
                    case 10 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            System.out.println("Valor Total del stock: " + floristShop.getTotalValue(floristShop.getStock()) + " €");
                        }
                    }
                    case 11 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.createPurchaseTicket(floristShop.getStock());
                        }
                    }
                    case 12 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop.getPurchaseTickets(floristShop.getTickets());
                        }
                    }
                    case 13 -> {
                        toWatchFloristShop(floristShops);
                        shopName = Main.nameFloristShop();
                        floristShop = Main.findFlowerShop(floristShops, shopName);
                        if (floristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            System.out.println("Suma total de ventas: " + floristShop.getSalesProfits(floristShop.getTickets()) + " €");
                        }
                    }
                    case 0 -> {
                        System.out.println("Saliendo de la aplicación.");
                        exit = true;
                    }
                }
            } catch (InputMismatchException e) {
                Input.readString("Error: Ingrese un valor válido: ");
            }
        } while (!exit);
    }

    public static byte menu() {
        byte option = -1;

        do {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1.  Crear floristería.");
            System.out.println("2.  Agregar árbol a floristería.");
            System.out.println("3.  Agregar flor a floristería.");
            System.out.println("4.  Agregar decoración a floristería.");
            System.out.println("5.  Ver stock de floristería.");
            System.out.println("6.  Retirar árbol de floristería.");
            System.out.println("7.  Retirar flor de floristería.");
            System.out.println("8.  Retirar decoración de floristería.");
            System.out.println("9.  Ver stock de floristería con cantidades.");
            System.out.println("10. Ver valor total de la floristería.");
            System.out.println("11. Crear ticket de compra.");
            System.out.println("12. Mostrar compras antiguas.");
            System.out.println("13. Ver ganacias de floristería.");
            System.out.println("0.  Salir de la aplicación.\n");

            try {
                option = Input.readByte("Introduce una opcion: ");
                Input.input();
                if (option < 0 || option > 13) {
                    System.out.println("Opción no válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
            }

        } while (option < 0 || option > 13);

        return option;
    }

    public static void createFloristShop(ArrayList<FloristShop> floristShops) {
        String inputName = Input.readString("Introduce el nombre de la floristería: ");
        if (inputName.equalsIgnoreCase("")) {
            System.out.println("Nombre no válido.");
        } else {
            FloristShop floristShop = Main.findFlowerShop(floristShops, inputName);
            if (floristShop == null) {
                SQL.insertFloristShop(connection, inputName);
                floristShop = SQL.lastFloristShop(connection);
                System.out.println("Creada nueva floristería:");
                System.out.println(floristShop.getName());
                floristShops.add(floristShop);
            } else {
                System.out.println("Ya existe una floristería con el nombre " + floristShop.getName() + ".");
            }
        }
    }

    public static byte selectMaterialMenu() {
        byte option = -1;

        do {
            System.out.println("Selecciona el material de la decoración:");
            System.out.println("1. Madera.");
            System.out.println("2. Plástico.");

            try {
                option = Input.readByte("Escoge una opción: ");
                if (option < 1 || option > 2) {
                    System.out.println("Opción no válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
            }
        } while (option < 1 || option > 2);

        return option;
    }

    public static void toWatchFloristShop(ArrayList<FloristShop> floristShops) {
        System.out.println("--- Floristerias disponibles ---");
        floristShops.forEach(floristShop -> System.out.println(floristShop.getName()));
    }

}
