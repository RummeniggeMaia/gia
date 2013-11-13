package br.ufrn.cerescaico.labordoc.gia.negocio;

import br.ufrn.cerescaico.labordoc.gia.dao.ImagemDao;
import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import br.ufrn.cerescaico.labordoc.gia.util.Util;
import java.io.ByteArrayInputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Rummenigge
 */
public class ImagemCtrl {

    private ImagemDao imagemDao;
    private ImagemModel model;

    public ImagemCtrl() throws Exception {
        imagemDao = new ImagemDao();
        model = new ImagemModel();
    }

    public ImagemCtrl(ImagemModel model) throws Exception {
        this.model = model;
        imagemDao = new ImagemDao();
    }

    public ImagemCtrl(ImagemDao imagemDao, ImagemModel model) {
        this.imagemDao = imagemDao;
        this.model = model;
    }

    public ImagemDao getImagemDao() {
        return imagemDao;
    }

    public void setImagemDao(ImagemDao imagemDao) {
        this.imagemDao = imagemDao;
    }

    public ImagemModel getModel() {
        return model;
    }

    public void setModel(ImagemModel model) {
        this.model = model;
    }

    public void salvarImagem(Imagem nova) throws Exception {
        imagemDao.criar(nova);
        model.getImagens().add(nova);
    }
    
    public void deletarImagem() throws Exception {
        imagemDao.excluir(model.getImagemAux());
        model.getImagens().remove(model.getImagemAux());
    }

    public StreamedContent getStreamedContent() {
        StreamedContent sc = null;
        if (model.getImagem().getConteudo() != null) {
            ByteArrayInputStream bais =
                    new ByteArrayInputStream(model.getImagem().getConteudo());
            sc = new DefaultStreamedContent(
                    bais,
                    model.getImagem().getContentType(),
                    model.getImagem().getNome());
        }
        return sc;
    }

    public void carregarImagens(Documento doc) {
        try {
            Imagem i = new Imagem();
            i.setDocumento(doc);
            List<Imagem> imagens = imagemDao.pesquisarImagens(i);
            model.setImagens(imagens);
            model.setExibirDialog(true);
        } catch (Exception e) {
            Util.addMsg(null, e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void limparImagens() {
        model.getImagens().clear();
        model.setImagem(new Imagem("800", "600"));
        model.setExibirDialog(false);
    }
}
