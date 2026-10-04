package nischitweaker.asm.zenutils;

import meldexun.asmutil2.ASMUtil;
import meldexun.asmutil2.HashMapClassNodeClassTransformer;
import meldexun.asmutil2.IClassTransformerRegistry;
import net.minecraft.launchwrapper.IClassTransformer;
import nischitweaker.config.ConfigHandler;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class ZenUtilsShareTransformer extends HashMapClassNodeClassTransformer implements IClassTransformer {

    @Override
    protected void registerTransformers(IClassTransformerRegistry registry) {
        //Put #mixin Share on the parameter like Local
        if(!ConfigHandler.zenUtils.fixShareAnnotation) return;
        registry.addOptional("youyihj.zenutils.impl.mixin.crafttweaker.MixinParsedZenClassMethod", "generatePlainMixinMethod", ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS, method -> {
            LdcInsnNode local = ASMUtil.first(method).ldcInsn("Local").find();
            MethodInsnNode equals = ASMUtil.nextExclusive(method, local).methodInsn("equals").find();
            ASMUtil.replace(method, equals,
                    new MethodInsnNode(Opcodes.INVOKESTATIC, "nischitweaker/asm/zenutils/ZenUtilsShareTransformer$Hook", "isLocalOrShare", "(Ljava/lang/String;Ljava/lang/Object;)Z", false)
            );
        });
    }

    public static class Hook {
        public static boolean isLocalOrShare(String local, Object annotationName) {
            return local.equals(annotationName) || "Share".equals(annotationName);
        }
    }
}
