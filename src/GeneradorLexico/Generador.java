package GeneradorLexico;

import java.io.File;

public class Generador {
	public static void main(String[] args) {
		String path = "/home/luisfelipe/javaprojects/AnalizadorLexico/src/GeneradorLexico/";
		String archivo = path +"lexer.lex";
	
		File file = new File(archivo);
		jflex.Main.generate(file);
		System.out.print(false);
		
	}
}
