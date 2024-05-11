package com.orden.rest;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import javax.servlet.http.HttpServletRequest;

public class OrdenController implements ModelDriven<Object>{
    private static final long serialVersionUID = 1L;
    private String id;

    private Object model;
    private OrdenRepository ordenRepository = new OrdenRepository();
    private Map<String, Orden> map;
    {
        map = ordenRepository.findAllOrden();
    }

    public HttpHeaders index() {
        model = map;
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        model = ordenRepository.getOrdenById(id);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders create() {
        HttpServletRequest request = ServletActionContext.getRequest();

        int id = Integer.parseInt(request.getParameter("id"));
        String[] productList = request.getParameterValues("productList");
        int idUser = Integer.parseInt(request.getParameter("idUser"));

        Orden order = new Orden(id, productList, idUser);

        ordenRepository.addOrden(order);

        model = order;

        return new DefaultHttpHeaders("create").disableCaching();
    }

    public HttpHeaders update() {
        Map<String, Parameter> parameters = ActionContext.getContext().getParameters();

        String[] lista = new String[]{parameters.get("lista").getValue()}; // Get the parameter value
        String idUser = parameters.get("idUser").getValue(); // Get the parameter value

        // Obtener el objeto Orden actual y actualizar sus datos
        Orden orden = (Orden) getModel();
        orden.setLista(lista);
        orden.setIdUser(Integer.parseInt(idUser));

        // Actualizar el orden en el repositorio
        ordenRepository.updateOrden(orden);

        model = orden;

        System.out.println(model);

        return new DefaultHttpHeaders("update").disableCaching();
    }

    public HttpHeaders destroy() {
        ordenRepository.deleteOrden(id);
        return new DefaultHttpHeaders("destroy").disableCaching();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        model = ordenRepository.getOrdenById(id);
        this.id = id;
    }

    @Override
    public Object getModel() {
        return model;
    }

}
