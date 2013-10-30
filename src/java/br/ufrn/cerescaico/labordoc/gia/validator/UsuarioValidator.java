package br.ufrn.cerescaico.labordoc.gia.validator;

import java.io.Serializable;

/**
 *
 * @author Rummenigge
 */
public class UsuarioValidator implements Serializable {

    public ValidatorResult validarLogin(String login) {
        if (login.isEmpty()) {
            return new ValidatorResult(false, "Campo login vazio.");
        } else if (login.length() < 4) {
            return new ValidatorResult(false,
                    "O login deve conter no mínimo 4 caracteres.");
        } else if (!login.matches("[a-zA-Z]+[a-zA-Z0-9_]*")) {
            return new ValidatorResult(false, "Login inválido.");
        }
        return new ValidatorResult(true, "");
    }

    public ValidatorResult validarSenha(String senha) {
        if (senha.isEmpty()) {
            return new ValidatorResult(false, "Campo senha vazio.");
        } else if (senha.length() < 4) {
            return new ValidatorResult(false,
                    "Senha com no mínimo 4 caracteres.");
        }
        return new ValidatorResult(true, "");
    }

    public ValidatorResult confimarSenhas(String senha, String senhaConf) {
        return new ValidatorResult(senha.equals(senhaConf), "");
    }

    public ValidatorResult validarNome(String nome) {
        if (nome.isEmpty()) {
            return new ValidatorResult(false, "Campo nome vazio.");
        } else if (nome.length() < 5) {
            return new ValidatorResult(false,
                    "O nome deve conter no mínimo 5 letras.");
        } else if (!nome.matches("[a-zA-Z ]+")) {
            return new ValidatorResult(
                    false,
                    "Caracteres inválidos no nome. Evite acentos, "
                    + "números, e caracteres especiais.");
        }
        return new ValidatorResult(true, "");
    }

    public ValidatorResult validarEmail(String email) {
        if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)"
                + "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return new ValidatorResult(false, "E-mail inválido.");
        }
        return new ValidatorResult(true, "");
    }

    public ValidatorResult validarCpf(String cpf) {
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            return new ValidatorResult(false, 
                    "CPF inválido. Preencha o campo completamente");
        }
        return new ValidatorResult(true, "");
    }
}
