package LexiCraft;


/*
    A Token has : {value, type}
 */
public class Token {
    public final String value;
    public final TokenType tokenType;

    public Token(String value, TokenType tokenType){
        this.value = value;
        this.tokenType = tokenType;
    }
}
