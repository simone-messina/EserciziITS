package esercizio_04;

public class MarcaGS {
	private int idmarca;
	public int getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}
	private String marca;
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@Override
	public String toString() {
		return "MarcaGS [idmarca=" + idmarca + ", marca=" + marca + "]";
	}
	
}
