package ru.nsu.rmorozov.msf.clparser.exception;

public class InputFilesNotSpecifiedException extends BadArgumentsException{

    public InputFilesNotSpecifiedException() {
        super("At least one file to sort must be specified");
    }
}
