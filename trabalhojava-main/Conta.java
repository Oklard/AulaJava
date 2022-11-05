package trabalho;

public  class Conta {

	private String nome, cpf;
	private int numero;
	private double valorConta;

	public Conta(String nome, String cpf, int numero, double valorConta) {
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;
		this.valorConta = valorConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getValorConta() {
		return valorConta;
	}

	public void setValorConta(double valorConta) {
		this.valorConta = valorConta;
	}

	@Override
	public String toString() {
		return "Conta [nome=" + nome + ", cpf=" + cpf + ", numero=" + numero + ", valorConta=" + valorConta + "]";
	}

	public double saldo (){
		return valorConta;

	}

	public boolean transferencia ( Conta outraConta){
		;

	}

}
