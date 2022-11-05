package trabalho;

import java.io.*;
import java.util.Scanner;


public class LojaMain {

	static Scanner scan = new Scanner(System.in);
	static StringBuffer memoriap = new StringBuffer();
	static StringBuffer memoria = new StringBuffer();

	public static void main(String[] args) {

		char opcao;
		do {
			opcao = menu();
			switch(opcao){
			case '1' :
				inserirVend();
				break;
			case '2':
				inserirP();
				break;
			case '3':
				alterarP();
				break;
			case '4':
				excluirP();
				break;
			case'5':
				pesquisarV();
				break;
			case'6':
				pesquisarP();
				break;
			case'7':
				mostrarTudo();
			case 's':
				System.out.println("Saiu do programa!");
				break;
			default:
				System.out.println("Opcao incorreta \nTente novamente!");
			}
		}while (opcao !='8');
		System.exit(0);
	}

	static char menu(){
		System.out.println( "\n\nEscolha uma das opções a seguir:  \n\n"+
				"1. Cadastrar dados do vendedor \n"+
				"2. Cadastrar dados do produto \n" +
				"3. Alterar dados do vendedor \n"+
				"4. Excluir produtos \n"+
				"5. Pesquisar vendedor \n"+
				"6. Pesquisar produto \n"+
				"7. Mostrar vendedor e respectivos produtos \n"+
				"8. Sair do programa");
		return scan.next().charAt(0);
	}

	//este método server para atualizar a variável memoria com os dados que estão no HD

	static void iniciarArquivo(){
		try{
			BufferedReader arquivoEntrada;
			arquivoEntrada = new BufferedReader (new FileReader("Vendedor.txt"));
			String linha = "";
			memoria.delete(0,memoria.length());//APAGA TUDO QUE ESTA NA VARIAVEL MEMÓRIA
			do {
				linha = arquivoEntrada.readLine();
				if (linha != null) {
					memoria.append (linha + "\n");
				}
			}while (linha != null);
			arquivoEntrada.close();
		} catch (FileNotFoundException erro){
			System.out.println("\nArquivo não encontrado");
		} catch (Exception e){
			System.out.println("\nErro na leitura");
		}
	}

	//este método grava  os dados na memória segundária(HD, pendrive)
	public static void gravarDados(){
		try{
			BufferedWriter arquivoSaida;
			arquivoSaida = new BufferedWriter(new FileWriter("Vendedor.txt"));
			arquivoSaida.write(memoria.toString());
			arquivoSaida.flush();
			arquivoSaida.close();
		} catch (Exception e){
			System.out.println("\nErro de gravação");
		}
	}

	static void iniciaArquivo(){
		try{
			BufferedReader arquivoEntrada;
			arquivoEntrada = new BufferedReader (new FileReader("Produto.txt"));
			String linha = "";
			memoria.delete(0,memoria.length());//APAGA TUDO QUE ESTA NA VARIAVEL MEMÓRIA
			do {
				linha = arquivoEntrada.readLine();
				if (linha != null) {
					memoria.append (linha + "\n");
				}
			}while (linha != null);
			arquivoEntrada.close();
		} catch (FileNotFoundException erro){
			System.out.println("\nArquivo não encontrado");
		} catch (Exception e){
			System.out.println("\nErro de gravação");
		}
	}

	public static void gravaDados(){
		try{
			BufferedWriter arquivoSaida;
			arquivoSaida = new BufferedWriter(new FileWriter("Produto.txt"));
			arquivoSaida.write(memoria.toString());
			arquivoSaida.flush();
			arquivoSaida.close();
		} catch (Exception e){
			System.out.println("\nErro de gravação");
		}
	}

	static void inserirVend() {
		int codVend;
		String nome, tel;
		try {

			System.out.println("Digite o código do vendedor: ");
			codVend = scan.nextInt();
			System.out.println("Digite o nome do vendedor");
			nome = scan.next();
			System.out.println("Digite o número de contato do vendedor: ");
			tel = scan.next();
			//VARIAVEL REG DO TIPO PRODUTOS RECEBE OS VALORES DIGITADOS PELO USUARIO:
			Vendedor reg = new Vendedor(codVend, nome, tel);
			BufferedWriter arquivoSaida;
			arquivoSaida = new BufferedWriter(new FileWriter("Vendedor.txt",true));
			arquivoSaida.write(reg.toString());
			arquivoSaida.flush();
			arquivoSaida.close();


		}catch (Exception e){
			System.out.println("\nErro de gravação");
		}
	}


