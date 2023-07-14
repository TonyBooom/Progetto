package Model;

public class CategoriaBean {

	int codcategoria;
	String nome;
	ImmagineBean immagine;
	
	
	public int getCodcategoria() {
		return codcategoria;
	}
	public void setCodcategoria(int codcategoria) {
		this.codcategoria = codcategoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public CategoriaBean() {
		super();
	}
	
	public ImmagineBean getImmagine() {
		return immagine;
	}
	public void setImmagine(ImmagineBean immagine) {
		this.immagine = immagine;
	}
	
	
	
	
	
}
