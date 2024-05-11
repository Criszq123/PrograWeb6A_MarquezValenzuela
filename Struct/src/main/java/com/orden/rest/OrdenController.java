package com.orden.rest;

import java.util.Arrays;
import java.util.List;
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
        Map parameters = ActionContext.getContext().getParameters();

        if (!parameters.containsKey("lista") || !parameters.containsKey("idUser") || !parameters.containsKey("id")) {
            System.out.println("No se han ingresado todos los datos solicitados.");
            return new DefaultHttpHeaders("error").disableCaching(); // O devuelve los encabezados adecuados para indicar un error
        }

        int id = Integer.parseInt(request.getParameter("id"));
        List<String> productos = Arrays.asList(parameters.get("lista").toString().split(","));
        int idUser = Integer.parseInt(request.getParameter("idUser"));

        Orden order = new Orden(id, productos, idUser);

        ordenRepository.addOrden(order);

        model = order;

        return new DefaultHttpHeaders("create").disableCaching();
    }

    public HttpHeaders update() {
        Map<String, Parameter> parameters = ActionContext.getContext().getParameters();

        if (!parameters.containsKey("lista") || !parameters.containsKey("idUser")) {
            System.out.println("No se han ingresado todos los datos solicitados.");
            return new DefaultHttpHeaders("error").disableCaching(); // O devuelve los encabezados adecuados para indicar un error
        }


        List<String> productos = Arrays.asList(parameters.get("lista").toString().split(","));

        String idUser = parameters.get("idUser").getValue();


        Orden orden = (Orden) getModel();


        orden.setProductos(productos);
        orden.setIdUser(Integer.parseInt(idUser));


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
