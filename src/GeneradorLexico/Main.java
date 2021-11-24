package GeneradorLexico;

import java.io.IOException;
import java.io.StringReader;

public class Main {
	public static void main(String[] args) throws IOException {
		String expresion = "2+3 hola";
		Lexer lexico = new Lexer(new StringReader(expresion));
		lexico.yylex();
	}
}
