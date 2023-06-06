package golovin.store.gusli.security.exception;

public class TokenNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "The token does not exist";
    }
}
