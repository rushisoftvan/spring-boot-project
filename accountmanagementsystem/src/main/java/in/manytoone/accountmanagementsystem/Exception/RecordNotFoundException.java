package in.manytoone.accountmanagementsystem.Exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String msg){
        super(msg);
    }
}
