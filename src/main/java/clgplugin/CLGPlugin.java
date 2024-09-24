package clgplugin;


import clgplugin.Items.ItemManager;
import clgplugin.Items.gui.mainmenu.Menu;
import clgplugin.Items.gui.mainmenu.MenuScreenSelector;
import clgplugin.Items.gui.slayermenu.SlayerScreenSelector;
import clgplugin.Items.Weapons.YetiSword;
import clgplugin.events.TestEvents;
import clgplugin.handler.CommandHandler;
import clgplugin.handler.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CLGPlugin extends JavaPlugin {

    private CommandHandler commandHandler;

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
        getServer().getPluginManager().registerEvents(new TestEvents(), this);
        getServer().getPluginManager().registerEvents(new Menu(), this);
        getServer().getPluginManager().registerEvents(new YetiSword(), this);
        getServer().getPluginManager().registerEvents(new MenuScreenSelector(), this);
        getServer().getPluginManager().registerEvents(new SlayerScreenSelector(), this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED +"MSBPlugin disabled.");
        //Leave above
    }

    public void RegisterCommands(){
        this.commandHandler = new CommandHandler(this);
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}