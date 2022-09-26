package de.chaos.mc.utils;

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
