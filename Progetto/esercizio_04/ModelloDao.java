package esercizio_04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelloDao {
	private ModelloGS mappa(ResultSet rs)
	{
		ModelloGS a = new ModelloGS();
		try
		{
			a.setIdmodello(rs.getInt("idmodello"));
			a.setModello(rs.getString("modello"));
			
			return a;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void mappa(PreparedStatement st, ModelloGS a) {

		try 
		{
			st.setString(1, a.getModello());

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Cancella Record
		public boolean cancella(int id) {
			boolean esito = false;
			try {
				Connection conn = DBUtil04.connect();
				PreparedStatement st = conn.prepareStatement("DELETE from tabmodello WHERE idmodello = ?");
				st.setInt(1, id);
				st.executeUpdate();
				st.close();
				esito = true;
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e)  {
				e.printStackTrace();
			}


			return esito;
		}

		// Modifica record
		public boolean modifica(ModelloGS a) {

			boolean esito = false;
			try{ 
				Connection conn = DBUtil04.connect();
				PreparedStatement st = conn.prepareStatement("UPDATE tabmodello SET modello = ? WHERE idmodello=?");
				mappa(st, a);
				st.setInt(2, a.getIdmodello());
				st.executeUpdate();
				st.close();
				conn.close();
				esito= true;
			}catch(SQLException se){ 
				//Handle errors for JDBC 
				se.printStackTrace(); 
			}catch(Exception e){ 
				//Handle errors for Class.forName 
				e.printStackTrace(); 
			}
			return esito;

		}

		// Crea Record
		public boolean crea(ModelloGS a) {
			boolean esito = false;
			try {
				Connection conn = DBUtil04.connect();
				PreparedStatement st = conn.prepareStatement("INSERT INTO tabmodello (modello) VALUES (?)");
				mappa(st, a);
				st.executeUpdate();
				st.close();
				conn.close();
				esito=true;
			}
			catch(SQLException se){ 
				//Handle errors for JDBC 
				se.printStackTrace(); 
			}catch(Exception e){ 
				//Handle errors for Class.forName 
				e.printStackTrace(); 
			}
			return false;
		}

		// Lista i record
		public List<ModelloGS> lista() {
			List<ModelloGS> auto = new ArrayList<ModelloGS>();
			try {
				Connection conn = DBUtil04.connect();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * FROM tabmodello"); 
				while (rs.next()) {
					auto.add(mappa(rs));
				}
				rs.close();
				st.close();
				conn.close();
			}catch(SQLException se){ 
				//Handle errors for JDBC 
				se.printStackTrace(); 
			}catch(Exception e){ 
				//Handle errors for Class.forName 
				e.printStackTrace(); 
			}
			return auto;
		}

		// Per modello

		public List<ModelloGS> permodello(String modello)
		{
			List<ModelloGS> auto = new ArrayList<ModelloGS>();
			ModelloGS a=null;
			try 
			{
				Connection conn = DBUtil04.connect();
				String sql="SELECT * FROM tabmodello WHERE idmodello=?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, modello);
				ResultSet rs =st.executeQuery();

				while(rs.next())
				{
					auto.add(mappa(rs));


				}


				rs.close();
				st.close();
				conn.close();
				return auto;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;

		}

	// per chiave
		public ModelloGS perChiave(int id)          // auto per chiave integer chiamata id
		
		{
			ModelloGS a=null;
			try 
			{
				Connection conn = DBUtil04.connect();
				String sql="SELECT * FROM tabmodello WHERE idmodello=?";     // prendi tutto da automobili dove l' id è = a cosa?
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, id);                                     //metti l' integer id (che verrà inserito nel main) al posto del punto di domanda
				ResultSet rs =st.executeQuery();					// il risultato della ricerca saranno tutti gli id richiesti, cioè 1
				
				while(rs.next())
				{
					a=mappa(rs);
				}
				rs.close();
				st.close();
				conn.close();
				return a;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;

		}
	
	
}
