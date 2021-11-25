package GeneradorLexico;
import static GeneradorLexico.Tokens.*;
%%
%class Lexer
%type Tokens
BLANCO = [\n| |\t]
ID = [_|a-z|A-Z][a-z|A-Z|0-9|_]*
ENTERO = 0|[1-9][0-9]*
PUNTO_FLOTANTE = [0-9][0-9]*"."[0-9]+
OPERADORES_ARITMETICOS = ("+"|"-"|"*"|"/"|"%"|"+="|"-="|"*="|"/="|"%="|"++"|"--"|"=")
OPERADORES_COMPARACION = ("<"|">"|"<="|">="|"=="|"!="|"<=>")
OPERADORES_LOGICOS = ("!"|"&&"|"||")
HEADERS = [_|a-z|A-Z][a-z|A-Z|0-9|_]*
KEYWORDS = ( switch|if|then|else|new|auto|operator|template|break|enum|prvate|this|case|extern|protected|throw|catch|
public|try|for|register|typedef|class|friend|return|union|const|goto|short|unsigned|continue|signed|virtual|
default|inline|sizedof|void|delete|static|volatile|do|struct|while|using|namespace|std|MAX|MIN )
PRIMITIVEDATATYPES = (int|char|bool|float|double|void|wchart_t|long)
BOL = (true|false)
SEPARADORES = ("<<"|">>"|"::")
espacio=[ ,,\t,\r,\n,	]+

%{
    public String lexeme;
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"#include" + {BLANCO} + "<" + {HEADERS} + ">" { lexeme=yytext(); return Headers1; }
"#include" + {BLANCO} + "\"" + {HEADERS} + ".h" + "\"" { lexeme=yytext(); return Headers2; }
"#include" + {BLANCO} + "<" + {HEADERS} + ".h" + ">" { lexeme=yytext(); return Headers3; }
"#include"+ "<" + {HEADERS} + ">" { lexeme=yytext(); return Headers4; }
"#include"+ "\"" + {HEADERS} + ".h" + "\"" { lexeme=yytext(); return Headers5; }
"#include"+ "<" + {HEADERS} + ".h" + ">" { lexeme=yytext(); return Headers6; }
"#define" { lexeme=yytext(); return Define; }
"{" { lexeme=yytext(); return LlavedeApertura; }
"}" { lexeme=yytext(); return LlavedeCerradura; }
"(" { lexeme=yytext(); return ParentesisdeApertura; }
")" { lexeme=yytext(); return ParentesisdeCerradura; }
"[" { lexeme=yytext(); return CorchetesdeApertura; }
"]" { lexeme=yytext(); return CorchetesdeCerradura; }
"int main()" { return FuncionMain; }
"int" + {BLANCO} + {ID} + {BLANCO} + "=" + {BLANCO} + {ENTERO} + ";" { lexeme=yytext(); return VarInt; }
"int" + {BLANCO} + {ID} + "=" +{ENTERO} + ";" { lexeme=yytext(); return VarInt1; }
"float" + {BLANCO} + {ID} + "=" + {PUNTO_FLOTANTE} + ";" { lexeme=yytext(); return VarFloat; }
"float" + {BLANCO} + {ID} +{BLANCO} + "=" +{BLANCO} + {PUNTO_FLOTANTE} + ";" { lexeme=yytext(); return VarFloat1; }
"double" + {BLANCO} + {ID} + "=" + {PUNTO_FLOTANTE} + ";" { lexeme=yytext(); return VarDouble; }
"double" + {BLANCO} + {ID} + {BLANCO} + "=" +{BLANCO} + {PUNTO_FLOTANTE} + ";" { lexeme=yytext(); return VarDouble1; }
"char" + {BLANCO} + {ID} + "=" + "\'" + {ID} + "\"" + ";" { lexeme=yytext(); return VarChar; }
"char" + {BLANCO} + {ID} + {BLANCO} + "=" + {BLANCO} + "\'" + {ID} + "\"" + ";" { lexeme=yytext(); return VarChar1; }
"String" + {BLANCO} + {ID} +{BLANCO} + "=" + {BLANCO} +"\"" + {ID} + "\"" + ";" { lexeme=yytext(); return VarString; }
"String" + {BLANCO} + {ID} + "=" + {BLANCO} +"\"" + {ID} + "\"" + ";" { lexeme=yytext(); return VarString1; }
"bool" + {BLANCO} + {ID} + "=" + {BOL} + ";" { lexeme=yytext(); return VarBool; }
"bool" + {BLANCO} + {ID} ++ {BLANCO} + "="+ {BLANCO}  + {BOL} + ";" { lexeme=yytext(); return VarBool1; }
"cout" { lexeme=yytext(); return Cout; }
"cin" { lexeme=yytext(); return Cin; }
"\"" { lexeme=yytext(); return Comillas; }
";" { lexeme=yytext(); return PuntoyComa; }
{SEPARADORES}  {lexeme=yytext(); return Separador;}
{PRIMITIVEDATATYPES} + {BLANCO} +{ID} + ";" { lexeme=yytext(); return Var; }
{OPERADORES_ARITMETICOS} { lexeme=yytext(); return OpAr; }
{OPERADORES_COMPARACION} { lexeme=yytext(); return OpCom; }
{OPERADORES_LOGICOS} { lexeme=yytext(); return OpLog; }
{KEYWORDS} { lexeme=yytext(); return Keywrod; }
{ID} { lexeme=yytext(); return Identificador; }
{ENTERO} { lexeme=yytext(); return Int; }
{PRIMITIVEDATATYPES}+{BLANCO}+{ID}+"["+{ENTERO}+"]" +";" { lexeme=yytext(); return Lista; }
{PRIMITIVEDATATYPES}+{BLANCO}+{ID} + "[" + {ID} + "]" +";" { lexeme=yytext(); return Lista1; }
{PRIMITIVEDATATYPES} + {BLANCO} +{ID}+"(" + ")"  { lexeme=yytext(); return Funcion; }

"->" { lexeme=yytext(); return Arrow; }
"&" { lexeme=yytext(); return Ampersand; }
"." { lexeme=yytext(); return Dot; }
 . {return ERROR;}