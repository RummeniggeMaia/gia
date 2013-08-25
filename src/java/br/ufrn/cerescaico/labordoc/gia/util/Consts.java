package br.ufrn.cerescaico.labordoc.gia.util;

/**
 * Constantes usadas no sistema para evitar que dados sejam digitados errados
 * durante o desenvolvimento.
 *
 * @author Rummenigge
 */
public class Consts {

    /**
     * Funções do sistema.
     */
    public static final int FUNCAO_CRIAR_USUARIOS = 0;
    public static final int FUNCAO_PESQUISAR_USUARIOS = 1;
    public static final int FUNCAO_EDITAR_USUARIOS = 2;
    public static final int FUNCAO_EXCLUIR_USUARIOS = 3;
    public static final int FUNCAO_CRIAR_DOCUMENTOS = 4;
    public static final int FUNCAO_PESQUISAR_DOCUMENTOS = 5;
    public static final int FUNCAO_EDITAR_DOCUMENTOS = 6;
    public static final int FUNCAO_EXCLUIR_DOCUMENTOS = 7;
    public static final int FUNCAO_CRIAR_CONTA = 8;
    public static final int FUNCAO_EDITAR_PERFIL = 9;
    public static final int FUNCAO_EXCLUIR_CONTA = 10;
    public static final int FUNCAO_CRIAR_NOVOS_TIPOS_DE_DOCUMENTOS = 11;
    //Criterias
    public static final Integer NULL_CRITERIA = 0;
    public static final Integer CRITERIA_USUARIO_ID = 1;
    public static final Integer CRITERIA_USUARIO = 2;
    public static final Integer CRITERIA_AUTENTICAR = 3;
    public static final Integer CRITERIA_DOCUMENTO_ID = 4;
    public static final Integer CRITERIA_DOCUMENTO_TIPO = 5;
    public static final Integer CRITERIA_DOCUMENTO = 6;
    /**
     * String: "_id"
     */
    public static final String _ID = "_id";
    /**
     * String: "home"
     */
    public static final String HOME = "home";
    //Banco de dados
    /**
     * String: "giaDB"
     */
    public static final String BANCO = "giaDB";
    /**
     * String: "usuarios"
     */
    public static final String COLECAO_USUARIOS = "usuarios";
    /**
     * String: "documentos"
     */
    public static final String COLECAO_DOCUMENTOS = "documentos";
    /**
     * String: "visitas"
     */
    public static final String COLECAO_VISITAS = "visitas";
    /**
     * String: "execucoes"
     */
    public static final String COLECAO_EXECUCOES = "execucoes";
    /**
     * String: "funcoes"
     */
    public static final String COLECAO_FUNCOES = "funcoes";
    /**
     * String: "000000000000000000000000"
     */
    public static final String NULL_OBJECT_ID = "000000000000000000000000";
    /**
     * String: "br.ufrn.cerescaico.labordoc.gia.modelo"
     */
    public static final String MODELO_PACKAGE = "br.ufrn.cerescaico.labordoc.gia.modelo";
    //Visita
    /**
     * String: "usuario"
     */
    public static final String USUARIO = "usuario";
    /**
     * String: "documento"
     */
    public static final String DOCUMENTO = "documento";
    /**
     * String: "total_de_visitas"
     */
    public static final String TOTAL_DE_VISITAS = "total_de_visitas";
    /**
     * String: "usuario_logado"
     */
    public static final String USUARIO_LOGADO = "usuario_logado";
    //Usuario
    /**
     * String: "login"
     */
    public static final String CAMPO_LOGIN = "login";
    /**
     * String: "senha"
     */
    public static final String CAMPO_SENHA = "senha";
    /**
     * String: "nome"
     */
    public static final String CAMPO_NOME = "nome";
    /**
     * String: "email"
     */
    public static final String CAMPO_EMAIL = "email";
    /**
     * String: "cpf"
     */
    public static final String CAMPO_CPF = "cpf";
    /**
     * String: "matricula"
     */
    public static final String CAMPO_MATRICULA = "matricula";
    /**
     * String: "logado"
     */
    public static final String LOGADO = "usuario_gia_logado";
    //Documento
    //Cadastro de usuarios
    /**
     * String: "criado"
     */
    public static final String CRIADO = "criado";
    /**
     * String: "nao_criado"
     */
    public static final String NAO_CRIADO = "nao_criado";
}
