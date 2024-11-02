package LexiCraft;


/*
    var x = 7 + (mii + moo);
    [VarToken, IdentifierToken, EqualToken, NumberToken...]
 */
public enum TokenType {
    Number, // 1, 2, 3 ...
    Identifier,// foo, bar, ...
    Equals, // =
    OpenParen, CloseParen, // (, )...
    BinaryOperator, // +, - , *. /
    Var,
}

