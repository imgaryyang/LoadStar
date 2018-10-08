package com.ciel.pocket.link.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class PageableListModel<T> {
    public PageableListModel() {
        total = 0;
    }

    private int total;

    private List<T> items;
}
