package br.ufrn.cerescaico.labordoc.gia.modelo;

import java.io.Serializable;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Rummenigge
 */
public class Imagem implements Serializable {

    private StreamedContent streamedContent;
    private String width = "1024";
    private String height = "768";

    public Imagem() {
    }

    public Imagem(StreamedContent streamedContent, String width, String height) {
        this.streamedContent = streamedContent;
        this.width = width;
        this.height = height;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
