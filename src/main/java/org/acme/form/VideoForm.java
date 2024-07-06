package org.acme.form;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;


import java.io.InputStream;

public class VideoForm {

    @FormParam("nomeImagem")
    @PartType(MediaType.TEXT_PLAIN)
    private String nomeFile;

    @FormParam("video")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream videoData;

    public InputStream getVideoData() {
        return videoData;
    }

    public void setVideoData(InputStream videoData) {
        this.videoData = videoData;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }
}