	static void inserirP() {
		int codProduto;
		String nomeP, qntProduto;

		try{
			System.out.println("Digite o codigo do produto:");
			codProduto = scan.nextInt();
			System.out.println("Digite o nome do produto: ");
			nomeP = scan.next();
			System.out.println("Digite a quantidade do produto: ");
			qntProduto = scan.next();
			Produto pro = new Produto(codProduto, nomeP, qntProduto);
			BufferedWriter arquivoSaida;
			arquivoSaida = new BufferedWriter(new FileWriter("Produto.txt", true));
			arquivoSaida.write(pro.toString());
			arquivoSaida.flush();
			arquivoSaida.close();

		}catch (Exception e){
			System.out.println("\nErro de gravacao");

		}
	}
	static void alterarP() {
		String codigo, nome, telefone;
		int inicio, fim, ultimo, primeiro;
		boolean achou=false;
		int procura;
		iniciarArquivo(); //atualizar a variavel memoria para iniciar a pesquisa
		if (memoria.length() != 0) {   // n�o est� vazia
			System.out.println("\nDigite o codigo para alteraçãoo: ");
			procura= scan.nextInt();
			inicio = 0;   //inicio come�a na posi��o 0 
			while ((inicio != memoria.length()) && (!achou)) {
				ultimo = memoria.indexOf ("\t", inicio);
				codigo = memoria.substring(inicio, ultimo);
				primeiro = ultimo + 1;
				ultimo = memoria.indexOf ("\t", primeiro); 
				nome = memoria.substring(primeiro, ultimo);		
				primeiro = ultimo + 1;
				fim = memoria.indexOf ("\n", primeiro);
				telefone = memoria.substring(primeiro, fim);
				Produto reg = new Produto (Integer.parseInt(codigo),nome,telefone);
				if (procura == reg.getCodProduto()){
					System.out.println("\nC�digo: "+reg.getCodProduto()+
							"  nome: " +reg.getNomeP()+
							"  telefone: "+reg.getQntProduto());
					System.out.println("Entre com novo telefone:");
					reg.setQntProduto(scan.next());
					memoria.replace(inicio, fim+1,reg.toString()); //alterar dados na memoria
					gravarDados(); //grava altera��o no HD
					achou = true;
				}
				inicio = fim + 1;  // continua procurando o c�digo da pessoa
			}
			if (achou){
				System.out.println("\naltera��o realizada com sucesso");
			}else{
				System.out.println("\nc�digo n�o encontrado");
			}
		}else{
			System.out.println("\narquivo vazio");
		}
	}

	static void excluirP() {
		String nomeP, codProduto, qntProduto;
		int inicio, fim, ultimo, primeiro, procura;
		boolean achou=false;
		char resp;
		iniciarArquivo(); //atualizar a variavel memoria para iniciar a pesquisa
		if (memoria.length() != 0) {   // n�o esta vazia
			System.out.println("\nDigite o codigo para exclusao: ");
			procura= scan.nextInt();
			inicio = 0;   //inicio come�a na posi��o 0 da vari�vel memoria
			while ((inicio != memoria.length()) && (!achou)) {
				ultimo = memoria.indexOf ("\t", inicio);
				codProduto = memoria.substring(inicio, ultimo);
				primeiro = ultimo + 1;
				ultimo = memoria.indexOf ("\t", primeiro);
				nomeP = memoria.substring(primeiro, ultimo);
				primeiro = ultimo + 1;
				fim = memoria.indexOf ("\n", primeiro);
				qntProduto = memoria.substring(primeiro, fim);
				Produto reg = new Produto (Integer.parseInt(nomeP),codProduto, qntProduto);
				if (procura == reg.getCodProduto()){
					System.out.println("Deseja excluir?"+"\n"+"Digite S ou N"+"\n\n"+
							"  Codigo: "+reg.getCodProduto()+
							"  Nome: " +reg.getNomeP()+
							"  Quantidade: "+reg.getQntProduto());
					resp = Character.toUpperCase(scan.next().charAt(0));
					if (resp == 'S'){
						memoria.delete (inicio, fim + 1);
						System.out.println("Registro excluido.");
						gravarDados();
					}else{
						System.out.println("Exclusao cancelada.");
					}
					achou = true;
				}
				inicio = fim + 1;  // continua procurando o codigo da pessoa
			}
			if (!achou){
				System.out.println("\nCodigo nao encontrado");
			}
		}else{
			System.out.println("\nArquivo Vazio");

		}
	}

