package de.chaos.mc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardLine {
    String LINENAME;
    String LINEPREFIX;
    String LINESUFFIX;
    int POSITION;
    boolean update;
}
