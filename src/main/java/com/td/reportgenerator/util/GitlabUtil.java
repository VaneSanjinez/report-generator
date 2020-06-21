package com.td.reportgenerator.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class GitlabUtil {

    public HttpHeaders setGitlabHeaders (String privateToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Private-Token",privateToken);

        return headers;
    }

    public HttpEntity declareTemplate(String privateToken){
        HttpHeaders headers = setGitlabHeaders(privateToken);
        HttpEntity request = new HttpEntity(headers);

        return request;
    }
}
