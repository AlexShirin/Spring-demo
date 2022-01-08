package de.springboot.model;

import lombok.Data;

@Data
public class SiteDto {
    private String favicon_url;
    private String audience;
    private String site_url;
    private String name;

    public String getFavicon_url() {
        return favicon_url;
    }

    public void setFavicon_url(String favicon_url) {
        this.favicon_url = favicon_url;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
