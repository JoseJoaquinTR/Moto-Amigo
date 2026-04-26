package com.mycompany.motoamigopersistencia;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.motoamigodto.UbicacionDTO;
import java.io.*;
import java.net.*;
import java.util.*;

public class UbicacionDAO implements IUbicacionDAO {

    private static UbicacionDAO instancia;

    private UbicacionDAO() {
    }

    public static synchronized UbicacionDAO getInstancia() {
        if (instancia == null) {
            instancia = new UbicacionDAO();
        }
        return instancia;
    }

    private static final String TOKEN = "pk.eyJ1IjoiY3ViaXhhIiwiYSI6ImNtbnFzb2h2ZjBiMHoyc240ajN1MmE1YzUifQ.q-1JIZNmttY9LI5vE_aD7w";
    private static final String URL_BASE = "https://api.mapbox.com/geocoding/v5/mapbox.places/";

    @Override
    public List<UbicacionDTO> buscarUbicacion(String query) throws Exception {
        List<UbicacionDTO> lista = new ArrayList<>();

        String urlStr = URL_BASE
                + URLEncoder.encode(query, "UTF-8")
                + ".json?access_token=" + TOKEN
                + "&language=es"
                + "&country=MX"
                + "&limit=5";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        if (conn.getResponseCode() != 200) {
            return lista;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            sb.append(linea);
        }
        reader.close();

        // Parseo con Gson
        JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
        JsonArray features = json.getAsJsonArray("features");

        for (int i = 0; i < features.size(); i++) {
            JsonObject feature = features.get(i).getAsJsonObject();
            JsonArray coords = feature.getAsJsonObject("geometry")
                    .getAsJsonArray("coordinates");

            UbicacionDTO dto = new UbicacionDTO(coords.get(1).getAsDouble(),coords.get(0).getAsDouble(),feature.get("place_name").getAsString());
            lista.add(dto);
        }

        return lista;
    }
}
