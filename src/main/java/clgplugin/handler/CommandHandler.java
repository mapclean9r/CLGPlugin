package clgplugin.handler;

import clgplugin.CLGPlugin;
import clgplugin.commands.ModCommand;
import clgplugin.commands.annotations.CommandAlias;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CommandHandler implements CommandExecutor {

    private final List<Class<?>> commandClasses = new ArrayList<>();
    private final CLGPlugin plugin;

    public CommandHandler(CLGPlugin clgPlugin){
        plugin = clgPlugin;
        registerCommands();
    }

    private void registerCommands(){
        Reflections reflections = new Reflections("clgplugin.commands");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CommandAlias.class);
        for (Class<?> clazz : classes){
            Objects.requireNonNull(plugin.getCommand(clazz.getAnnotation(CommandAlias.class).alias())).setExecutor(this);
            commandClasses.add(clazz);
        }
        LoadConsoleMessage();
    }

    private void LoadConsoleMessage(){
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"[CLGPlugin] Commands loaded.");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player player)){
            commandSender.sendMessage(command.getName()+"is not a console command");
            return true;
        }
        for (Class<?> clazz : commandClasses){
            if (command.getName().equals(clazz.getAnnotation(CommandAlias.class).alias())){
                try {
                    ModCommand e = (ModCommand) clazz.getDeclaredConstructor().newInstance();
                    e.execute(commandSender, s, strings);
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                         IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return false;
    }
}
