package Faculdade.aluguel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.DomainLoadStoreParameter;
import java.util.Scanner;


public class ImovelMain {

	static Scanner scan = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();
	static StringBuffer memoriaLocatario = new StringBuffer();
	static StringBuffer memoriaImovel = new StringBuffer();



	public static void main(String[] args) {
		char opcao;

		do {
			opcao = menu();

			switch (opcao) {

				case '1':

				  cadastrarLocatario();

					break;
				case '2':

					cadastrarImovel();

						break;
				case '3':

					alterarImovel();

						break;

				case '4':
				
				  consultaTodos();
				    break;
					
				case '5':
                  consultaImovel();
				break;

				case '6':
				excluirImovel();

				break;
				case '0':

					System.out.println("Voce saiu do Programa");

					break;

				default:

					System.out.println("Opção Invalida");
			}

		} while (opcao != 0);

	}

	static char menu() {
		System.out.println("Menu : \n" +
		"1- Cadastro de locatario\n" +
		"2- Cadastrar um imóvel\n" +
		"3- Alterar dado de um Imovel\n" +
		"4- Consultar todos os Imoveis\n" +
		"5- Consultar um Imovel especifico\n" +
		"6- Excluir dado de um Imovel\n" +
		"0- Sair do Programa");
	return scan.next().charAt(0);
	}

	static void iniciarArquivo() {
		try {
			BufferedReader arquivoEntrada;
			arquivoEntrada = new BufferedReader(new FileReader("arquivoDados.txt"));
			String linha = "";
			memoria.delete(0, memoria.length());
			do {
				linha = arquivoEntrada.readLine();
				if (linha != null) {
					memoria.append(linha + "\n");
				}
			} while (linha != null);
			arquivoEntrada.close();
		} catch (FileNotFoundException erro) {
			System.out.println("\nArquivo não encontrado");
		} catch (Exception e) {
			System.out.println("\nErro de Leutura");
		}

	}

	public static void gravarDadosLocatario(){
		try{
			BufferedWriter arquivoSaida;
			arquivoSaida = new BufferedWriter(new FileWriter ("Locatario.txt"));
			arquivoSaida.write(memoriaLocatario.toString());
			arquivoSaida.flush();
			arquivoSaida.close();
		} catch (Exception e){
			System.out.println("\nErro de gravacao!"+e);
		}
	}
	public static void gravarDadosImovel(){
		try{
			BufferedWriter arquivoSaida;
			arquivoSaida = new BufferedWriter(new FileWriter ("Imovel.txt"));
			arquivoSaida.write(memoriaImovel.toString());
			arquivoSaida.flush();
			arquivoSaida.close();
		} catch (Exception e){
			System.out.println("\nErro de gravacao!"+e);
		}
	}

