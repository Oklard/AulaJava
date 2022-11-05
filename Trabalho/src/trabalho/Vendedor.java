package trabalho;

public class Vendedor {

	//ATRIBUTOS

	private int codVend;
	private String nome;
	private String tel;
	
	public int getCodVend() {
		return codVend;
	}

	//GET AND SET
	public void setCodVend(int codVend) {
		this.codVend = codVend;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroC() {
		return tel;
	}
	public void setNumeroC(String numeroC) {
		this.tel = numeroC;
	}

	//CONSTRUTOR
	public Vendedor(int codVend, String nome, String numeroC) {
		super();
		this.codVend = codVend;
		this.nome = nome;
		this.tel = numeroC;
	}

	//toString
	@Override
	public String toString() {
		return  this.codVend + "\t" + this.nome + "\t" + this.tel + "\n";
	}
}