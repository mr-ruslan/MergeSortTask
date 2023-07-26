package ru.nsu.rmorozov.msf.sort;

import ru.nsu.rmorozov.msf.sort.parser.Parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MergeSorter<T> extends FileSorter<T>{


    public MergeSorter(Comparator comparator, Parser parser) {
        super(comparator, parser);
    }

    @Override
    public void sort(List<String> inputFilesNames, String outputFileName) {

        try (PrintWriter output = new PrintWriter(outputFileName)){
            Iterator outArray = createArrays(this.comparator, this.parser, inputFilesNames);
            while (outArray.hasNext()){
                output.println(outArray.next());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static Iterator createArrays(Comparator comparator, Parser parser, List<String> files){
        if (files.size() > 1){
            return new VirtualArray(comparator, parser, files);
        }
        else{
            return new FileArray(comparator, parser, files.get(0));
        }
    }

}
