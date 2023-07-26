package ru.nsu.rmorozov.msf.sort.parser;

import ru.nsu.rmorozov.msf.sort.parser.exception.ParseException;

public class IntParser implements Parser {

    @Override
    public Integer apply(String s) throws ParseException{
        try {
            return Integer.valueOf(s);
        }
        catch (NumberFormatException e){
            throw new ParseException(e.getMessage());
        }
    }
}
