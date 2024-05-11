package com.user.rest;

import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import java.util.Map;

public class UserController implements ModelDriven<Object> {
        private static final long serialVersionUID = 1L;
        private String id;
        private Object model;
        private UserRepository userRepository = new UserRepository();
        private Map<String, User> map;
        {
            map = userRepository.findAllUser();
        }

        public HttpHeaders index() {
            model = map;
            return new DefaultHttpHeaders("index");
        }

        public HttpHeaders show() {
            model = userRepository.getUserById(id);
            return new DefaultHttpHeaders("show");
        }

        public HttpHeaders create() {
            Map parameters = ActionContext.getContext().getParameters();

            if (!parameters.containsKey("id") || !parameters.containsKey("nombre") || !parameters.containsKey("apellidos") || !parameters.containsKey("Email")) {
                System.out.println("No se han ingresado todos los datos solicitados.");
                return new DefaultHttpHeaders("error").disableCaching(); // O devuelve los encabezados adecuados para indicar un error
            }

            int id = Integer.parseInt(parameters.get("id").toString());
            String name = parameters.get("nombre").toString();
            String lastName = parameters.get("apellidos").toString();
            String email = parameters.get("Email").toString();

            User user = new User(id,name,lastName,email);

            userRepository.addUser(user);

            model = user;

            return new DefaultHttpHeaders("create").disableCaching();
        }

        public HttpHeaders update() {
            Map<String, Parameter> parameters = ActionContext.getContext().getParameters();

            if (!parameters.containsKey("nombre") || !parameters.containsKey("apellidos") || !parameters.containsKey("Email")) {
                System.out.println("No se han ingresado todos los datos solicitados.");
                return new DefaultHttpHeaders("error").disableCaching(); // O devuelve los encabezados adecuados para indicar un error
            }
            String name = parameters.get("nombre").getValue(); // Get the parameter value
            String lastName = parameters.get("apellidos").getValue(); // Get the parameter value
            String email = parameters.get("Email").getValue();

            // Obtener el objeto User actual y actualizar sus datos
            User user = (User) getModel();
            user.setName(name);
            user.setLastName(lastName);
            user.setEmail(email);

            // Actualizar el usuario en el repositorio
            userRepository.updateUser(user);

            model = user;

            System.out.println(model);

            return new DefaultHttpHeaders("update").disableCaching();
        }

        public HttpHeaders destroy() {
            userRepository.deleteUser(id);
            return new DefaultHttpHeaders("destroy").disableCaching();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            model = userRepository.getUserById(id);
            this.id = id;
        }

        public Object getModel() {
            return model;
        }
    }