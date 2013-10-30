package br.ufrn.cerescaico.labordoc.gia.validator;

/**
 *
 * @author Rummenigge
 */
public class ValidatorResult {

    private boolean valido;
    private String causa;

    public ValidatorResult(boolean valido, String causa) {
        this.valido = valido;
        this.causa = causa;
    }

    public boolean isValido() {
        return valido;
    }

    public String getCausa() {
        return causa;
    }
}
