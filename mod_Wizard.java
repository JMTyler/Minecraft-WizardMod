package net.minecraft.src;

import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

public class mod_Wizard extends BaseMod
{
	public static final String armorGroup = "wizard";
	
	public static final Block holyLight;
	
	public static Item lightningStaff;
	public static Item explosiveStaff;
	public static Item fireStaff;
	public static Item holyStaff;
	public static Item teleportStaff;
	
    public static Item wizardHat;
    public static Item wizardRobe;
    public static Item wizardPantaloons;
    public static Item wizardBooties;
	
	
	static
	{
		// don't need this anymore but haven't tested removing it yet
		holyLight = (new BlockHolyLight()).setBlockName("holyLight")
				.setHardness(0.0F)
				.setResistance(0.0F)
				.setLightValue(1.0F)
				.setRequiresSelfNotify();
		
		if (mod_Classes.addArmorGroup(armorGroup)) {
			wizardHat = mod_Classes.addArmor("wizardHat", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, ArmorType.Helmet, 0));
			wizardRobe = mod_Classes.addArmor("wizardRobe", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, ArmorType.Plate, 0));
			wizardPantaloons = mod_Classes.addArmor("wizardPantaloons", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, ArmorType.Legs, 0));
			wizardBooties = mod_Classes.addArmor("wizardBooties", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, ArmorType.Boots, 0));
			
			if (wizardHat != null) {
				mod_Classes.addWeapon("lightningStaff", new ItemLightningStaff()).dependsOn((ItemClassArmor)wizardHat);
				mod_Classes.addWeapon("explosiveStaff", new ItemExplosiveStaff()).dependsOn((ItemClassArmor)wizardHat);
				mod_Classes.addWeapon("fireStaff", new ItemFireStaff()).dependsOn((ItemClassArmor)wizardHat);
				mod_Classes.addWeapon("holyStaff", new ItemHolyStaff()).dependsOn((ItemClassArmor)wizardHat);
				mod_Classes.addWeapon("teleportStaff", new ItemTeleportationStaff()).dependsOn((ItemClassArmor)wizardHat);
			}
		}
	}
	
	public mod_Wizard()
	{
		/* Setting up blocks */
		ModLoader.RegisterBlock(holyLight);
		
		
		/* Setting up items */
		ModLoader.AddName(lightningStaff, "Lightning Staff");
		ModLoader.AddName(explosiveStaff, "Explosive Staff");
		ModLoader.AddName(fireStaff, "Fire Staff");
		ModLoader.AddName(holyStaff, "Holy Staff");
		ModLoader.AddName(teleportStaff, "Teleportation Staff");
		ModLoader.AddName(wizardHat, "Wizard's Hat");
		
		lightningStaff.iconIndex = ModLoader.addOverride("/gui/items.png", "/gui/wizardStaff.png");
		explosiveStaff.iconIndex = lightningStaff.iconIndex;
		fireStaff.iconIndex = lightningStaff.iconIndex;
		holyStaff.iconIndex = lightningStaff.iconIndex;
		teleportStaff.iconIndex = lightningStaff.iconIndex;
		
		
		/* Setting up entities */
		ModLoader.RegisterEntityID(EntityGlowingOrb.class, "glowingorb", mod_Classes.getUniqueEntityId());
		
		
		/* Setting up recipes */
		ModLoader.AddRecipe(new ItemStack(lightningStaff, 1), new Object[] {
			"X", "Y", "Y", Character.valueOf('X'), Item.redstone, Character.valueOf('Y'), Item.bone
		});
		ModLoader.AddRecipe(new ItemStack(explosiveStaff, 1), new Object[] {
			"X", "Y", "Y", Character.valueOf('X'), Item.gunpowder, Character.valueOf('Y'), Item.bone
		});
		ModLoader.AddRecipe(new ItemStack(fireStaff, 1), new Object[] {
			"X", "Y", "Y", Character.valueOf('X'), Item.flintAndSteel, Character.valueOf('Y'), Item.bone
		});
		ModLoader.AddRecipe(new ItemStack(holyStaff, 1), new Object[] {
			"X", "Y", "Y", Character.valueOf('X'), Item.lightStoneDust, Character.valueOf('Y'), Item.bone
		});
		ModLoader.AddRecipe(new ItemStack(teleportStaff, 1), new Object[] {
			"X", "Y", "Y", Character.valueOf('X'), Item.field_35416_bo, Character.valueOf('Y'), Item.bone
		});
	}
	
	@Override
	public void AddRenderer(Map map)
	{
		map.put(EntityGlowingOrb.class, new RenderSlime(new ModelSlime(1), new ModelSlime(1), 0.5F));
	}
	
	@Override
	public String Version()
	{
		return null;
	}
}
