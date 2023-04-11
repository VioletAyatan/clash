package com.ankol.api.entity;

import lombok.Data;

import java.util.List;
@Data
public class ItemResult<T> {
    private List<T> items;
}
