/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

/**
 *
 * @author joset
 */
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class OSMTileFactoryCustom extends DefaultTileFactory {

    public OSMTileFactoryCustom() {
        super(new TileFactoryInfo(
                "OSM",
                1, 17, 17,
                256, true, true,
                "https://tile.openstreetmap.org",
                "x", "y", "z") {
            @Override
            public String getTileUrl(int x, int y, int zoom) {
                int z = 17 - zoom;
                return "https://tile.openstreetmap.org/" + z + "/" + x + "/" + y + ".png";
            }
        });
    }
}
