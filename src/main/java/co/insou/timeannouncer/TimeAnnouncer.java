package co.insou.timeannouncer;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TimeAnnouncer extends JavaPlugin {

    private final List<Announcer> announcers = Lists.newArrayList();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.announcers.add(new Announcer(this.getConfig().getLong("night.tick"), this.getConfig().getString("night.message")));
        this.announcers.add(new Announcer(this.getConfig().getLong("day.tick"), this.getConfig().getString("day.message")));

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Announcer announcer : TimeAnnouncer.this.announcers) {
                        if (world.getTime() == announcer.getTick()) {
                            for (Player player : world.getPlayers()) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', announcer.getMessage()));
                            }
                        }
                    }
                }
            }
        }, 1, 1);
    }

    @Override
    public void onDisable() {

    }

}
