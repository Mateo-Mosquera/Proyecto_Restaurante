package Framework;

public class MsgException extends Exception {

    public MsgException(String e, String clase, String metodo) {
        //grabar el log
        System.out.println("[ERROR APEARED --> LOG] " + clase +"."+ metodo +" : "+ e );
    }

    @Override 
    public String getMessage(){
        return "Ups algo anda mal...";
    }    
}