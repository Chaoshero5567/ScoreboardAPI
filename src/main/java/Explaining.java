import de.chaos.mc.BoardLine;
import de.chaos.mc.ScoreboardAPI;
import org.bukkit.plugin.Plugin;


import java.util.ArrayList;
import java.util.List;

public class Explaining {

    public Explaining(Plugin plugin) {
        List<BoardLine> lines = new ArrayList<BoardLine>();
        BoardLine test0 = new BoardLine("Test0" , "prefix", "suffix", 0, true);
        BoardLine test1 = new BoardLine("Test1" , "prefix", "suffix", 1, true);
        lines.add(test0);
        lines.add(test1);
        new ScoreboardAPI(plugin, lines, "TITLE OF THE BOARD");
    }
}