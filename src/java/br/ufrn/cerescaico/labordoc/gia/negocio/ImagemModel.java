package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.ImagemDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class ImagemModel {

    private Imagem imagem = new Imagem("1024", "600");
    private Imagem imagemAux;
    private List<Imagem> imagens = new ArrayList<Imagem>();

    public ImagemModel() {
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Imagem getImagemAux() {
        return imagemAux;
    }

    public void setImagemAux(Imagem imagemAux) {
        this.imagemAux = imagemAux;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }
}
