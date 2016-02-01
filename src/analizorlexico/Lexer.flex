package analizorlexico;
import static analizorlexico.Token.*;
%%
%class Lexer
%type Token
L=[a-z]
A=[A-Z]
D=[0-9]
white=[ \n]
%{
    public String lexeme;
%}
%%
<YYINITIAL> void {return Palabra_Reservada;}
<YYINITIAL> main {return Palabra_Reservada;}
<YYINITIAL> static {return Palabra_Reservada;}
<YYINITIAL> public {return Palabra_Reservada;}
<YYINITIAL> private {return Palabra_Reservada;}
<YYINITIAL> string {return Palabra_Reservada;}
<YYINITIAL> int {return Palabra_Reservada;}

{white} {/*Ignore*/}
[/][/] ({L}|{D}|{A})* {lexeme=yytext(); return comentario;}
"=" {return igual;}
"+" {return suma;}
[-+]?[.]{D}+ {lexeme=yytext(); return num_decimal;}
({L}|{A}|_)({L}|{D}|{A}|_)* {lexeme=yytext(); return Identificador;}
[-+]?{D}+ {lexeme=yytext(); return Entero;}
[/][*]({L}|{D}|{A})*[*][/] {lexeme=yytext(); return comentario;}
"*" {return multiplicacion;}
"-" {return resta;}
"/" {return division;}
"{" {return LLA;}
"}" {return LLC;}
[=][=] {lexeme=yytext(); return EQUAL;}
"(" {return PA;}
")" {return PC;}
";" {return punt_coma;}
. {return ERROR;}
