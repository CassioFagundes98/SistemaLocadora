package provaA3;

public class FilmeDAO {

	public int anoFilme;
	private int idFilme;
	private String nomeFilme;
	private String categoria;
	private int faixaEtaria;
	private int disponivel;
	private float preço;

	public int getAnoFilme() {
		return anoFilme;
	}

	public void setAnoFilme(int anoFilme) {
		this.anoFilme = anoFilme;
	}

	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(int faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public int isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(int disponivel) {
		this.disponivel = disponivel;
	}

	public float getPreço() {
		return preço;
	}

	public void setPreço(float preço) {
		this.preço = preço;
	}

	public String toString() {
		return this.nomeFilme;

	}

}
