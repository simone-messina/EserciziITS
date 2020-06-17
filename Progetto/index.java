import java.util.Scanner;

public class index {
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		System.out.print("Inserisci Nome e Cognome: ");
		String nome = in.nextLine();
		System.out.print("Inserisci indirizzo: ");
		String indirizzo = in.nextLine();
		System.out.print("Nome del tuo cane: ");
		String dog = in.nextLine();
		System.out.println("Nome: " + nome);
		System.out.println("Indirizzo: " + indirizzo);
		System.out.println("Cane: " + dog);
		in.close();
	}
}