	public static void cadastrarLocatario() {
		int codigo;
		String nome,cpf,telefone;
		try{
			System.out.println("Digite o Código");
			codigo = scan.nextInt();
			System.out.println("Digite o nome");
			nome = scan.next();
			System.out.println("Digite o CPF");
			cpf = scan.next();
			System.out.println("Digite o Telefone");
			telefone = scan.next();
			Locatario reg = new Locatario(codigo,nome, cpf, telefone);
			memoriaLocatario.append(reg.toString());  //inserir uma nova linha no final
			System.out.println(memoriaLocatario);
			gravarDadosLocatario();     //Grava no bloco de notas
			
		}catch (Exception e){
			System.out.println("\nErro de gravacao");
		}
	}
	public static void cadastrarImovel() {
		String tipo,cep,area;
		int codImovel;
		try{
			System.out.println("Digite o tipo do Imovel (Casa/Prédio)");
			tipo = scan.next();
			System.out.println("Digite o CEP");
			cep = scan.next();
			System.out.println("Digite a area");
			area = scan.next();
			System.out.println("Digite um codigo para o imóvel");
			codImovel = scan.nextInt();
			Imovel reg = new Imovel(codImovel, cep, area, tipo);
			memoriaImovel.append(reg.toString());  //inserir uma nova linha no final
			System.out.println(memoriaImovel);
			gravarDadosImovel();     //Grava no bloco de notas
			
		}catch (Exception e){
			System.out.println("\nErro de gravacao"+ e);
		}
	}
	static void alterarImovel(){
		String codImovel,tipo,cep,area;
		int inicio,fim,ultimo,primeiro;
		boolean achou=false;
		int procura;					//usar o alterar dados para pesquisar 
		iniciarArquivo();
		if(memoriaImovel.length()!=0){
			System.out.println("\nDigite o codigo para busca:");
			procura= scan.nextInt();
			inicio = 0;   //inicio come�a na posi��o 0 
			while ((inicio != memoriaImovel.length()) && (!achou)) {
				ultimo = memoriaImovel.indexOf ("\t", inicio);
				tipo = memoriaImovel.substring(inicio, ultimo);
				primeiro = ultimo + 1;
				ultimo = memoriaImovel.indexOf ("\t", primeiro); 
				cep = memoriaImovel.substring(primeiro, ultimo);		
				primeiro = ultimo + 1;
				ultimo=memoriaImovel.indexOf("\t",primeiro);
				area=memoriaImovel.substring(primeiro, ultimo);
				primeiro=ultimo + 1;
				fim=memoriaImovel.indexOf("\n",primeiro);
				codImovel=memoriaImovel.substring(primeiro, fim);
				Imovel reg = new Imovel(Integer.parseInt(codImovel),cep,area,tipo);
				if (procura == reg.getCodImovel()){
					System.out.println("\ntipo: "+reg.getTipo()+
							"  CEP: " +reg.getCep()+
							"  Area: "+reg.getArea()+
							"  Código Imóvel: "+reg.getCodImovel());
					System.out.println("Informe um novo tipo: ");
					reg.setTipo(scan.next());
					System.out.println("Informe uma nova area: ");
					reg.setArea(scan.next());
					System.out.println("Informe um novo CEP: ");
					reg.setCep(scan.next());
					memoriaImovel.replace(inicio,fim+1,reg.toString());
					gravarDadosImovel();
					achou = true;
				}
				inicio= fim + 1;
			}
			if(achou){
				System.out.println("\nAlteração realizada com sucesso ");
			}else{
				System.out.println("\nCodigo nao encontrado");
			}
		}else{
			System.out.println("\nArquivo vazio.");
		}
	}

	//apresentar todos os Dados de Imovel
	static void consultaTodos() {
		String codImovel, tipo, area, cep;
		String imovelDados= "";
		int inicio, fim, ultimo, primeiro;
		iniciarArquivo();
		
		inicio = 0;
		try{
			if(memoriaImovel.length() !=0){
				while(inicio != memoriaImovel.length()){
					ultimo = memoriaImovel.indexOf("\t",inicio);
					codImovel= memoriaImovel.substring(inicio, ultimo);
					primeiro = ultimo + 1;

					ultimo = memoriaImovel.indexOf("\t",primeiro);
					tipo = memoriaImovel.substring(primeiro, ultimo);
					primeiro = ultimo + 1;

					ultimo = memoriaImovel.indexOf("\t",primeiro);
					area = memoriaImovel.substring(primeiro, ultimo);
					primeiro = ultimo + 1;

					fim=memoriaImovel.indexOf("\n",primeiro);
					cep=memoriaImovel.substring(primeiro, fim);

					Imovel reg = new Imovel(Integer.parseInt(codImovel),tipo,cep,area);
					imovelDados = "\ntipo: "+reg.getTipo()+
					"  CEP: " +reg.getCep()+
					"  Area: "+reg.getArea()+
					"  Código Imóvel: "+reg.getCodImovel();
				}
				System.out.println("\nDados Imovel : \n"+ imovelDados);
			}else{
				System.out.println("\nO arquivo está vazio");
			}

		}catch(Exception l){
			System.out.println("\nNão foi possivel fazer a leitura.");

		}
	}

