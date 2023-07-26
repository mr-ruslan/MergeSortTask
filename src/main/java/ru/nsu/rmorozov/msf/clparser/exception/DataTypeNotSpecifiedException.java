package ru.nsu.rmorozov.msf.clparser.exception;

public class DataTypeNotSpecifiedException extends BadArgumentsException{

    public DataTypeNotSpecifiedException() {
        super("The type of data to be sorted is not specified");
    }
}
