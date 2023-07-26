package ru.nsu.rmorozov.msf;

import ru.nsu.rmorozov.msf.clparser.CLParser;
import ru.nsu.rmorozov.msf.clparser.SortParams;
import ru.nsu.rmorozov.msf.clparser.exception.BadArgumentsException;
import ru.nsu.rmorozov.msf.sort.FileSorter;
import ru.nsu.rmorozov.msf.sort.MergeSorter;


public class Main {
    public static void main(String[] args) {
        try {
            SortParams sortParams = CLParser.parse(args);
            FileSorter sorter = new MergeSorter(sortParams.getComparator(), sortParams.getParser());
            sorter.sort(sortParams.getIns(), sortParams.getOut());
        }
        catch (BadArgumentsException e){
            System.out.println(e.getMessage());
        }
    }
}
