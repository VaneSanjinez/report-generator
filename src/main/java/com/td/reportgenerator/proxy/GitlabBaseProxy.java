package com.td.reportgenerator.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitlabBaseProxy {
    @Value("${personal_toekn:cxXdxSAm8KmZZe7RZ7i6}")
    protected String personalToken;

    public final String GITLABBASEURL = "http://localhost:9090/api/";

}
