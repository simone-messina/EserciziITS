package esercizio_04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MarcaDao {
	private MarcaGS mappa(ResultSet rs)
	{
		MarcaGS a = new MarcaGS();
		try
		{
			a.setIdmarca(rs.getInt("idmarca"));
			a.setMarca(rs.getString("marca"));
			
			return a;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void mappa(PreparedStatement st, MarcaGS a) {

		try 
		{
			st.setString(1, a.getMarca());

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
				PreparedStatement st = conn.prepareStatement("DELETE from tabmarca WHERE idmarca = ?");
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
		public boolean modifica(MarcaGS a) {

			boolean esito = false;
			try{ 
				Connection conn = DBUtil04.connect();
				PreparedStatement st = conn.prepareStatement("UPDATE tabmarca SET marca = ? WHERE idmarca=?");
				mappa(st, a);
				st.setInt(2, a.getIdmarca());
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
		public boolean crea(MarcaGS a) {
			boolean esito = false;
			try {
				Connection conn = DBUtil04.connect();
				PreparedStatement st = conn.prepareStatement("INSERT INTO tabmarca (marca) VALUES (?)" );
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
		public List<MarcaGS> lista() {
			List<MarcaGS> auto = new ArrayList<MarcaGS>();
			try {
				Connection conn = DBUtil04.connect();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * FROM tabmarca"); 
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

		// Per marca

		public List<MarcaGS> perMarca(String marca)
		{
			List<MarcaGS> auto = new ArrayList<MarcaGS>();
			MarcaGS a=null;
			try 
			{
				Connection conn = DBUtil04.connect();
				String sql="SELECT * FROM tabmarca WHERE idmarca=?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, marca);
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
		public MarcaGS perChiave(int id)          // auto per chiave integer chiamata id
		
		{
			MarcaGS a=null;
			try 
			{
				Connection conn = DBUtil04.connect();
				String sql="SELECT * FROM tabmarca WHERE idmarca=?";     // prendi tutto da automobili dove l' id è = a cosa?
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
