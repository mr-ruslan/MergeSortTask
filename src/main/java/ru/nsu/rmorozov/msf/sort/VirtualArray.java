package ru.nsu.rmorozov.msf.sort;

import ru.nsu.rmorozov.msf.sort.parser.Parser;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class VirtualArray implements Iterator {

    private Iterator left;
    private Iterator right;
    private Comparator comparator;
    private Parser parser;

    private Object currentLeft = null;
    private Object currentRight = null;
    private Object result = null;

    public VirtualArray(Comparator comparator, Parser parser, List<String> files){
        this.comparator = comparator;
        this.parser = parser;
        this.left = MergeSorter.createArrays(comparator, parser, files.subList(0, files.size()/2));
        this.right = MergeSorter.createArrays(comparator, parser, files.subList(files.size()/2, files.size()));
    }

    @Override
    public boolean hasNext() {
        if (result != null){
            return true;
        }
        this.result = next();
        return this.result != null;
    }

    @Override
    public Object next() {

        if (this.result != null) {
            Object result = this.result;
            this.result = null;
            return result;
        }

        if (currentLeft == null)
            currentLeft = left.next();
        if (currentRight == null)
            currentRight = right.next();
        Object result = null;
        if (currentLeft == null){
            result = currentRight;
            currentRight = right.next();
        }
        else if (currentRight == null){
            result = currentLeft;
            currentLeft = left.next();
        }
        else if (comparator.compare(currentLeft, currentRight) >= 0){
            result = currentLeft;
            currentLeft = left.next();
        }
        else{
            result = currentRight;
            currentRight = right.next();
        }
        return result;
    }

}
