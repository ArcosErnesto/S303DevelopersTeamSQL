import java.util.InputMismatchException;
import java.util.Scanner;

public class Input{

	public static Scanner input() {
		return new Scanner(System.in);
	}
	
	public static byte readByte(String msj) throws InputMismatchException {
		Object num;
		byte numCorrecto;
		System.out.print(msj);
		
		num = input().nextByte();
		numCorrecto = (byte) num;
		
		return numCorrecto;
	}

	public static int readInt(String msj) throws InputMismatchException{
		Object obj;
		int num;
		
		System.out.print(msj);
		obj = input().nextInt();
		num = (int) obj;
		
		return num;
	}

	public static double readDouble(String msj) throws InputMismatchException{
		Object obj;
		double num;
		
		System.out.print(msj);
		obj = input().nextDouble();
		num = (double) obj;

		return num;
	}

	public static String readString(String msj) throws InputMismatchException {
		System.out.print(msj);

		return input().nextLine();
	}

	public static void cleanBuffer() {
		input().nextLine();
	}

}