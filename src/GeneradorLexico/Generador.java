package GeneradorLexico;

import java.io.File;

public class Generador {
	public static void main(String[] args) {
		//primero creamos nuestro acrhivo .lex donde iran nuestras expresions regulares
		
		//Para generar la clase lexer haremos uso de nuestro archivo .lex
		String path = "/home/luisfelipe/javaprojects/AnalizadorLexico/src/GeneradorLexico/";
		String archivo = path +"lexer.lex";
		File file = new File(archivo);
		
		//creamos la clase lexer con ayuda de la libreria Jflex
		jflex.Main.generate(file);
	}
}
