package de.chaos.mc.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BoardPLayer {
    UUID uuid;
    EasyBoard easyBoard;
}
