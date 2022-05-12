/**
*A classe Hangman é um jogo da forca segunda as regras na Wikipédia.
*  
* - https://pt.wikipedia.org/wiki/Jogo_da_forca
* - https://en.wikipedia.org/w/index.php?title=Hangman_(game)&oldid=1024011230
* 
*@author v.hofmeister@edu.pucrs.br
*@version 2022-05-09
*/
import java.util.Scanner;

public class Hangman{

	public static String code(String s){ //Codifica uma palavra, substituindo suas letras por -.
		String c = "";
		while(c.length() < s.length()){
			c += "-";
		}
		return c;
	}
	public static String update(String secret, String word, String guess){ //Atualiza a palavra codificada de acordo com a tentativa.
		String w;
		w = "";

		for (int pos = 0; pos < word.length(); pos++){

			if (secret.charAt(pos) == guess.charAt(0)){
				w += guess.charAt(0);
			} else {
				w += word.charAt(pos);
			}
			
		}
		

		return w;
	}
	public static String Hang(int chances){ //Desenha a forca com o boneco de acordo com as chances restantes.

		System.out.printf("+------+\n");
		System.out.printf("|      |\n");
		System.out.printf("|      ");
		if (chances <= 5){
			System.out.printf("O\n");
		} else {
			System.out.printf("\n");
		}
		System.out.printf("|     ");
		if (chances == 4){
			System.out.printf(" |\n");
		} else if (chances == 3){
			System.out.printf("/|\n");
		} else if (chances <= 2){
			System.out.printf("/|\\\n");
		} else {
			System.out.printf("\n");
		}
		System.out.printf("|     ");
		if (chances == 1){
			System.out.printf("/\n");
		} else if (chances == 0){
			System.out.printf("/ \\\n");
		} else {
			System.out.printf("\n");
		}
		System.out.println("|___________");

		return "";
	}
	public static String Start(){
		System.out.printf("\n\nInicio do jogo!\n\n\n\n");
		return "";
	}
	public static String AskSecret(Scanner sc){
		String secret;
		System.out.printf("Informe a palavra secreta\n");
		secret = sc.next();
		secret = secret.toLowerCase();
		return secret;
	}
	public static String AskGuess(Scanner sc){
		String guess;
		System.out.println("Informe a letra: ");
		guess = sc.next();
		guess = guess.toLowerCase();
		return guess;
	}
	public static String Display(String word, String misses, int chances){  //Mostra a palavra codificada, as tentativas erradas e o desenho da forca.
		Hang(chances);
	      System.out.printf("\n\n%s\n\n", word);
		System.out.printf("Erros: %s\n\n", misses);
		return "";
	}
	public static String CheckRepeatedGuess(Scanner sc, String word, String misses, String guess){
		String newGuess = guess;

		while (word.contains(newGuess) || misses.contains(newGuess)){
			System.out.printf("\nEssa tentativa já foi realizada\n");
			newGuess = AskGuess(sc);
		}

		return newGuess;
	}

	public static void main(String args[]){
		Scanner sc;
		String secret, word, guess, misses;
		int chances;

		sc = new Scanner(System.in);

		//System.out.printf("\f");
		secret = AskSecret(sc);
		word = code(secret);
		misses = "";
		chances = 6;

		Start();

		while(!word.equals(secret) && chances > 0){

			Display(word, misses, chances);
		
			guess = AskGuess(sc);

			guess = CheckRepeatedGuess(sc, word, misses, guess);
			

	       	if (secret.contains(guess)) {

		            System.out.printf("\n\n\nAcertou!\n\n\n\n");

		            word = update(secret, word, guess);

	      	} else {

		            System.out.print("\n\n\nErrou!\n\n\n\n");
		            
		            misses = misses + guess + " ";

		            chances--;
	       	}

		}
		sc.close();

		if (word.equals(secret)){
			System.out.printf("\nVOCE VENCEU!\n\n");
		} else if (chances == 0){
			Hang(chances);
			System.out.printf("\nACABARAM AS TENTATIVAS!\n\n");
		}

		System.out.printf("\nFIM DE JOGO!\n\n");
	}

}