import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Test1 {

	// !! modificato per verifica funzionamento GIT
	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			// mi metto in ascolto sulla porta 9000
			serverSocket = new ServerSocket(9000);
			System.out.println("Server in ascolto sulla porta 9000");

			Socket socket = serverSocket.accept();

			System.out.println("Un client si è connesso!");

			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			dialogo(pw, br);

			br.close();
			pw.close();
		

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// in prima battuta, sviluppiamo un eco
	public static void dialogo(PrintWriter pw, BufferedReader br) throws IOException {
		pw.println("Scrivi qualcosa, e io lo ripeterò (digita \"esci\" per uscire):");
		pw.flush();

		String stringaLetta = null;
		do {
			stringaLetta = br.readLine();
			System.out.println("Il client ha scritto => " + stringaLetta);

			pw.println("Alle " + new Date().toString() + " hai scritto => " + stringaLetta);
			pw.flush();
		} while (!stringaLetta.contentEquals("esci"));
		// stringaLetta.contentEquals("esci") => è true quando stringaLetta è uguale a esci
		// il ! nega il valore di verità della condizione
	}

}
