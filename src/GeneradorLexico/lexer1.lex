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
KEYWORDS = ( bul|breic|const|du|els|if|ritorn|while|not|and|or|escribir )
PRIMITIVEDATATYPES = (int|char|bool|float|double|string)
BOL = (tru|fols)
SEPARADORES = ("<<"|">>"|"::")
espacio=[ ,,\t,\r,\n,	]+

%{
    public String lexeme;
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"{" { lexeme=yytext(); return LlavedeApertura; }
"}" { lexeme=yytext(); return LlavedeCerradura; }
"(" { lexeme=yytext(); return ParentesisdeApertura; }
")" { lexeme=yytext(); return ParentesisdeCerradura; }
"[" { lexeme=yytext(); return CorchetesdeApertura; }
"]" { lexeme=yytext(); return CorchetesdeCerradura; }
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