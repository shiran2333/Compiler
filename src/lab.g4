grammar lab;

compUnit:       funcDef;
decl:           constDecl | varDecl;
constDecl:      'const' bType constDef ( ',' constDef )* ';' ;
bType:          'int';
constDef:       ident '=' constInitVal;
constInitVal:   constExp;
constExp:       addExp;
varDecl:        bType varDef ( ',' varDef )* ';';
varDef:         ident
                | ident '=' initVal;
initVal:        exp;
funcDef:        funcType ident '(' ')' block;
funcType:       'int';
block:          '{' (blockItem)* '}';
blockItem:      decl | stmt;
stmt:           lval '=' exp ';'
                | (exp)? ';'
                | RETURN exp ';';
exp:            addExp;
lval:           ident;
primaryExp:     '(' exp ')' | lval | number;
addExp:         mulExp
                | addExp (ADD | SUB) mulExp;
mulExp:         unaryExp
                | mulExp (MUL | DIV | MOD) unaryExp;
unaryExp:       primaryExp
                | ident '(' (funcRParams) ')'
                | unaryOp unaryExp;
funcRParams:    exp ( ',' exp )*;
unaryOp:        ADD | SUB;
number:         DecimalConst | OctalConst | HexadecimalConst;
ident:          Ident;

RETURN: 'return';

Ident: NonDigit IC;
fragment IC: ((NonDigit | Digit) IC)?;
DecimalConst: NonzeroDigit DC;
fragment DC: (Digit DC)?;
OctalConst: '0' OC;
fragment OC: (OctalDigit OC)?;
HexadecimalConst: HexadecimalPrefix HexadecimalDigit HC;
fragment HC: (HexadecimalDigit HC)?;

HexadecimalPrefix: '0x' | '0X';
Digit: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
NonDigit: '_' | [a-z] | [A-Z];
NonzeroDigit: '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
OctalDigit: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
HexadecimalDigit: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'A' | 'B' | 'C' | 'D' | 'E' | 'F';

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';

Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;