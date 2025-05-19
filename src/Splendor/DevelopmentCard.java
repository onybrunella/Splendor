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

	public String[] toCardLines() {
		String[] lines = new String[6];

		StringBuilder costLine = new StringBuilder();
		boolean first = true;
		for (Map.Entry<GemColor, Integer> entry : cost.entrySet()) {
			if (!first) costLine.append(", ");
			costLine.append(entry.getValue()).append(" ").append(entry.getKey());
			first = false;
		}

		lines[0] = "+------------------+";
		lines[1] = String.format("| Level: %-8s |", level);
		lines[2] = String.format("| Bonus : %-8s |", bonus);
		lines[3] = String.format("| Prestige: %-6s |", prestige);
		lines[4] = String.format("| Cost : %-9s |", costLine.toString());
		lines[5] = "+------------------+";

		return lines;
	}

}