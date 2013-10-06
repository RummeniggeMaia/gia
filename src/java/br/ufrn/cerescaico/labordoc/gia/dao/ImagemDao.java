package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import br.ufrn.cerescaico.labordoc.gia.negocio.MongoClientSingleton;
import com.mongodb.DB;
import com.mongodb.gridfs.*;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rummenigge
 */
public class ImagemDao implements DaoIF<Imagem>, Serializable {

    private GridFS gridFS;

    public ImagemDao() throws UnknownHostException {
        DB db = MongoClientSingleton.getInstance().getDB("giaDB");
        gridFS = new GridFS(db);
    }

    @Override
    public Object criar(Imagem e) throws Exception {
        GridFSInputFile gfsif = gridFS.createFile(e.getInputStream());
        gfsif.setFilename(e.getNome());
        gfsif.save();
        return null;
    }

    @Override
    public Object editar(Imagem e) throws Exception {
        throw new UnsupportedOperationException("Ação não suportada.");
    }

    @Override
    public Object excluir(Imagem e) throws Exception {
        gridFS.remove(e.getNome());
        return null;
    }

    @Override
    public List<Imagem> pesquisar(
            Imagem e, int offset,
            int limit,
            Integer criteria) throws Exception {

        List<Imagem> lista = new ArrayList<Imagem>();
        List<GridFSDBFile> bFiles = gridFS.find(e.getNome());
        for (GridFSDBFile atual : bFiles) {
            lista.add(new Imagem(atual.getFilename(), atual.getInputStream()));
        }
        return lista;
    }

    public List<Imagem> pesquisarImagens(List<String> imagens) {
        List<Imagem> imgs = new ArrayList<Imagem>();
        for (String s : imagens) {
            GridFSDBFile bFile = gridFS.findOne(s);
            imgs.add(new Imagem(bFile.getFilename(), bFile.getInputStream()));
        }
        return imgs;
    }
}
