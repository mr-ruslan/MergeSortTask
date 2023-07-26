package ru.nsu.rmorozov.msf.clparser;

import ru.nsu.rmorozov.msf.clparser.exception.BadArgumentsException;
import ru.nsu.rmorozov.msf.sort.comparator.IntegerComparator;
import ru.nsu.rmorozov.msf.sort.comparator.StringComparator;
import ru.nsu.rmorozov.msf.sort.parser.IntParser;
import ru.nsu.rmorozov.msf.sort.parser.StringParser;

public class CLParser {

    public static SortParams parse(String[] args) throws BadArgumentsException {
        SortParams params = new SortParams();
        SortMode mode = SortMode.ASCENDING;
        int i = 0;
        for (String arg: args){
            if (arg.equals("-a")){
                mode = SortMode.ASCENDING;
            }
            else if (arg.equals("-d")){
                mode = SortMode.DESCENDING;
            }
            else if (arg.equals("-i")){
                params.setComparator(new IntegerComparator());
                params.setParser(new IntParser());
            }
            else if (arg.equals("-s")){
                params.setComparator(new StringComparator());
                params.setParser(new StringParser());
            }
            else {
                if (i==0){
                    params.setOut(arg);
                }
                else{
                    params.addIn(arg);
                }
                ++i;
            }
        }
        params.verify();
        if (mode == SortMode.ASCENDING){
            params.setComparator(params.getComparator().reversed());
        }
        return params;
    }
}
