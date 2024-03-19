package gg.pixelarcade.skywars.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.pixelarcade.skywars.util.json.FileTypeAdapter;
import gg.pixelarcade.skywars.util.serialization.*;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.io.File;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:35
 */

public class GsonProvider {

    public static Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(PotionEffect.class, new PotionEffectAdapter())
            .registerTypeHierarchyAdapter(ItemStack.class, new ItemStackAdapter())
            .registerTypeHierarchyAdapter(Location.class, new LocationAdapter())
            .registerTypeHierarchyAdapter(Vector.class, new VectorAdapter())
            .registerTypeAdapter(BlockVector.class, new BlockVectorAdapter())
            .registerTypeAdapter(File.class, new FileTypeAdapter())
            .serializeNulls()
            .setPrettyPrinting()
            .create();

}
