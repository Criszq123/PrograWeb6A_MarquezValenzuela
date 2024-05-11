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

    public void addOrden(Orden order) {
        map.put(String.valueOf(order.getId()), order);
    }

    public void updateOrden(Orden o) {
        map.put(String.valueOf(o.getId()), o);
        System.out.println(map);
    }

    public void deleteOrden(String id) {
        map.remove(id);
    }
}

