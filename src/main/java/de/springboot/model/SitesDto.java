package de.springboot.model;

import lombok.Data;

import java.util.List;

@Data
public class SitesDto {
    List<SiteDto> items;

    public List<SiteDto> getItems() {
        return items;
    }
}
