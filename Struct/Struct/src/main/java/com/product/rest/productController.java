package com.product.rest;

import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
public class productController implements ModelDriven<Object>{

        private static final long serialVersionUID = 1L;
        private String id;

        private Object model;
        private productRepository productRepository = new productRepository();
        private Map<String, product> map;
        {
            map = productRepository.findAllProduct();
        }

        public HttpHeaders index() {
            model = map;
            return new DefaultHttpHeaders("index");
        }

        public HttpHeaders show() {
            model = productRepository.getProductById(id);
            return new DefaultHttpHeaders("show");
        }

        public HttpHeaders create() {
            Map parameters = ActionContext.getContext().getParameters();

            int id = Integer.parseInt(parameters.get("id").toString());
            String name = parameters.get("name").toString();
            String categoria = parameters.get("Categoria").toString();
            String precio = parameters.get("Categoria").toString();


            product product = new product(id, name, categoria,precio);

            productRepository.addProduct(product);

            model = product;

            return new DefaultHttpHeaders("create").disableCaching();
        }

        public HttpHeaders update() {
            Map<String, Parameter> parameters = ActionContext.getContext().getParameters();

            String name = parameters.get("name").getValue(); // Get the parameter value
            String categoria = parameters.get("company").getValue(); // Get the parameter value
            String precio = parameters.get("precio").getValue(); // Get the parameter value

            // Obtener el objeto Employee actual y actualizar sus datos
            product product = (product) getModel();
            product.setName(name);
            product.setCategoria(categoria);
            product.setPrecio(precio);

            // Actualizar el empleado en el repositorio
            productRepository.updateProduct(product);

            model = product;

            System.out.println(model);

            return new DefaultHttpHeaders("update").disableCaching();
        }

        public HttpHeaders destroy() {
            productRepository.deleteProduct(id);
            return new DefaultHttpHeaders("destroy").disableCaching();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            model = productRepository.getProductById(id);
            this.id = id;
        }

    @Override
    public Object getModel() {
        return null;
    }
}
