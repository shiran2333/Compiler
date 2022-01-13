grammar lab;

compUnit:       decl* funcDef;
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
                | block
                | (exp)? ';'
                | IF '(' cond ')' stmt (ELSE stmt)?
                | WHILE '(' cond ')' stmt
                | CONTINUE
                | BREAK
                | RETURN exp ';';
exp:            addExp;
cond:           lorExp;
lval:           ident;
primaryExp:     '(' exp ')' | lval | number;
unaryExp:       primaryExp
                | ident '(' (funcRParams)? ')'
                | unaryOp unaryExp;
unaryOp:        ADD | SUB | NOT;
funcRParams:    exp ( ',' exp )*;
mulExp:         unaryExp
                | mulExp (MUL | DIV | MOD) unaryExp;
addExp:         mulExp
                | addExp (ADD | SUB) mulExp;
relExp:         addExp
                | relExp (LT | GT | LTEQ | GTEQ) addExp;
eqExp:          relExp
                | eqExp (EQ | NEQ) relExp;
landExp:        eqExp
                | landExp '&&' eqExp;
lorExp:         landExp
                | lorExp '||' landExp;
number:         DecimalConst | OctalConst | HexadecimalConst;
ident:          Ident;

RETURN: 'return';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
CONTINUE: 'continue';
BREAK: 'break';

LT: '<';
GT: '>';
LTEQ: '<=';
GTEQ: '>=';
EQ: '==';
NEQ: '!=';

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

NOT: '!';
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