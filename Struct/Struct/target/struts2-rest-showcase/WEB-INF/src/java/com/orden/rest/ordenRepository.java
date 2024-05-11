package com.orden.rest;

import java.util.HashMap;
import java.util.Map;

public class OrdenRepository {
    private static Map<String, Orden> map = new HashMap<String, Orden>();

    public OrdenRepository() { }

    public Orden getOrdenById(String id) {
        return map.get(id);
    }

    public Map<String, Orden> findAllOrden() {
        return map;
    }

    public void addOrden(Orden orden) {
        map.put(String.valueOf(orden.getId()), orden);
    }

    public void updateOrden(Orden e) {
        map.put(String.valueOf(e.getId()), e);
        System.out.println(map);
    }

    public void deleteOrden(String id) {
        map.remove(id);
    }
}
