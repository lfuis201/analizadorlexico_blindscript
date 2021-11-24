package GeneradorLexico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 655, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textAnalizar = new JTextArea();
		
		JTextArea textresultado = new JTextArea();
		textresultado.setEditable(false);
		
		JButton btnAnalizar = new JButton("Analizar");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(textAnalizar, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAnalizar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textresultado, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textAnalizar, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
							.addComponent(textresultado, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAnalizar))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Primero vamos a convertir la entrada del JTextField a un documento de texto, posteriormente analizarlo con el lexer que creamos 
				File archivo = new File("archivo.txt");
				PrintWriter escribir;
				try {
					escribir = new PrintWriter(archivo);
					escribir.print(textAnalizar.getText());
					escribir.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Empezaremos a utilizar el analizador lexico
				
				try {//No se sabe si el archivo esta o no en la computadora
					Reader lector = new BufferedReader(new FileReader("archivo.txt"));
					Lexer lexer = new Lexer(lector);
					String resultado ="";//Sirve como resultado de toda la cadena que vamos a analizar
					while(true) {
						Tokens tokens = lexer.yylex();
						if(tokens == null) {
							resultado += "FIN";
							textresultado.setText(resultado);
							return;
						}
						switch(tokens) {
							case ERROR:
								resultado +="Simbolo no definido\n";
								break;
							case FuncionMain:
								resultado +="Funcion main .\n";
								break;
							case Keywrod:
								resultado += lexer.lexeme + ": Es un " + tokens + ".\n";
								break;
							case Headers1:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case LlavedeApertura:
								resultado += lexer.lexeme + ": Llave de Apertura.\n";
								break;
							case LlavedeCerradura:
								resultado += lexer.lexeme + ": Llave de Cerradura.\n";
								break;
							case ParentesisdeApertura:
								resultado += lexer.lexeme + ": Parentesis de Apertura.\n";
								break;
							case ParentesisdeCerradura:
								resultado += lexer.lexeme + ": Parentesis de Cerradura.\n";
								break;
							case CorchetesdeApertura:
								resultado += lexer.lexeme + ": Corchetes de Apertura.\n";
								break;
							case CorchetesdeCerradura:
								resultado += lexer.lexeme + ": Corchetes de Cerradura.\n";
								break;
							case Headers2:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case VarInt:
								resultado += lexer.lexeme + ": Es una VariableEntera.\n";
								break;
							case VarInt1:
								resultado += lexer.lexeme + ": Es una VariableEntera.\n";
								break;
							case VarFloat:
								resultado += lexer.lexeme + ": Es una VariableFloat.\n";
								break;
							case VarFloat1:
								resultado += lexer.lexeme + ": Es una VariableFloat.\n";
								break;
							case VarDouble:
								resultado += lexer.lexeme + ": Es una VariableDouble.\n";
								break;
							case VarDouble1:
								resultado += lexer.lexeme + ": Es una VariableDouble.\n";
								break;
							case VarChar:
								resultado += lexer.lexeme + ": Es una VariableChar.\n";
								break;
							case VarChar1:
								resultado += lexer.lexeme + ": Es una VariableChar.\n";
								break;
							case VarString:
								resultado += lexer.lexeme + ": Es una VariableString.\n";
								break;
							case VarString1:
								resultado += lexer.lexeme + ": Es una VariableString.\n";
								break;
							case VarBool:
								resultado += lexer.lexeme + ": Es una VariableBool.\n";
								break;
							case VarBool1:
								resultado += lexer.lexeme + ": Es una VariableBool.\n";
								break;
							case Cout:
								resultado += lexer.lexeme + ": Instruccion cout.\n";
								break;
							case Cin:
								resultado += lexer.lexeme + ": Instruccion cout.\n";
								break;
							case Comillas:
								resultado += lexer.lexeme + ": Comillas.\n";
								break;
							case PuntoyComa:
								resultado += lexer.lexeme + ": punto y coma.\n";
								break;
							case Separador:
								resultado += lexer.lexeme + ": Es un Separador.\n";
								break;
							case Var:
								resultado += lexer.lexeme + ": Es una Variable.\n";
								break;
							case OpAr:
								resultado += lexer.lexeme + ": Es un Operador Aritmetico.\n";
								break;
							case OpCom:
								resultado += lexer.lexeme + ": Es un Operador de Comparacion.\n";
								break;
							case OpLog:
								resultado += lexer.lexeme + ": Es un Operador Logico.\n";
								break;
							case Identificador:
								resultado += lexer.lexeme + ": Es un Operador Identificador.\n";
								break;
							case Int:
								resultado += lexer.lexeme + ": Es un Entero.\n";
								break;
							default:
								resultado += "Token: " + tokens + ".\n";
								break;
						}
						
						
						
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
