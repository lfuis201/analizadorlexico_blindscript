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
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
		frame.setBounds(100, 100, 625, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAnalizar = new JButton("Analizar");
		
		JButton btnLimpiar = new JButton("Limpiar");
		
		
		JButton btnLimpiar_1 = new JButton("Limpiar");
		
		JLabel lblAnalizadorLexicoPara = new JLabel("Analizador Lexico para c++");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAnalizar)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnLimpiar))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLimpiar_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(233)
							.addComponent(lblAnalizadorLexicoPara)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblAnalizadorLexicoPara)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAnalizar)
						.addComponent(btnLimpiar)
						.addComponent(btnLimpiar_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_1)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JTextArea textresultado = new JTextArea();
		scrollPane_1.setViewportView(textresultado);
		
		JTextArea textAnalizar = new JTextArea();
		scrollPane.setViewportView(textAnalizar);
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
						//si ya no quedan tokens por analizar se scribira FIN
						if(tokens == null) {
							resultado += "FIN";
							textresultado.setText(resultado);
							return;
						}
						//utilizaremos un switch pra todoss tokens de nuestra clase Tokens estos se mostraran en el textresultado
						switch(tokens) {
							case ERROR:
								resultado +="Simbolo no definido\n";
								break;
							case FuncionMain:
								resultado +="Funcion main .\n";
								break;
							case Keywrod:
								resultado += lexer.lexeme + ": Es un keyword .\n";
								break;
							case Headers1:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case Headers2:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case Headers3:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case Headers4:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case Headers5:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case Headers6:
								resultado += lexer.lexeme + ": Es un " + "header" + ".\n";
								break;
							case Define:
								resultado += lexer.lexeme + ": Instrucion define" + ".\n";
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
								resultado += lexer.lexeme + ": Es un Identificador.\n";
								break;
							case Int:
								resultado += lexer.lexeme + ": Es un Entero.\n";
								break;
							case Funcion:
								resultado += lexer.lexeme + ": Es una Funcion.\n";
								break;
							case Lista:
								resultado += lexer.lexeme + ": Es una Lista.\n";
								break;
							case Lista1:
								resultado += lexer.lexeme + ": Es una Lista.\n";
								break;
							case Arrow:
								resultado += lexer.lexeme + ": Operador arrow.\n";
								break;
							case Ampersand:
								resultado += lexer.lexeme + ": Ampersand.\n";
								break;
							case Dot:
								resultado += lexer.lexeme + ": Operador punto.\n";
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
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAnalizar.setText(null);	}
		});
		
		btnLimpiar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textresultado.setText(null);	}
		});
		
	}
}
