package trabalho;

public class Produto {

	//ATRIBUTOS

	private String nomeP;
	private int codProduto;
	private String qntProduto;

	//GET AND SET
	public String getNomeP() {
		return nomeP;
	}


	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}


	public int getCodProduto() {
		return codProduto;
	}


	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}


	public String getQntProduto() {
		return qntProduto;
	}


	public void setQntProduto(String qntProduto) {
		this.qntProduto = qntProduto;
	}

	//CONSTRUTOR
	public Produto(int codigo, String nome, String quantidade) {
		super();
		this.nomeP = nome;
		this.codProduto = codigo;
		this.qntProduto = quantidade;
	}

	@Override //toString
	public String toString() {
		return this.nomeP + "\t" + this.codProduto + "\t" + this.qntProduto + "\n";
	}

}