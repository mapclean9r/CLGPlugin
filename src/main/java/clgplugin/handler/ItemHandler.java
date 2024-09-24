package clgplugin.handler;

import clgplugin.CLGPlugin;
import clgplugin.Items.ModItem;
import clgplugin.Items.annotations.ItemName;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemHandler {

    private List<Class<?>> itemClasses = new ArrayList<>();
    private CLGPlugin plugin;
    private List<ItemStack> items;
    public ItemHandler(CLGPlugin clgPlugin){
        items = new ArrayList<>();
        registerItems();
    }

    private void registerItems(){
        Reflections reflections = new Reflections("clgplugin.Items");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ItemName.class);
        itemClasses.addAll(classes);

        for (Class<?> clazz : itemClasses){
                try {
                    ModItem e = (ModItem) clazz.getDeclaredConstructor().newInstance();
                    plugin.getServer().getConsoleSender().sendMessage(e+"");
                    items.add(e.getItemStack());
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                         IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
        }
    }

    public List<ItemStack> getItems() {
        return items;
    }
}
