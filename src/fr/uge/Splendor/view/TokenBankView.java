package fr.uge.Splendor.view;
import Splendor.tokens.*;
import fr.uge.Splendor.tokens.GemColor;
import fr.uge.Splendor.tokens.TokenBank;

public class TokenBankView {
    public static void display(TokenBank bank) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Banque de jetons ===\n");
        for (GemColor color : GemColor.values()) {
            int count = bank.getTokens().getOrDefault(color, 0);
            sb.append(color).append(": ").append(count).append(" ");
        }
        System.out.println(sb);
    }
}

