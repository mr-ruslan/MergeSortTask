package ru.nsu.rmorozov.msf.sort;

import ru.nsu.rmorozov.msf.sort.parser.Parser;

import java.util.Comparator;
import java.util.List;

public abstract class FileSorter<T> {

    Comparator<T> comparator;
    Parser parser;

    public FileSorter(Comparator<T> comparator, Parser parser){
        this.comparator = comparator;
        this.parser = parser;
    }

    public abstract void sort(List<String> inputFilesNames, String outputFileName);
}
