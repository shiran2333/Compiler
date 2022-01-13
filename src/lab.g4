grammar lab;

compUnit: funcDef;
funcDef: funcType ident '(' ')' block;
funcType: 'int';
ident: 'main';
block: '{' stmt '}';
stmt: 'return' exp ';';
exp: addExp;
addExp: mulExp | addExp (ADD | SUB) mulExp;
mulExp: unaryExp | mulExp (MUL | DIV | MOD) unaryExp;
unaryExp: primaryExp | unaryOp unaryExp;
primaryExp: '(' exp ')' | number;
unaryOp: ADD | SUB;
number: DecimalConst | OctalConst | HexadecimalConst;

DecimalConst: NonzeroDigit DC;
fragment DC: (Digit DC)?;
OctalConst: '0' OC;
fragment OC: (OctalDigit OC)?;
HexadecimalConst: HexadecimalPrefix HexadecimalDigit HC;
fragment HC: (HexadecimalDigit HC)?;

HexadecimalPrefix: '0x' | '0X';
Digit: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
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