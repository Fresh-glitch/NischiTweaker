package nischitweaker.mixins.incontrol;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import mcjty.incontrol.rules.EntityModCache;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityModCache.class)
public abstract class EntityModCacheMixin {
    // old handling matched all entities to forge -> minecraft due to curseforge being in the absolute path of the jar... bruh
    @WrapOperation(
            method = "getMod",
            at = @At(value = "INVOKE", target = "Lmcjty/tools/varia/Tools;findModID(Ljava/lang/Object;)Ljava/lang/String;"),
            remap = false
    )
    private String fixGettingModId(Object obj, Operation<String> original){
        @SuppressWarnings("unchecked")
        ResourceLocation loc = EntityList.getKey((Class<? extends Entity>) obj);
        return loc == null ? "null" : loc.getNamespace();
    }
}
