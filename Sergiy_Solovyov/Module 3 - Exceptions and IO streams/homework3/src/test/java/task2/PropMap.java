package task2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 13.03.2016
 */
public class PropMap {

    public static HashMap<String, String> props = new HashMap<String, String>();

    static {
        props.put("message.username", "Por favor ingrese el nombre de usuario");
        props.put("message.password", "Por favor ingrese una clave");
        props.put("message.unauth", "Acceso denegado !!");
        props.put("message.badCredentials", "Usuario o clave invalida");
        props.put("message.sessionExpired", "La sesion expiro");
    }
}
