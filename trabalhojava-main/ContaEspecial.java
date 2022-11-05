package trabalho;

public class ContaEspecial extends Conta{

	private double limite;


	public ContaEspecial(String nome, String cpf, int numero, double valorConta, double limite) {
		super(nome, cpf, numero, valorConta);
		this.limite = limite;
	}


	public double getLimite() {
		return limite;
	}


	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double saldo (){
		return (this.getValorConta() + this.getLimite());


	}






}
