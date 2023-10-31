package org.ankol.server.api.entity;

import lombok.Data;

import java.util.List;
@Data
public class ItemResult<T> {
    private List<T> items;
}
