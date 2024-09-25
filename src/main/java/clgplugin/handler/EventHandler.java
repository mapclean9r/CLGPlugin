package clgplugin.handler;

import clgplugin.CLGPlugin;
import clgplugin.commands.ModCommand;
import clgplugin.events.annotations.EventAlias;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventHandler{

    private final List<Class<?>> eventClasses = new ArrayList<>();
    private final CLGPlugin plugin;
    public EventHandler(CLGPlugin clgPlugin) {
        plugin = clgPlugin;
        registerEvents();
        LoadConsoleMessage();
    }

    private void LoadConsoleMessage(){
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"[CLGPlugin] Events loaded.");
    }

    private void registerEvents() {
        Reflections reflections = new Reflections("clgplugin.events");
        Reflections reflections2 = new Reflections("clgplugin.Items");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(EventAlias.class);
        Set<Class<?>> classes2 = reflections2.getTypesAnnotatedWith(EventAlias.class);
        eventClasses.addAll(classes);
        eventClasses.addAll(classes2);
        for (Class<?> clazz : eventClasses){
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
