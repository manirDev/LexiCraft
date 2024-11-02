package EXAMPLES;

import LexiCraft.Lexer;
import LexiCraft.Token;

import java.util.List;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sourceCode = scanner.nextLine();
        Lexer lexer = new Lexer(sourceCode);
        List<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.printf("Token {value, type} : {%s, %s}%n", token.value, token.tokenType);
        }
    }
}
