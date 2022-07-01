package io.github.pkstdev.bwf.util;

import net.minecraft.src.CraftingManager;
import net.minecraft.src.ItemStack;

import java.lang.reflect.Method;

/**
 * @author Bestsoft100
 */
public class RecipeHelper {
    private static Method addRecipeMethod;

    static {
        try {
            Method[] methods = CraftingManager.class.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("addRecipe")) {
                    if (method.getParameterCount() == 2) {
                        addRecipeMethod = method;
                    }
                }
            }
            if (addRecipeMethod == null) throw new RuntimeException("Could not find addRecipe method!");
            addRecipeMethod.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addRecipe(ItemStack result, Object... recipe) {
        try {
            addRecipeMethod.invoke(CraftingManager.getInstance(), result, recipe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
