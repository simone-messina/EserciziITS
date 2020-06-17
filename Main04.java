package esercizio_04;


import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main04 {


	DBUtil04 db = new DBUtil04(); 

	public static void main(String[] args) { 

		Connection conn = null; 
		Statement stmt = null; 

		try{ 

			AutoDao dao = new AutoDao();

			String syso = "Inserisci numero: 1. Elenco | 2. Cerca marca | 3. Aggiungi auto | 4. Modifica | 5. Rimuovi | 6. Esporta su file | 7. Exit | 9. CRUD Marca e Modello ";
			String el = "1";
			String cm = "2";
			String aa = "3";
			String up = "4";
			String rm = "5";
			String esf = "6";
			String ex = "7";
			String z = "9";

			Scanner in = new Scanner(System.in);
			String ins = ""; 

			for (; !ins.contentEquals(ex);) 
			{
				System.out.println("\n" + syso);
				ins = in.nextLine();

				// visualizza i record

				if (ins.contentEquals(el))
				{
					for (AutoGS a : dao.lista()) {
						System.out.println(a);
					}
					System.out.println("\n ___________________________________________________________________ \n");


				}

				// Cerca marca


				if (ins.contentEquals(cm)) {
					System.out.println("Cerca marca");
					String insmarca = in.nextLine();
					for (AutoGS a : dao.perMarca(insmarca)) {
						System.out.println(a);
					}

					System.out.println("\n ______________________ \n");

				}


				//aggiungi auto

				if (ins.contentEquals(aa)) {
					System.out.println("Inserisci marca: ");
					String insmarca = in.nextLine();
					System.out.println("Inserisci modello: ");
					String insmodello = in.nextLine();
					System.out.println("Inserisci data: ");
					String insdi = in.nextLine();
					System.out.println("Inserisci cv: ");
					int inscv = in.nextInt();
					System.out.println("Inserisci numero posti: ");
					int insnp = in.nextInt();

					AutoGS nuovo = new AutoGS();
					nuovo.setMarca(insmarca);
					nuovo.setModello(insmodello);
					nuovo.setCv(inscv);
					nuovo.setData_immatricolazione(insdi);
					nuovo.setNumero_posti(insnp);
					dao.crea(nuovo);

					for (AutoGS a : dao.lista()) {
						System.out.println(a);
					}

					System.out.println("\n ____________________________________________________________________ \n");



				}

				// Modifica il record
				if(ins.contentEquals(up))
				{
					
					System.out.println("Inserisci marca: ");
					String marca = in.nextLine(); // non usato
					System.out.println("Inserisci modello: ");
					String modello = in.nextLine();
					System.out.println("Inserisci data immatricolazione: ");
					String di = in.nextLine();
					System.out.println("Inserisci cavalli: ");
					int cv = in.nextInt();
					System.out.println("Inserisci numero posti: ");
					int np = in.nextInt();
					
					System.out.println("Inserisci id macchina da modificare: ");
					int idmod = in.nextInt();
					AutoGS automod = dao.perChiave(idmod);
					System.out.println("Modifica quest' auto: ");
					System.out.println(automod);
					automod.setMarca(marca);
					automod.setModello(modello);
					automod.setCv(cv);
					automod.setNumero_posti(np);
					automod.setData_immatricolazione(di);

					System.out.println("\n Modifica in corso... \n");
					dao.modifica(automod);

					for (AutoGS a : dao.lista()) {
						System.out.println(a);
					}
					System.out.println("\n _______________________________________________________________________ \n");
				}

				if (ins.contentEquals(esf))
				{

					File file = new File("C:/windows/temp/garage"); 
					file.createNewFile(); 
					FileWriter writer = new FileWriter(file);
					for (AutoGS a : dao.lista()) {
						writer.write(a + "\n");


						writer.write("\n");
					}
					writer.flush(); 
					writer.close();
				}
				if (ins.contentEquals(rm))
				{
					System.out.println("Scegli una macchina da rimuovere:");
					System.out.println("Id:	");
					int key = in.nextInt();
					dao.cancella(key);
					System.out.println("\n _______________________________________________________ \n");
				}
				if (ins.contentEquals(z)) {

					String ma = "1";
					String mo = "2";
					String ex1 = "3" ;
					String scelta = ""; 
					for (; !scelta.contentEquals(ex1);) 
					{
						System.out.println("\n 1. CRUD marca | 2. CRUD modello | 3. Exit");
						scelta = in.nextLine();

						if (scelta.contentEquals(ma)) {
							marca();
						}
						if (scelta.contentEquals(mo)) {
							modello();
						}
					}
				}
			}

			//			}
			//STEP 6: Clean-up environment 
			//			rs.close(); 
			stmt.close(); 
			conn.close(); 
			in.close();
		}catch(SQLException se){ 
			//Handle errors for JDBC 
			se.printStackTrace(); 
		}catch(Exception e){ 
			//Handle errors for Class.forName 
			e.printStackTrace(); 
		}finally{ 
			//finally block used to close resources 
			try{ 
				if(stmt!=null)
					stmt.close(); 
			}catch(SQLException se2){ 
			}// nothing we can do 
			try{ 
				if(conn!=null) 
					conn.close(); 
			}catch(SQLException se){ 
				se.printStackTrace(); 
			}//end finally try 

		}//end try 

		System.out.println("Goodbye!"); 
	}//end main 


	public static void marca() {

		String el = "1";
		String aa = "2";
		String up = "3";
		String rm = "4";
		String ex1 = "5";
		Scanner in = new Scanner(System.in);
		String ins = "";
		MarcaDao dao = new MarcaDao();
		for (; !ins.contentEquals(ex1);) 
		{
			System.out.println("CRUD marca: 1. Elenco | 2. Crea | 3. Modifica | 4. Elimina | 5. Exit");
			ins = in.nextLine();

			if (ins.contentEquals(el)) {                 //1


				for (MarcaGS a : dao.lista()) {
					System.out.println(a);
				}
				System.out.println("\n _______________________________________________________________________ \n");
			}



			if (ins.contentEquals(aa)) {                 //2
				System.out.println("Inserisci marca: ");
				

				MarcaGS nuovo = new MarcaGS();
				nuovo.setMarca(in.nextLine());
				dao.crea(nuovo);

				for (MarcaGS a : dao.lista()) {
					System.out.println(a);
				}
				System.out.println("\n _______________________________________________________________________ \n");
			}
			if (ins.contentEquals(up)) {				//3

				System.out.println("Inserisci nuova marca: ");
				String marca = in.nextLine();
				
				
				for (MarcaGS a : dao.lista()) {
					System.out.println(a);
				}
				System.out.println("Al posto di quale id?: ");
				int idmod = in.nextInt();
				MarcaGS automod = dao.perChiave(idmod);
				System.out.println("Modifica quest' auto: ");
				System.out.println(automod);
				automod.setMarca(marca);

				System.out.println("\n Modifica in corso... \n");
				dao.modifica(automod);

				for (MarcaGS a : dao.lista()) {
					System.out.println(a);
				}
				System.out.println("\n _______________________________________________________________________ \n");

			}
			if (ins.contentEquals(rm)) {					//4
				System.out.println("Scegli una marca da rimuovere:");
				System.out.println("Id:	");
				int key = in.nextInt();
				dao.cancella(key);
				System.out.println("\n _______________________________________________________ \n");

			}

		}

	}








	public static void modello() {


		String el = "1";
		String aa = "2";
		String up = "3";
		String rm = "4";
		String ex1 = "5";
		Scanner in = new Scanner(System.in);
		String ins = "";
		ModelloDao dao = new ModelloDao();

		for (; !ins.contentEquals(ex1);) 
		{
			System.out.println("CRUD modello: 1. Elenco | 2. Crea | 3. Modifica | 4. Elimina | 5. Exit");
			ins = in.nextLine();

			if (ins.contentEquals(el)) {					//1


				for (ModelloGS a : dao.lista()) {
					System.out.println(a);
				}System.out.println("\n _______________________________________________________________________ \n");
			}
			if (ins.contentEquals(aa)) {					//2
				System.out.println("Inserisci modello: ");
				String insmodello = in.nextLine();

				ModelloGS nuovo = new ModelloGS();
				nuovo.setModello(insmodello);
				dao.crea(nuovo);

				for (ModelloGS a : dao.lista()) {
					System.out.println(a);
				}System.out.println("\n _______________________________________________________________________ \n");
			}

			if (ins.contentEquals(up)) {					//3
				System.out.println("Inserisci nuovo modello: ");
				String modello = in.nextLine();
				
				
				for (ModelloGS a : dao.lista()) {
					System.out.println(a);
				}
				System.out.println("Al posto di quale id?: ");
				int idmod = in.nextInt();
				ModelloGS automod = dao.perChiave(idmod);
				System.out.println("Modifica questo modello: ");
				System.out.println(automod);
			

				automod.setModello(modello);

				System.out.println("\n Modifica in corso... \n");
				dao.modifica(automod);

				for (ModelloGS a : dao.lista()) {
					System.out.println(a);
				}
				System.out.println("\n _______________________________________________________________________ \n");
			}
			if (ins.contentEquals(rm)) {					//4
				System.out.println("Scegli un modello da rimuovere:");
				System.out.println("Id:	");
				int key = in.nextInt();
				dao.cancella(key);
				System.out.println("\n _______________________________________________________ \n");
			}
		}
	}
}