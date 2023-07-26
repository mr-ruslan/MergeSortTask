package ru.nsu.rmorozov.msf.clparser.exception;

public class OutputFileNotSpecifiedException extends BadArgumentsException{

    public OutputFileNotSpecifiedException() {
        super("The output file name is not specified");
    }
}
