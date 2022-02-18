package br.com.supera.game.store.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.CONFLICT, reason = "Product already exist in cart")
public class ProductExistException extends RuntimeException {
    public ProductExistException(String message){
        super(message);
        Logger.warn("");
    }
}
