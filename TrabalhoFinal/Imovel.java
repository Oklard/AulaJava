package Faculdade.aluguel;

public class Imovel {
	private String tipo, cep, area;
	private int codImovel;
	
	public Imovel(int codImovel,String tipo, String cep, String area) {
		super();
		this.codImovel = codImovel;	
		this.tipo = tipo;
		this.cep = cep;
		this.area = area;
	}
	public int getCodImovel() {
		return codImovel;
	}
	public void setCodImovel(int codImovel) {
		this.codImovel = codImovel;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	

	@Override
	public String toString() {
		return  this.tipo +"\t" + this.cep +"\t" + this.area +"\t" + this.codImovel +"\n";
	}
}
