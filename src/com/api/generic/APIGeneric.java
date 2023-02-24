package com.api.generic;

import com.api.clases.ClaseGlobal;
import com.api.herramientas.Utilitarios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class APIGeneric extends ClaseGlobal {

    Utilitarios objUtilitarios = new Utilitarios();
    
    //Invocando un servicio rest
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    //Convierte el Objeto Json en Objetos Java
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T Listar(TypeReference<T> references, String endpoint) {
        try {
            HttpResponse<String> response = ReturnBody(endpoint);
            //Ignorará todas las propiedades que no estén declaradas en una entidad.
            this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (response.statusCode() == 200) {
                return this.mapper.readValue(response.body(), references);
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean Guardar(Map<Object, Object> objeto, String endpoint) {
        try {
            String requestBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objeto);
            HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(requestBody)).uri(URI.create(this.api + endpoint)).setHeader("Content-type", "application/json").build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objUtilitarios.RespuestasHttpBoolean(response.statusCode());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Actualizar(Map<Object, Object> objeto, String endpoint) {
        try {
            String requestBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objeto);
            HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(requestBody)).uri(URI.create(this.api + endpoint)).setHeader("Content-type", "application/json").build();
            System.out.println(requestBody);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objUtilitarios.RespuestasHttpBoolean(response.statusCode());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Eliminar(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(this.api + endpoint)).setHeader("Content-type", "application/json").build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objUtilitarios.RespuestasHttpBoolean(response.statusCode());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private HttpResponse ReturnBody(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(this.api + endpoint)).build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

}
