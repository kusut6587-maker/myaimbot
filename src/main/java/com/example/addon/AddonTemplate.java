package com.example.addon;

import com.example.addon.modules.CustomAimbot;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class AddonTemplate extends MeteorAddon {
    @Override
    public void onInitialize() {
        System.out.println("Custom Aimbot Eklentisi Yuklendi!");
        Modules.get().add(new CustomAimbot());
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
