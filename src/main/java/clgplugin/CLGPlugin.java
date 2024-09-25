package clgplugin;


import clgplugin.Items.ItemManager;
import clgplugin.handler.CommandHandler;
import clgplugin.handler.EventHandler;
import clgplugin.slayer.bosses.customZomb;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class CLGPlugin extends JavaPlugin {

    private CommandHandler commandHandler;
    private EventHandler eventHandler;

    @Override
    public void onEnable() {
        //Server console call
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"[CLGPlugin] Plugin loaded.");
        //Leave above

        //Commands
        RegisterCommands();

        //Items
        ItemManager.initItems();

        //Events

        registerEvents();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED +"MSBPlugin disabled.");
        //Leave above
    }

    public void RegisterCommands(){
        this.commandHandler = new CommandHandler(this);
    }

    public void registerEvents() {
        this.eventHandler = new EventHandler(this);
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
    public void spawnCustomBoss(Location location) {
        customZomb boss = new customZomb(location); // Pass the plugin instance
        boss.spawn(); // Call the boss's spawn method
    }

    public static CLGPlugin getPlugin(){
        return CLGPlugin.getPlugin(CLGPlugin.class);
    }
}