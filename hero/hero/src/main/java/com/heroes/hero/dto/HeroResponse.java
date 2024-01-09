package com.heroes.hero.dto;

import lombok.Data;

import java.util.List;
@Data
public class HeroResponse {

    private List<HeroDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
