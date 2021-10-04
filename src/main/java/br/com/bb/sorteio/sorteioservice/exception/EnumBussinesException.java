package br.com.bb.sorteio.sorteioservice.exception;

import org.springframework.http.HttpStatus;

public enum EnumBussinesException {

    NOT_FOUND("Cliente não encontrado", HttpStatus.NOT_FOUND),
    NRO_CUPOM_NAO_ENCONTRADO("Cupom fiscal inválido ou não pertence ao cliente informado", HttpStatus.BAD_REQUEST),
    SORTEIO_NAO_ENCONTRADO("Sorteio não encontrado", HttpStatus.NOT_FOUND)
    ;

    final String message;
    final HttpStatus httpStatus;

    EnumBussinesException(final String message, final HttpStatus httpStatus)
    {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public BusinessException exception()
    {
        return new BusinessException(this.message, this.httpStatus);
    }
}
