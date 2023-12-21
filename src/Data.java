import java.util.ArrayList;


public class Data {

	private ArrayList<Product> allProducts = new ArrayList<Product>();
	private ArrayList<FloristShop> floristerias = new ArrayList<FloristShop>();

	public void loadShop() {
		FloristShop fh1 = new FloristShop("Rosas del valle");
		FloristShop fh2 = new FloristShop("Rosas del bosque");
		floristerias.add(fh1);
		floristerias.add(fh2);


		fh1.getStock().add(new Tree("pino", 5, 9));
		fh1.getStock().add(new Flower("rosa", 8, "red"));
		fh1.getStock().add(new Flower("periwinkle", 6, "violet"));
		fh1.getStock().add(new Tree("palmera", 12, 23));
		fh1.getStock().add(new Decoration("mueble", 40, "madera"));

		fh2.getStock().add(new Tree("christmas tree", 78, 50));
		fh2.getStock().add(new Flower("tulipan", 8, "yellow"));
		//fh2.getStock().add(new Flower("girasol", 6, "orange"));
		fh2.getStock().add(new Tree("almendro", 12, 23));
		fh2.getStock().add(new Decoration("escritorio", 27, "madera"));

		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();

		ticket1.setProducts(allProducts);
		ticket2.setProducts(allProducts);
		ticket1.calculateFinalPrice();
		ticket2.calculateFinalPrice();

		fh1.getTickets().add(ticket1);
		fh2.getTickets().add(ticket2);
	}

	public void loadProducts() {
		
		allProducts.add(new Tree("pino", 5, 9));
		allProducts.add(new Flower("rosa", 8, "red"));
		allProducts.add(new Flower("periwinkle", 6, "violet"));
		allProducts.add(new Tree("palmera", 12, 23));
		allProducts.add(new Decoration("mueble", 40, "madera"));
		
		allProducts.add(new Tree("christmas tree", 78, 50));
		allProducts.add(new Flower("tulipan", 8, "yellow"));
		allProducts.add(new Flower("girasol", 6, "orange"));
		allProducts.add(new Tree("almendro", 12, 23));
		allProducts.add(new Decoration("escritorio", 27, "madera"));
	}

	public ArrayList<FloristShop> getFloristerias() {
		return floristerias;
	}
}