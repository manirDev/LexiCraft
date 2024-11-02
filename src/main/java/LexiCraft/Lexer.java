package LexiCraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {
    private final String source;
    private final List<Token> tokens = new ArrayList<Token>();

    private static final Map<String, TokenType> KEYWORDS;
    static {
        KEYWORDS = new HashMap<>();
        KEYWORDS.put("var", TokenType.Var);
    }


    public Lexer(String source){
        this.source = source;
    }

    public List<Token> tokenize() {
        char[] sourceCode = source.toCharArray();
        int index = 0;
        while (index < sourceCode.length) {
            char currenChar = sourceCode[index];
            // Handling single char tokens
            if (currenChar == '(') {
                pushToken(String.valueOf(currenChar), TokenType.OpenParen);
                index++;
            } else if (currenChar == ')') {
                pushToken(String.valueOf(currenChar), TokenType.CloseParen);
                index++;
            } else if (currenChar == '=') {
                pushToken(String.valueOf(currenChar), TokenType.Equals);
                index++;
            } else if (currenChar == '-' || currenChar == '+' || currenChar == '*' || currenChar == '/') {
                pushToken(String.valueOf(currenChar), TokenType.BinaryOperator);
                index++;
            }
            // Handling multi-char tokens
            // Identifier token
            // Identifiers must start with letter or underscore
            else if (isAlpha(currenChar) || currenChar == '_') {
                StringBuilder identifier = new StringBuilder();
                identifier.append(currenChar);
                index++;
                while (index < sourceCode.length && isAlphaNumeric(sourceCode[index])){
                    identifier.append(sourceCode[index]);
                    index++;
                }
                if (isReserved(identifier.toString())) {
                    TokenType keyword = KEYWORDS.get(identifier.toString());
                    pushToken(identifier.toString(), keyword);
                }
                else{
                    pushToken(identifier.toString(), TokenType.Identifier);
                }
            }
            // Number token
            else if (isDigit(currenChar)) {
                StringBuilder number = new StringBuilder();
                while (index < sourceCode.length && isDigit(sourceCode[index])) {
                    number.append(sourceCode[index]);
                    index++;
                }
                pushToken(number.toString(), TokenType.Number);
            }
            //ignorable chars like : tab, white space and new line;
            else if (canSkip(currenChar)) {
                index++;
            }
            else {
                System.out.println("Unrecognized character found in source code: " + currenChar);
                break;
            }
        }
        return tokens;
    }


    private boolean canSkip(char currenChar){
        boolean whiteSpace = currenChar == ' ';
        boolean tab = currenChar == '\t';
        boolean newLine = currenChar == '\n';
        return whiteSpace || tab || newLine;
    }
    private boolean isReserved(String identifier){
        return KEYWORDS.containsKey(identifier);
    }
    private boolean isAlpha(char currenChar){
        boolean lowerCase = (currenChar >= 'a' && currenChar <= 'z');
        boolean upperCase = (currenChar >= 'A' && currenChar <= 'Z');
        return lowerCase || upperCase;
    }
    private boolean isAlphaNumeric(char currenChar){
        return isAlpha(currenChar) || (currenChar >= '0' && currenChar <= '9');
    }

    private boolean isDigit(char currenChar){
        return (currenChar >= '0' && currenChar <= '9');
    }
    private void pushToken(String value, TokenType tokenType) {
        tokens.add(new Token(value, tokenType));
    }
}
