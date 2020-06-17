package esercizio_04;

public class AutoGS {
	private int id;
	private String marca;
	private String modello;
	private int cv;
	private int numero_posti;
	private String data_immatricolazione;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public int getCv() {
		return cv;
	}
	public void setCv(int cv) {
		this.cv = cv;
	}
	public int getNumero_posti() {
		return numero_posti;
	}
	public void setNumero_posti(int numero_posti) {
		this.numero_posti = numero_posti;
	}
	public String getData_immatricolazione() {
		return data_immatricolazione;
	}
	public void setData_immatricolazione(String di) {
		this.data_immatricolazione = di;
	}
	@Override
	public String toString() {
		return "Auto [id=" + id + ", marca=" + marca + ", modello=" + modello + ", cv=" + cv + ", numero_posti="
				+ numero_posti + ", data_immatricolazione=" + data_immatricolazione + "]";
	}
	
}