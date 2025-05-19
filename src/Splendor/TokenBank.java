package Splendor;

import java.util.HashMap;
import java.util.Map;

public class TokenBank {
	private final Map<GemColor, Integer> tokens;
	public TokenBank() {
		tokens = new HashMap<>();
		for(var gemcolor : GemColor.values()) {
			tokens.put(gemcolor, 4);
		}
	}
	
	public boolean takeToken(GemColor color) {
	    int count = tokens.getOrDefault(color, 0);
	    if (count > 0) {
	        tokens.put(color, count - 1);
	        return true;
	    }
	    return false;
	}
	
	public void returnToken(GemColor color) {
	    tokens.put(color, tokens.getOrDefault(color, 0) + 1);
	}
	
	public int getCount(GemColor color) {
	    return tokens.getOrDefault(color, 0);
	}
}