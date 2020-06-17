package esercizio_04;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class AutoDao {
	private AutoGS mappa(ResultSet rs)
	{
		AutoGS a = new AutoGS();
		try
		{
			a.setId(rs.getInt("id"));
			a.setMarca(rs.getString("marca"));
			a.setModello(rs.getString("modello"));
			a.setCv(rs.getInt("cv"));
			a.setNumero_posti(rs.getInt("numero_posti"));
			a.setData_immatricolazione(rs.getString("data_immatricolazione"));
			return a;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private void mappa(PreparedStatement st, AutoGS a) {

		try 
		{
			st.setString(1, a.getMarca());
			st.setString(2, a.getModello());
			st.setInt(3, a.getCv());
			st.setString(4, a.getData_immatricolazione());
			st.setInt(5, a.getNumero_posti());
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
			PreparedStatement st = conn.prepareStatement("DELETE from automobili WHERE id = ?");
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
	public boolean modifica(AutoGS a) {

		boolean esito = false;
		try{ 
			Connection conn = DBUtil04.connect();
			PreparedStatement st = conn.prepareStatement("UPDATE automobili SET marca = ?, modello =?, cv=?, data_immatricolazione = ?, numero_posti = ? WHERE id=?");
			mappa(st, a);
			st.setInt(6, a.getId());
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
	public boolean crea(AutoGS a) {
		boolean esito = false;
		try {
			Connection conn = DBUtil04.connect();
			PreparedStatement st = conn.prepareStatement("INSERT INTO automobili (marca, modello, cv, data_immatricolazione, numero_posti) VALUES (?, ?, ?, ?, ?)");
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
	public List<AutoGS> lista() {
		List<AutoGS> auto = new ArrayList<AutoGS>();
		try {
			Connection conn = DBUtil04.connect();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * FROM automobili"); 
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

	public List<AutoGS> perMarca(String marca)
	{
		List<AutoGS> auto = new ArrayList<AutoGS>();
		AutoGS a=null;
		try 
		{
			Connection conn = DBUtil04.connect();
			String sql="SELECT * FROM automobili WHERE marca=?";
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
	public AutoGS perChiave(int id)          // auto per chiave integer chiamata id
	
	{
		AutoGS a=null;
		try 
		{
			Connection conn = DBUtil04.connect();
			String sql="SELECT * FROM automobili WHERE id=?";     // prendi tutto da automobili dove l' id è = a cosa?
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
