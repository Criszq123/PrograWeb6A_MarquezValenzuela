package com.product.rest;

import java.util.HashMap;
import java.util.Map;

public class productRepository {

        private static Map<String, product> map = new HashMap<>();

        public productRepository() { }

        public product getProductById(String id) {
            return map.get(id);
        }

        public Map<String, product> findAllProduct() {
            return map;
        }

        public void addProduct(product product) {
            map.put(String.valueOf(product.getId()), product);
        }

        public void updateProduct(product e) {
            map.put(String.valueOf(e.getId()), e);
            System.out.println(map);
        }

        public void deleteProduct(String id) {
            map.remove(id);
        }
}
