package lt.traveladvisor.mvp.general.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JaxbAnnotationModule());

    public static <T> T buildObjectFromJson(String body, Class<T> clazz) {
        try {
            return objectMapper.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(
                    "Failed deserialization during json processing, can't evaluate trip advise, please submit application again...",
                    Response.Status.INTERNAL_SERVER_ERROR
            );
        }
    }
}
