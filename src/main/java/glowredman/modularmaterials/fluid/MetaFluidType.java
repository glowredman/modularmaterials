package glowredman.modularmaterials.fluid;

import java.util.function.Consumer;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.client.ClientHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;

public class MetaFluidType extends FluidType {
	
	private final MM_Material material;
	private final String typeName;

	public MetaFluidType(MM_Material material, MM_Type type, String typeName) {
		super(getProperties(material, type));
		this.material = material;
		this.typeName = typeName;
	}
	
	private static Properties getProperties(MM_Material material, MM_Type type) {
		return Properties.create()
				.density(material.fluid.propertiesOfState(type.state).density)
				.lightLevel(material.fluid.propertiesOfState(type.state).luminosity)
				.rarity(material.item.rarity.get())
				.temperature(material.fluid.getTemperature(material.state, type.state))
				.viscosity(material.fluid.propertiesOfState(type.state).viscosity)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH);
	}
	
	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			
			@Override
			public ResourceLocation getFlowingTexture() {
				return new ResourceLocation(MM_Reference.MODID, "block/" + material.texture + "/" + typeName + "_flowing");
			}
			
			@Override
			public ResourceLocation getStillTexture() {
				return new ResourceLocation(MM_Reference.MODID, "block/" + material.texture + "/" + typeName + "_still");
			}
			
			/**
			 * Done via {@link ClientHandler#colorItems(RegisterColorHandlersEvent.Item)}.
			 */
			@Override
			public int getTintColor() {
				return material.color.getARGB();
			}
		});
	}

}
