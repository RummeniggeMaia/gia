package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Documento;
import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import br.ufrn.cerescaico.labordoc.gia.negocio.MongoClientSingleton;
import br.ufrn.cerescaico.labordoc.gia.util.Consts;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBRef;
import com.mongodb.gridfs.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.bson.types.ObjectId;

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
        ByteArrayInputStream bais = new ByteArrayInputStream(e.getConteudo());
        BufferedImage bi = ImageIO.read(bais);
        String width = String.valueOf(bi.getWidth());
        String height = String.valueOf(bi.getHeight());
        bais.close();

        bais = new ByteArrayInputStream(e.getConteudo());
        GridFSInputFile gfsif = gridFS.createFile(bais);
        gfsif.setFilename(e.getNome());
        gfsif.setContentType(e.getContentType());
        gfsif.put("width", width);
        gfsif.put("height", height);
        gfsif.put("documento", new DBRef(
                gridFS.getDB(),
                Consts.COLECAO_DOCUMENTOS,
                e.getDocumento().getId()));
        gfsif.save();
        bais.close();
        return null;
    }

    @Override
    public Object editar(Imagem e) throws Exception {
        throw new UnsupportedOperationException("Ação não suportada.");
    }

    @Override
    public Object excluir(Imagem e) throws Exception {
        gridFS.remove(e.getId());
        return null;
    }

    @Override
    public List<Imagem> pesquisar(
            Imagem e, int offset,
            int limit,
            Integer criteria) throws Exception {

        return pesquisarImagens(e);
    }

    public List<Imagem> pesquisarImagens(Imagem imagem)
            throws Exception {

        List<Imagem> imgs = new ArrayList<Imagem>();
        List<GridFSDBFile> lista = gridFS.find(
                new BasicDBObject("documento", new DBRef(
                gridFS.getDB(),
                Consts.COLECAO_DOCUMENTOS,
                imagem.getDocumento().getId())));
        for (GridFSDBFile atual : lista) {
            Imagem i = new Imagem();
            i.setId((ObjectId) atual.get(Consts._ID));
            i.setNome(atual.getFilename());
            i.setContentType(atual.getContentType());
            Object width = atual.get("width");
            i.setWidth(width == null ? "0" : width.toString());
            Object height = atual.get("height");
            i.setHeight(height == null ? "0" : height.toString());

            InputStream is = atual.getInputStream();
            byte[] buffer = new byte[4096];
            int pos = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((pos = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, pos);
            }
            i.setConteudo(baos.toByteArray());
            DBRef bRef = (DBRef) atual.get("documento");
            Documento d = new Documento();
            d.setId((ObjectId) bRef.getId());
            i.setDocumento(d);
            imgs.add(i);
            baos.close();
            is.close();
        }
//        for (String s : imagens) {
//            GridFSDBFile atual = gridFS.findOne(s);
//            Imagem i = new Imagem();
//            i.setNome(atual.getFilename());
//            i.setContentType(atual.getContentType());
//            Object width = atual.get("width");
//            i.setWidth(width == null ? "0" : width.toString());
//            Object height = atual.get("height");
//            i.setHeight(height == null ? "0" : height.toString());
//            
//            InputStream is = atual.getInputStream();
//            byte[] buffer = new byte[4096];
//            int pos = 0;
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            while ((pos = is.read(buffer, 0, buffer.length)) != -1) {
//                baos.write(buffer, 0, pos);
//            }
//            i.setConteudo(baos.toByteArray());
//            imgs.add(i);
//            baos.close();
//            is.close();
//        }
        return imgs;
    }
}