	static void pesquisarV() {
		String codigo, nome, telefone;
		int inicio, fim, ultimo, primeiro;
		boolean achou=false;
		int procura;
		iniciarArquivo(); //atualizar a variavel memoria para iniciar a pesquisa
		if (memoria.length() != 0) {   // n�o est� vazia
			System.out.println("\nDigite o codigo para pesquisar:");
			procura= scan.nextInt();
			inicio = 0;    
			while ((inicio != memoria.length()) && (!achou)) {
				ultimo = memoria.indexOf ("\t", inicio);
				codigo = memoria.substring(inicio, ultimo);
				primeiro = ultimo + 1;
				ultimo = memoria.indexOf ("\t", primeiro);
				nome = memoria.substring(primeiro, ultimo);
				primeiro = ultimo + 1;
				fim = memoria.indexOf ("\n", primeiro);
				telefone = memoria.substring(primeiro, fim);
				Vendedor reg = new Vendedor (Integer.parseInt(codigo),nome,telefone);
				if (procura == reg.getCodVend()){
					System.out.println("\nC�digo: "+reg.getCodVend()+
							"  nome: " +reg.getNome()+
							"  telefone: "+reg.getNumeroC());
					achou = true;
				}
				inicio = fim + 1;  // continua procurando o c�digo da pessoa
			}
			if (!achou){
				System.out.println("\nCodigo nao encontrado");
			}
		}else{
			System.out.println("\nArquivo vazio");
		}
	}



	static void pesquisarP() {
		String codigo, nome, telefone;
		int inicio, fim, ultimo, primeiro;
		boolean achou=false;
		int procura;
		iniciarArquivo(); //atualizar a variavel memoria para iniciar a pesquisa
		if (memoria.length() != 0) {   // n�o est� vazia
			System.out.println("\nDigite o codigo para pesquisar:");
			procura= scan.nextInt();
			inicio = 0;    
			while ((inicio != memoria.length()) && (!achou)) {
				ultimo = memoria.indexOf ("\t", inicio);
				codigo = memoria.substring(inicio, ultimo);
				primeiro = ultimo + 1;
				ultimo = memoria.indexOf ("\t", primeiro);
				nome = memoria.substring(primeiro, ultimo);
				primeiro = ultimo + 1;
				fim = memoria.indexOf ("\n", primeiro);
				telefone = memoria.substring(primeiro, fim);
				Produto reg = new Produto (Integer.parseInt(codigo),nome,telefone);
				if (procura == reg.getCodProduto()){
					System.out.println("\nC�digo: "+reg.getCodProduto()+
							"  nome: " +reg.getNomeP()+
							"  telefone: "+reg.getQntProduto());
					achou = true;
				}
				inicio = fim + 1;  // continua procurando o c�digo da pessoa
			}
			if (!achou){
				System.out.println("\nCodigo nao encontrado");
			}
		}else{
			System.out.println("\nArquivo vazio");
		}

	}

	static void mostrarTudo() {
		String codigo, nome, telefone;
		int inicio, fim, ultimo, primeiro;
		boolean achou=false;
		int procura;
		iniciarArquivo(); //atualizar a variavel memoria para iniciar a pesquisa
		if (memoria.length() != 0) {   // n�o est� vazia
			System.out.println("\nDigite o codigo para pesquisar:");
			procura= scan.nextInt();
			inicio = 0;    
			while ((inicio != memoria.length()) && (!achou)) {
				ultimo = memoria.indexOf ("\t", inicio);
				codigo = memoria.substring(inicio, ultimo);
				primeiro = ultimo + 1;
				ultimo = memoria.indexOf ("\t", primeiro);
				nome = memoria.substring(primeiro, ultimo);
				primeiro = ultimo + 1;
				fim = memoria.indexOf ("\n", primeiro);
				telefone = memoria.substring(primeiro, fim);
				Vendedor reg = new Vendedor (Integer.parseInt(codigo),nome,telefone);
				if (procura == reg.getCodVend()){
					System.out.println("\nCódigo: "+reg.getCodVend()+
							"  nome: " +reg.getNome()+
							"  telefone: "+reg.getNumeroC());
					achou = true;
				}
				inicio = fim + 1;  // continua procurando o codigo da pessoa
			}
			if (!achou){
				System.out.println("\ncódigo não encontrado");
			}
		}else{
			System.out.println("\nArquivo vazio");
		}
	}

}