package ru.nsu.rmorozov.msf.sort;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.rmorozov.msf.sort.parser.Parser;
import ru.nsu.rmorozov.msf.sort.parser.exception.ParseException;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;

public class FileArray implements Iterator {

    private final static Logger logger = LogManager.getLogger(FileArray.class);

    private Comparator comparator;
    private Parser parser;
    private BufferedReader stream = null;
    private String fileName;

    private Object result = null;
    private Object last = null;
    private int unsorted = 0;

    public FileArray(Comparator comparator, Parser parser, String fileName){
        this.comparator = comparator;
        this.parser = parser;
        this.fileName = fileName;
        try {
            this.stream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            logger.error(String.format("file \"%s\" not found", fileName));
        }
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
        if (stream == null){
            return null;
        }
        Object result = readNewObject();

        while (this.last != null && result != null && this.comparator.compare(result,this.last) > 0){
            result = readNewObject();
            ++unsorted;
        }
        this.last = result;
        return result;

    }

    private Object readNewObject(){
        String line = null;

        boolean found = false;
        Object result = null;

        while (!found) {

            try {
                line = stream.readLine();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            if (line == null){
                try {
                    stream.close();
                } catch (IOException ignored) { }
                stream = null;
                if (unsorted > 0) {
                    logger.warn(String.format("%d unsorted items in file \"%s\" were skipped", unsorted, fileName));
                }
            }

            try {
                result = line == null ? null : this.parser.apply(line);
                found = true;
            } catch (ParseException e) {
                logger.info(String.format("Invalid item in file \"%s\": %s", fileName, line));
            }
        }
        return result;
    }

}
