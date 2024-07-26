package org.acme.form;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;


import java.io.InputStream;

public class VideoForm {

    @FormParam("nomeFile")
    @PartType(MediaType.TEXT_PLAIN)
    private String nomeFile;

    @FormParam("video")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream video;

    public InputStream getVideo() {
        return video;
    }

    public void setVideo(InputStream video) {
        this.video = video;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }
}
