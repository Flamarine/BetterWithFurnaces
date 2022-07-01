package io.github.pkstdev.bwf.util;

import net.minecraft.src.TileEntity;

import java.lang.reflect.Method;

public class TileEntityHelper {
    private static Method addMappingMethod;

    static {
        try {
            Method[] methods = TileEntity.class.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("addMapping")) {
                    if (method.getParameterCount() == 2) {
                        addMappingMethod = method;
                    }
                }
            }
            if (addMappingMethod == null) throw new RuntimeException("Could not find addMapping method!");
            addMappingMethod.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerTileEntityClass(Class class1, String s) {
        try {
            addMappingMethod.invoke(null, class1, s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
