package gg.pixelarcade.skywars.scoreboard.api;

import gg.pixelarcade.skywars.scoreboard.api.events.AssembleBoardCreateEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class Assemble {

	private final JavaPlugin plugin;

	private AssembleAdapter adapter;
	private AssembleThread thread;
	private AssembleListener listeners;
	private AssembleStyle assembleStyle = AssembleStyle.MODERN;

	private Map<UUID, AssembleBoard> boards;

	private long ticks = 2;
	private boolean hook = false, debugMode = true, callEvents = true;
	private final ChatColor[] chatColorCache = ChatColor.values();

	/**
	 * Assemble.
	 *
	 * @param plugin instance.
	 * @param adapter that is being provided.
	 */
	public Assemble(JavaPlugin plugin, AssembleAdapter adapter) {
		if (plugin == null) {
			throw new RuntimeException("Assemble can not be instantiated without a plugin instance!");
		}

		this.plugin = plugin;
		this.adapter = adapter;
		this.boards = new ConcurrentHashMap<>();

		this.setup();
	}

	/**
	 * Setup Assemble.
	 */
	public void setup() {
		// Register Events.
		this.listeners = new AssembleListener(this);
		this.plugin.getServer().getPluginManager().registerEvents(listeners, this.plugin);

		// Ensure that the thread has stopped running.
		if (this.thread != null) {
			this.thread.stop();
			this.thread = null;
		}

		// Register new boards for existing online players.
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {

			// Call Events if enabled.
			if (this.callEvents) {
				AssembleBoardCreateEvent createEvent = new AssembleBoardCreateEvent(player);

				Bukkit.getPluginManager().callEvent(createEvent);
				if (createEvent.isCancelled()) {
					continue;
				}
			}

			boards.putIfAbsent(player.getUniqueId(), new AssembleBoard(player, this));
		}

		// Start Thread.
		this.thread = new AssembleThread(this);
	}

	/**
	 * Cleanup Assemble.
	 */
	public void cleanup() {
		// Stop thread.
		if (this.thread != null) {
			this.thread.stop();
			this.thread = null;
		}

		// Unregister listeners.
		if (listeners != null) {
			HandlerList.unregisterAll(listeners);
			listeners = null;
		}

		// Destroy player scoreboards.
		for (UUID uuid : boards.keySet()) {
			Player player = Bukkit.getPlayer(uuid);

			if (player == null || !player.isOnline()) {
				continue;
			}

			boards.remove(uuid);
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public AssembleAdapter getAdapter() {
		return adapter;
	}

	public AssembleThread getThread() {
		return thread;
	}

	public AssembleListener getListeners() {
		return listeners;
	}

	public AssembleStyle getAssembleStyle() {
		return assembleStyle;
	}

	public Map<UUID, AssembleBoard> getBoards() {
		return boards;
	}

	public long getTicks() {
		return ticks;
	}

	public boolean isHook() {
		return hook;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public boolean isCallEvents() {
		return callEvents;
	}

	public ChatColor[] getChatColorCache() {
		return chatColorCache;
	}

	public void setAdapter(AssembleAdapter adapter) {
		this.adapter = adapter;
	}

	public void setThread(AssembleThread thread) {
		this.thread = thread;
	}

	public void setListeners(AssembleListener listeners) {
		this.listeners = listeners;
	}

	public void setAssembleStyle(AssembleStyle assembleStyle) {
		this.assembleStyle = assembleStyle;
	}

	public void setBoards(Map<UUID, AssembleBoard> boards) {
		this.boards = boards;
	}

	public void setTicks(long ticks) {
		this.ticks = ticks;
	}

	public void setHook(boolean hook) {
		this.hook = hook;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public void setCallEvents(boolean callEvents) {
		this.callEvents = callEvents;
	}
}
