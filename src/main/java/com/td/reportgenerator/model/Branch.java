package com.td.reportgenerator.model;

public class Branch {
    public String name;
    public boolean merged;
    public String webUrl;

    public Branch() {
    }

    public Branch(String name, boolean merged, String webUrl) {
        this.name = name;
        this.merged = merged;
        this.webUrl = webUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
