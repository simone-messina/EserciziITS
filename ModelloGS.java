package esercizio_04;

public class ModelloGS {
	private int idmodello;
	private String modello;
	public int getIdmodello() {
		return idmodello;
	}
	public void setIdmodello(int idmodello) {
		this.idmodello = idmodello;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	@Override
	public String toString() {
		return "ModelloGS [idmodello=" + idmodello + ", modello=" + modello + "]";
	}
}
