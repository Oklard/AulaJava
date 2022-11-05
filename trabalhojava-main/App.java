package trabalho;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ArrayList<Integer> lista = new ArrayList<>();
		
		JOptionPane.showMessageDialog(null, "BANCO");

		char opcao;
		do {
			opcao = menu();
			switch(opcao){
			case '1' :
				cadConta(lista);
				break;
			case '2':
				saldo(lista);
				break;
			case '3':
				saque(lista);
				break;
			case '4':
				saldo(lista);
				break;
			case'5':
				transferencia(lista);
			case '6':
				sair(lista);
				System.out.println("Saiu do programa!");
				break;
			default:
				System.out.println("Opcao incorreta \nTente novamente!");
			}
		}while (opcao !='6');
		System.exit(0);
	
	
	}
	
	static char menu(){
		System.out.println( "\n\nEscolha uma das opcoes a seguir:  \n\n"+
				"1. Cadastrar conta \n"+
				"2. Saldo \n" +
				"3. Saque \n"+
				"4. Deposito \n"+
				"5. Transferência \n" +
				"6. Sair");
		return scan.next().charAt(0);

}
