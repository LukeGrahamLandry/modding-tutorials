package ca.lukegrahamlandry.firstmod.mixin;

import com.mojang.blaze3d.platform.MacosUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;

@Mixin(MacosUtil.class)
public class M1FixCocoa {
    @Inject(at = @At("HEAD"), method = "loadIcon", cancellable = true)
    private static void loadIcon(InputStream intbuffer1, CallbackInfo ci) {
        if (isAppleSlilicon()) ci.cancel();
    }

    private static boolean isAppleSlilicon() {
        return System.getProperty("os.arch").equals("aarch64") && System.getProperty("os.name").equals("Mac OS X");
    }
}
