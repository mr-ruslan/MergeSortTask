package ru.nsu.rmorozov.msf.sort.parser;

import ru.nsu.rmorozov.msf.sort.parser.exception.ParseException;

public interface Parser{
    public Object apply(String s) throws ParseException;
}
