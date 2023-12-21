import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu {


    public static void getMainMenu(ArrayList<String> floristShops) {
        boolean exit = false;
        FloristShop floristShop = new FloristShop();
        String shopName = "";

        do {
            try {
                switch (menu()) {
                    case 1:
                        createFloristShop(floristShops);
                        break;

                    case 2:
                        shopName = Main.nameFloristShop();
                        String nameFloristShop = Main.findFlowerShopString(floristShops, shopName);
                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            File file = new File("Data/" + shopName + ".txt");
                            try {
                                if (file.length() == 0L) {
                                    System.out.println("File is empty");
                                    FloristShop floristShop22 = new FloristShop(shopName);
                                    floristShop22.addTree(floristShop22.getStock());
                                    saveFloristShop(floristShop22);
                                } else {
                                    floristShop = loadFloristShop(shopName);
                                    floristShop.addTree(floristShop.getStock());
                                    saveFloristShop(floristShop);
                                }


                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;

                    case 3:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);
                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            File file = new File("Data/" + shopName + ".txt");
                            try {
                                if (file.length() == 0L) {
                                    System.out.println("File is empty");
                                    FloristShop floristShop22 = new FloristShop(shopName);
                                    floristShop22.addFlower(floristShop22.getStock());
                                    saveFloristShop(floristShop22);
                                } else {
                                    floristShop = loadFloristShop(shopName);
                                    floristShop.addFlower(floristShop.getStock());
                                    saveFloristShop(floristShop);
                                }


                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case 4:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);
                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            File file = new File("Data/" + shopName + ".txt");
                            try {
                                if (file.length() == 0L) {
                                    System.out.println("File is empty");
                                    FloristShop floristShop22 = new FloristShop(shopName);
                                    floristShop22.addDecoration(floristShop22.getStock());
                                    saveFloristShop(floristShop22);
                                } else {
                                    floristShop = loadFloristShop(shopName);
                                    floristShop.addDecoration(floristShop.getStock());
                                    saveFloristShop(floristShop);
                                }


                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;

                    case 5:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            File file = new File("Data/" + shopName + ".txt");
                            try {
                                if (file.length() == 0L) {
                                    System.out.println("File is empty");

                                } else {
                                    floristShop = loadFloristShop(shopName);
                                    floristShop.getShopStock(floristShop.getStock());
                                }


                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;


                    case 6:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            long treeCount = floristShop.getStock().stream()
                                    .filter(p -> p instanceof Tree)
                                    .count();
                            if (treeCount == 0) {
                                System.out.println("En estos momentos no hay arboles en el Stock");

                            } else {
                                System.out.println("  --- Stock --- ");
                                floristShop.printInfoStock(Tree.class);
                                System.out.println();//

                                int idProduct = Input.readInt("Dime el Id del arbol que quieres eliminiar: ");
                                Product producto = floristShop.findProduct(floristShop.getStock(), idProduct);

                                if (producto == null) {
                                    System.out.println("Producto no encontrado con el Id: " + idProduct);
                                } else {
                                    boolean continueLoop = false;

                                    do {
                                        System.out.println("Estas a punto de eliminar el arbol: " + producto.getName());
                                        String confirm = Input.readString("¿Estas seguro de eliminarlo? Si/NO ");
                                        if (confirm.equalsIgnoreCase("si")) {
                                            floristShop.removeTree(producto);
                                            saveFloristShop(floristShop);
                                            continueLoop = true;
                                        } else if (confirm.equalsIgnoreCase("no")) {
                                            System.out.println("Operacion cancelada");
                                            continueLoop = true;
                                        } else {
                                            System.out.println("Opcion no valida");
                                        }

                                    } while (continueLoop == false);
                                }
                            }
                        }
                        saveFloristShop(floristShop);
                        break;

                    case 7:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            long flowerCount = floristShop.getStock().stream()
                                    .filter(p -> p instanceof Flower)
                                    .count();
                            if (flowerCount == 0) {
                                System.out.println("En estos momentos no hay Flores en el Stock");

                            } else {
                                System.out.println("  --- Stock --- ");
                                floristShop.printInfoStock(Flower.class);
                                System.out.println();//
                                int idProduct = Input.readInt("Dime el Id de la Flor que quieres eliminiar: ");
                                Product producto = floristShop.findProduct(floristShop.getStock(), idProduct);

                                if (producto == null) {
                                    System.out.println("Producto no encontrado con el Id: " + idProduct);
                                } else {
                                    boolean continueLoop = false;

                                    do {
                                        System.out.println("Estas a punto de eliminar la flor: " + producto.getName());
                                        String confirm = Input.readString("¿Estas seguro de eliminarlo? Si/NO ");
                                        if (confirm.equalsIgnoreCase("si")) {
                                            floristShop.removeFlower(producto);
                                            saveFloristShop(floristShop);
                                            continueLoop = true;
                                        } else if (confirm.equalsIgnoreCase("no")) {
                                            System.out.println("Operacion cancelada");
                                            continueLoop = true;
                                        } else {
                                            System.out.println("Opcion no valida");

                                        }
                                    } while (continueLoop == false);
                                }
                            }
                        }
                        saveFloristShop(floristShop);
                        break;

                    case 8:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            long decorationCount = floristShop.getStock().stream()
                                    .filter(p -> p instanceof Decoration)
                                    .count();
                            if (decorationCount == 0) {
                                System.out.println("En estos momentos no hay decoracion en el Stock");

                            } else {
                                System.out.println("  --- Stock --- ");
                                floristShop.printInfoStock(Decoration.class);
                                System.out.println();//
                                int idProduct = Input.readInt("Dime el Id de la decoracion que quieres eliminiar: ");
                                Product producto = floristShop.findProduct(floristShop.getStock(), idProduct);

                                if (producto == null) {
                                    System.out.println("Producto no encontrado con el Id: " + idProduct);

                                } else {
                                    boolean continueLoop = false;

                                    do {
                                        System.out.println("Estas a punto de eliminar la decoracion: " + producto.getName());
                                        String confirm = Input.readString("¿Estas seguro de eliminarlo? Si/NO ");
                                        if (confirm.equalsIgnoreCase("si")) {
                                            floristShop.removeDecoration(producto);
                                            saveFloristShop(floristShop);
                                            continueLoop = true;
                                        } else if (confirm.equalsIgnoreCase("no")) {
                                            System.out.println("Operacion cancelada");
                                            continueLoop = true;
                                        } else {
                                            System.out.println("Opcion no valida");

                                        }
                                    } while (continueLoop == false);
                                }
                            }
                        }
                        saveFloristShop(floristShop);
                        break;

                    case 9:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            floristShop.getShopStockWithQuantity();
                        }
                        break;
                    case 10:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            System.out.println("Valor Total del stock: " + floristShop.getTotalValue(floristShop.getStock()) + " €");
                        }
                        break;
                    case 11:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            floristShop.createPurchaseTicket(floristShop.getStock());
                            saveFloristShop(floristShop);
                        }
                        break;
                    case 12:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            if (floristShop.getTickets().isEmpty()) {
                                System.out.println("No se ha hecho ninguna venta.");
                            } else {
                                floristShop.getPurchaseTickets(floristShop.getTickets());
                            }
                        }
                        break;
                    case 13:
                        shopName = Main.nameFloristShop();
                        nameFloristShop = Main.findFlowerShopString(floristShops, shopName);

                        if (nameFloristShop == null) {
                            System.out.println("Floristería no encontrada.");
                        } else {
                            floristShop = loadFloristShop(shopName);
                            System.out.println("Suma total de ventas: " + floristShop.getSalesProfits(floristShop.getTickets()) + " €");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación.");
                        exit = true;
                        break;
                }
            } catch (InputMismatchException e) {
                Input.readString("Error: Ingrese un valor válido: ");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } while (!exit);
    }

    public static byte menu() {
        byte option = -1;
        boolean numCorrect = false;

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
                if (option < 0 || option > 13) {
                    System.out.println("Opción no válida");
                }
                numCorrect = true;
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
            }

        } while (!numCorrect);
        return option;
    }

    public static void createFloristShop(ArrayList<String> floristShops) {
        String inputName = Input.readString("Introduce el nombre de la floristería: ").toLowerCase();
        String floristShopString = Main.findFlowerShopString(floristShops, inputName);

        if (inputName.equalsIgnoreCase("")) {
            System.out.println("Nombre no válido.");
        }else{
            if (floristShopString == null) {
                FloristShop floristShop = new FloristShop(inputName);
                try {
                    createFile(inputName);
                    Menu.saveFloristShop(floristShop);
                    System.out.println("Creada nueva floristería:");
                    System.out.println(floristShop.getName());
                    floristShops.add(inputName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Ya existe una floristería con el nombre " + floristShopString + ".");
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


    // Método para cargar una floristería desde un archivo
    public static FloristShop loadFloristShop(String shopName) throws FileNotFoundException {
        String filepath = "Data/" + shopName + ".txt";
        FileManager fileManager = new FileManager(filepath, false);


        return (FloristShop) fileManager.desSerializeObject(filepath);


    }


    // Método para guardar una floristería en un archivo
    public static void saveFloristShop(FloristShop floristShop) {
        String filepath = "Data/" + floristShop.getName() + ".txt";
        FileManager fileManager = new FileManager(filepath, false);
        fileManager.serializeObject(floristShop, filepath);
    }


    public static void createFile(String shopName) throws IOException {
        String filepath = "Data/" + shopName + ".txt";
        File file = new File(filepath);
        BufferedWriter bw;
        // creacion del fichero
        bw = new BufferedWriter(new FileWriter(file));
        bw.close();

    }

}

