package gg.pixelarcade.skywars.region

import com.google.common.base.Charsets
import com.google.common.collect.ImmutableSet
import com.google.common.io.Files
import com.google.gson.reflect.TypeToken
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin
import gg.pixelarcade.skywars.region.listeners.RegionListener
import gg.pixelarcade.skywars.region.selection.RegionSelection
import gg.pixelarcade.skywars.region.selection.listeners.RegionSelectionListener
import gg.pixelarcade.skywars.util.GsonProvider

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import java.io.File
import java.io.IOException

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region
 * @date 19/10/2023 / 15:49
 */

class RegionHandler {
    private val regions: MutableMap<String, Region> = HashMap()

    init {
        val folder: File = SkyWarsLobbyPlugin.instance.dataFolder
        val regionFile = File(folder, "regions.json")

        try {
            if (regionFile.exists()) {
                Files.newReader(regionFile, Charsets.UTF_8).use { regionFileReader ->
                    val regionListType = object : TypeToken<List<Region?>?>() {}.type
                    val regionList = GsonProvider.GSON.fromJson<List<Region>>(regionFileReader, regionListType)
                    for (region in regionList) {
                        regions[region.name!!] = region
                    }
                }
            }
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }

        Bukkit.getPluginManager().registerEvents(RegionListener(this), SkyWarsLobbyPlugin.instance)
        Bukkit.getPluginManager().registerEvents(RegionSelectionListener(this), SkyWarsLobbyPlugin.instance)
    }

    fun saveRegions() {
        try {
            Files.write(
                GsonProvider.GSON.toJson(regions.values),
                File(SkyWarsLobbyPlugin.instance.dataFolder, REGION_FILE),
                Charsets.UTF_8
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun getRegion(location: Location): Region? {
        for (region in regions.values) {
            if (region.bounds.contains(location)) {
                return region
            }
        }

        return null
    }

    fun getRegions(): Set<Region> {
        return ImmutableSet.copyOf(regions.values)
    }

    fun addRegion(region: Region) {
        regions[region.name!!] = region
    }

    fun removeRegion(region: Region) {
        regions.remove(region.name)
    }

    fun getRegion(regionName: String): Region? {
        return regions[regionName]
    }

    fun getSelection(player: Player): RegionSelection {
        if (player.hasMetadata(RegionSelection.SELECTION_WAND_KEY)) return player.getMetadata(
            RegionSelection.SELECTION_WAND_KEY
        )[0].value() as RegionSelection

        return createSelection(player)
    }

    fun createSelection(player: Player): RegionSelection {
        val selection = RegionSelection()
        player.setMetadata(
            RegionSelection.SELECTION_WAND_KEY,
            FixedMetadataValue(SkyWarsLobbyPlugin.instance, selection)
        )
        return selection
    }

    companion object {
        private const val REGION_FILE = "regions.json"
    }
}
