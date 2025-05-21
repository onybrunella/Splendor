package fr.uge.Splendor.tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenBank {
	private final Map<GemColor, Integer> tokens;
	public TokenBank() {
		tokens = new HashMap<>();
		for(var gemcolor : GemColor.values()) {
			tokens.put(gemcolor, 4);
		}
	}

	public Map<GemColor, Integer> getTokens() {
		return tokens;
	}

	public boolean canTakeTwoSame(GemColor color) {
		return tokens.getOrDefault(color, 0) >= 4;
	}

	public boolean canTakeThreeDifferent(GemColor c1, GemColor c2, GemColor c3) {
		return tokens.getOrDefault(c1, 0) > 0 &&
				tokens.getOrDefault(c2, 0) > 0 &&
				tokens.getOrDefault(c3, 0) > 0;
	}

	public void takeTwoSame(GemColor color) {
		tokens.put(color, tokens.get(color) - 2);
	}

	public void takeOne(GemColor color) {
		tokens.put(color, tokens.get(color) - 1);
	}

	public void returnToken(GemColor color, int amount) {
		tokens.put(color, tokens.getOrDefault(color, 0) + amount);
	}

	@Override
	//j'ai fais un stream
	public String toString() {
		return "Banque: " + tokens.entrySet().stream()
				.map(entry -> entry.getKey() + ": " + entry.getValue())
				.collect(Collectors.joining(", "));
	}
}