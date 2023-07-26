package ru.nsu.rmorozov.msf.clparser;

import ru.nsu.rmorozov.msf.clparser.exception.DataTypeNotSpecifiedException;
import ru.nsu.rmorozov.msf.clparser.exception.InputFilesNotSpecifiedException;
import ru.nsu.rmorozov.msf.clparser.exception.OutputFileNotSpecifiedException;
import ru.nsu.rmorozov.msf.sort.parser.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SortParams {
    private Comparator comparator;
    private Parser parser;
    private String out;
    private List<String> ins = new ArrayList<>();

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public List<String> getIns() {
        return ins;
    }

    public void addIn(String s) {
        this.ins.add(s);
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void verify() {
        if (this.comparator == null || this.parser == null){
            throw new DataTypeNotSpecifiedException();
        }
        if (this.out == null){
            throw new OutputFileNotSpecifiedException();
        }
        if (this.ins.size() == 0){
            throw new InputFilesNotSpecifiedException();
        }
    }
}
