package br.ufrn.cerescaico.labordoc.gia.validator;

/**
 *
 * @author Rummenigge
 */
public class TipoValidator {

    public ValidatorResult validarNome(String nome) {
        if (nome.isEmpty()) {
            return new ValidatorResult(false, "Campo nome vazio.");
        } else if (nome.length() < 4) {
            return new ValidatorResult(false,
                    "Digite um nome com no mínimo 4 caracteres. .");
        } else if (!nome.matches("^[a-zA-Z].*")) {
            return new ValidatorResult(false,
                    "O nome do tipo deve iniciar com uma letra.");
        } else if (!nome.matches("[a-zA-Z]+[a-zA-Z0-9 ]*")) {
            return new ValidatorResult(false, "Evite acentos e caracteres "
                    + "especiais, use apenas letras ou números.");
        }
        return new ValidatorResult(true, "");
    }
}
