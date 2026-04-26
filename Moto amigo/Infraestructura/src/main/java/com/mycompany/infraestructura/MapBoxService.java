package com.mycompany.infraestructura;

import com.mycompany.Entidades.Ubicacion;
import com.mycompany.motoamigodto.RutaResponseDTO;
import com.mycompany.motoamigodto.UbicacionDTO;
import com.mycompany.motoamigopersistencia.ISeguimientoEntregaDAO;
import com.mycompany.motoamigopersistencia.SeguimientoEntregaDAO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jxmapviewer.viewer.GeoPosition;


public class MapBoxService implements IMapBoxService {

    private ISeguimientoEntregaDAO dao = SeguimientoEntregaDAO.getInstance();
    private static final Logger LOGGER = Logger.getLogger(MapBoxService.class.getName());
    private static final String TOKEN = "pk.eyJ1IjoiY2FybWVuLWxhcmEiLCJhIjoiY21vOWkxdzcxMDl2NDJxcHQzbzhhM3pobSJ9.zMCAIOhXugIWywpDTGS2bQ";
    private static MapBoxService instancia;

    private MapBoxService() {
    }

    public static synchronized MapBoxService getInstance() {
        if (instancia == null) {
            instancia = new MapBoxService();
        }
        return instancia;
    }

    @Override
    public RutaResponseDTO obtenerRuta(String origen, String destino) {
        try {
            if (origen == null || origen.isBlank() || destino == null || destino.isBlank()) {
                LOGGER.severe("Origen o destino vacío");
                return new RutaResponseDTO(origen, destino, null, null, null, null,
                        0.0, 0, 0.0, false, false);
            }

            GeoPosition posOrigen = geocodificar(origen);
            GeoPosition posDestino = geocodificar(destino);

            if (posOrigen == null || posDestino == null) {
                LOGGER.severe("No se pudieron obtener coordenadas");
                return new RutaResponseDTO(origen, destino, null, null, null, null, 
                        0.0, 0, 0.0, false, false);
            }

            String url = "https://api.mapbox.com/directions/v5/mapbox/driving/"
                    + posOrigen.getLongitude() + "," + posOrigen.getLatitude()
                    + ";" + posDestino.getLongitude() + "," + posDestino.getLatitude()
                    + "?geometries=geojson&access_token=" + TOKEN;

            String json = llamarServicio(url);
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            if (!obj.has("routes") || obj.getAsJsonArray("routes").isEmpty()) {
                LOGGER.severe("Mapbox no devolvió rutas");
                return new RutaResponseDTO(origen, destino,
                        posOrigen.getLatitude(), posOrigen.getLongitude(),
                        posDestino.getLatitude(), posDestino.getLongitude(),
                        0.0, 0, 0.0, true, false);
            }
            JsonObject route = obj.getAsJsonArray("routes").get(0).getAsJsonObject();
            JsonArray coordinates = route.getAsJsonObject("geometry").getAsJsonArray("coordinates");

            List<double[]> coords = new ArrayList<>();
            for (int i = 0; i < coordinates.size(); i++) {
                JsonArray coord = coordinates.get(i).getAsJsonArray();
                coords.add(new double[]{coord.get(0).getAsDouble(), coord.get(1).getAsDouble()});
            }

            MapboxRouteDTO mapboxRoute = new MapboxRouteDTO(
                    route.get("distance").getAsDouble(),
                    route.get("duration").getAsDouble(),
                    coords,
                    posOrigen.getLatitude(), posOrigen.getLongitude(),
                    posDestino.getLatitude(), posDestino.getLongitude(),
                    origen, destino
            );
            return new AdapterMapboxRouteARutaResponseDTO().adaptar(mapboxRoute);

        } catch (IOException | JsonParseException  ex) {
            return new RutaResponseDTO(origen, destino, null, null, null, null, 0.0, 0, 0.0, false, false);
        } catch (org.apache.hc.core5.http.ParseException ex) {
            Logger.getLogger(MapBoxService.class.getName()).log(Level.SEVERE, null, ex);
            return new RutaResponseDTO(origen, destino, null, null, null, null, 0.0, 0, 0.0, false, false);
        }
    }

    @Override
    public UbicacionDTO obtenerSiguienteUbicacion() {
        Ubicacion entidad = dao.obtenerSiguiente();
        if (entidad == null) {
            return null;
        }

        return new UbicacionDTO(
                entidad.getLatitud(),
                entidad.getLongitud(),
                entidad.getDescripcion()
        );
    }

    @Override
    public boolean comprobarSiFinalizoRuta() {
        return SeguimientoEntregaDAO.getInstance().esUltimoPunto();
    }

    private GeoPosition geocodificar(String direccion) {
        try {
            String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/"
                    + URLEncoder.encode(direccion, StandardCharsets.UTF_8)
                    + ".json?access_token=" + TOKEN;

            String json = llamarServicio(url);
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            if (!obj.has("features") || obj.getAsJsonArray("features").isEmpty()) {
                LOGGER.severe("No se encontraron coordenadas para: " + direccion);
                return null;
            }

            JsonObject first = obj.getAsJsonArray("features").get(0).getAsJsonObject();
            JsonArray center = first.getAsJsonArray("center");

            double lng = center.get(0).getAsDouble();
            double lat = center.get(1).getAsDouble();

            return new GeoPosition(lat, lng);

        } catch (Exception ex) {
            LOGGER.severe("Error al geocodificar dirección: " + direccion + " -> " + ex.getMessage());
            return null;
        }
    }

    private String llamarServicio(String url) throws IOException, org.apache.hc.core5.http.ParseException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getCode() != 200) {
                    LOGGER.severe("Error HTTP al llamar a Mapbox: código " + response.getCode());
                    return "{}"; 
                }
                return EntityUtils.toString(response.getEntity());
            }
        }
    }

    private double calcularCosto(double distanciaKm) {
        double tarifaBase = 20.0;
        double costoPorKm = 5.0;
        return tarifaBase + (distanciaKm * costoPorKm);
    }
}
