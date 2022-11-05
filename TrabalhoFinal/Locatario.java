package Faculdade.aluguel;

public class Locatario {
	private int codigo;
	private String nome, cpf, telefone;

	public Locatario(int codigo,String nome, String cpf, String telefone) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getcpf() {
		return cpf;
	}

	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String gettelefone() {
		return telefone;
	}

	public void settelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	 public String toString(){
        return this.codigo+ "\t" + this.nome+ "\t" +this.cpf+ "\t" +this.telefone+"\n";

        }
	}


