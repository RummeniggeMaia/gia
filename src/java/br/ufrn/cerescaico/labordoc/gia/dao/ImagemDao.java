package br.ufrn.cerescaico.labordoc.gia.dao;

import br.ufrn.cerescaico.labordoc.gia.modelo.Imagem;
import br.ufrn.cerescaico.labordoc.gia.negocio.MongoClientSingleton;
import com.mongodb.DB;
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
        gfsif.save();
        bais.close();
//        StreamedContent sc = e.getStreamedContent();
//        InputStream is = sc.getStream();
//        byte[] buffer = new byte[4096];
//        int pos = 0;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        while ((pos = is.read(buffer, 0, buffer.length)) != -1) {
//            baos.write(buffer, 0, pos);
//        }
//        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//        BufferedImage bi = ImageIO.read(bais);
//        String width = String.valueOf(bi.getWidth());
//        String height = String.valueOf(bi.getHeight());
//        bais.close();
//
//        bais = new ByteArrayInputStream(baos.toByteArray());
//        GridFSInputFile gfsif = gridFS.createFile(bais);
//        gfsif.setFilename(sc.getName());
//        gfsif.setContentType(sc.getContentType());
//        gfsif.put("width", width);
//        gfsif.put("height", height);
//        gfsif.save();
//        bais.close();
//        baos.close();
//        BufferedInputStream bis = new BufferedInputStream(e.getInputStream());
//        BufferedImage bi = ImageIO.read(bis);
//        int width = bi.getWidth();
//        int height = bi.getHeight();
//        GridFSInputFile gfsif = gridFS.createFile(bis);
//        gfsif.setFilename(e.getNome());
//        gfsif.setContentType(e.getContentType());
//        gfsif.put("width", width);
//        gfsif.put("height", height);
//        gfsif.save();
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
            Imagem i = new Imagem();
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
            lista.add(i);
            baos.close();
            is.close();
        }
//        StreamedContent sc = e.getStreamedContent();
//        List<GridFSDBFile> bFiles = gridFS.find(sc.getName());
//        for (GridFSDBFile atual : bFiles) {
//            Imagem i = new Imagem();
//            sc = new DefaultStreamedContent(
//                    atual.getInputStream(),
//                    atual.getContentType(),
//                    atual.getFilename());
//            i.setStreamedContent(sc);
//            Object width = atual.get("width");
//            i.setWidth(width == null ? "0" : width.toString());
//            Object height = atual.get("height");
//            i.setHeight(height == null ? "0" : height.toString());
//            lista.add(i);
//        }
        return lista;
    }

    public List<Imagem> pesquisarImagens(List<String> imagens) 
            throws Exception {
        
        List<Imagem> imgs = new ArrayList<Imagem>();
        for (String s : imagens) {
            GridFSDBFile atual = gridFS.findOne(s);
            Imagem i = new Imagem();
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
            imgs.add(i);
            baos.close();
            is.close();
        }
        return imgs;
    }
}
