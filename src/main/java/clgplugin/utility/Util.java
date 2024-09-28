package clgplugin.utility;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Util {

    public static Vector getFromTo(Location locFrom, Location locTo) {
        return locTo.toVector().subtract(locFrom.toVector());
    }
}