	static void consultaImovel(){
		String codImovel, tipo, area, cep;
		int inicio,fim,ultimo,primeiro;
		boolean achou=false;
		int procura;					
		iniciarArquivo();
		try{
			if(memoriaImovel.length()!=0){
				System.out.println("\nDigite o codigo do paciente para busca:");
				procura= scan.nextInt();
				inicio = 0;   //inicio come�a na posi��o 0 
				while ((inicio != memoriaImovel.length()) && (!achou)) {
					ultimo = memoriaImovel.indexOf ("\t", inicio);
					codImovel = memoriaImovel.substring(inicio, ultimo);
					primeiro = ultimo + 1;

					ultimo = memoriaImovel.indexOf ("\t", primeiro); 
					tipo = memoriaImovel.substring(primeiro, ultimo);		
					primeiro = ultimo + 1;

					ultimo=memoriaImovel.indexOf("\t",primeiro);
					area =memoriaImovel.substring(primeiro, ultimo);
					primeiro=ultimo + 1;

					fim=memoriaImovel.indexOf("\n",primeiro);
					cep=memoriaImovel.substring(primeiro, fim);

					Imovel reg = new Imovel(Integer.parseInt(codImovel),tipo,area,cep);
					if (procura == reg.getCodImovel()){
						System.out.println("\ntipo: "+reg.getTipo()+
						"  CEP: " +reg.getCep()+
						"  Area: "+reg.getArea()+
						"  Código Imóvel: "+reg.getCodImovel());
						achou = true;
					}
					inicio = fim + 1;  
				}
				if (!achou){
					System.out.println("\nImovel não foi encontrado");
				}
			}else{
				System.out.println("\nO arquivo está vazio");

			}

		}
		catch(Exception l){
			System.out.println("\nNão foi possivel fazer a leitura.");
		}
	}

	static void excluirImovel() {
		String codImovel,tipo, area, cep;
		int inicio,fim,ultimo,primeiro, procura;
		boolean achou = false;
		char opcao;
		iniciarArquivo();
		if(memoriaImovel.length()!=0){
			System.out.println("Digite o codigo do Imovel :");
			procura=scan.nextInt();
			inicio=0;
			while((inicio!=memoriaImovel.length()) && (!achou)){
				ultimo = memoriaImovel.indexOf ("\t", inicio);
				codImovel = memoriaImovel.substring(inicio, ultimo);
				primeiro = ultimo + 1;

				ultimo = memoriaImovel.indexOf ("\t", primeiro); 
				tipo = memoriaImovel.substring(primeiro, ultimo);		
				primeiro = ultimo + 1;

				ultimo=memoriaImovel.indexOf("\t",primeiro);
				area =memoriaImovel.substring(primeiro, ultimo);
				primeiro=ultimo + 1;

				fim=memoriaImovel.indexOf("\n",primeiro);
				cep=memoriaImovel.substring(primeiro, fim);

				Imovel reg = new Imovel(Integer.parseInt(codImovel),tipo,area,cep);
				if (procura == reg.getCodImovel()){
					System.out.println("\n O arquivo será excluido: " +
					" Tipo: "+reg.getTipo()+
					"  CEP: " +reg.getCep()+
					"  Area: "+reg.getArea()+
					"  Código Imóvel: "+reg.getCodImovel()+
					"\n Deseja completar essa operação (s/n)?");
				
					opcao=Character.toUpperCase(scan.next().charAt(0));	
				
				if(opcao =='s'){

					memoriaImovel.delete(inicio, fim + 1);
					System.out.println("Imovel excluido com sucesso !");
					gravarDadosImovel();

				}else{

					System.out.println("Operação cancelada");
				}
				achou=true;
			}
			inicio=fim+1;
		  }
		  if(!achou){
			System.out.println("\nImovel não foi encontrado");
		  }
		  else{
			System.out.println("\nArquivo está vazio");
		  }
		}
	}
}
