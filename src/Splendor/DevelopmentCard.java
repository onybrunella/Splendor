package Splendor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record DevelopmentCard(int level, GemColor bonus, Map<GemColor, Integer> cost, int prestige) {
	public DevelopmentCard{
		if(level < 1 || level >3) {
			throw new IllegalArgumentException();
		}
		if(prestige != 1) {
			throw new IllegalArgumentException();
		}
		Objects.requireNonNull(cost);
		Objects.requireNonNull(bonus);
	}
}