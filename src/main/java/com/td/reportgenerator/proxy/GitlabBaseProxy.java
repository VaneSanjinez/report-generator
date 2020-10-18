package com.td.reportgenerator.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitlabBaseProxy {
    @Value("${personal_token:cxXdxSAm8KmZZe7RZ7i6}")
    protected String personalToken;

    protected static final String GITLAB_POC_BASE_URL = "http://localhost:9090/api/";

    protected static final String GITLAB_BASE_URL = "https://gitlab.com/api/v4/";

}
