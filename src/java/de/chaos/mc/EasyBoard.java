package de.chaos.mc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.scoreboard.Objective;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EasyBoard {
    List<BoardLine> lines;
    Objective objective;
    String title;
}
