package net.minecraft.src;

import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

public class mod_Wizard extends BaseMod
{
	public static final String armorGroup = "wizard";
	
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
		if (mod_Classes.addArmorGroup(armorGroup)) {
			wizardHat = mod_Classes.addArmor("wizardHat", "Wizard's Hat", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, EnumArmorType.Helmet, 0));
			wizardRobe = mod_Classes.addArmor("wizardRobe", "Robe of Mists", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, EnumArmorType.Plate, 0));
			wizardPantaloons = mod_Classes.addArmor("wizardPantaloons", "Magical Pantaloons", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, EnumArmorType.Legs, 0));
			wizardBooties = mod_Classes.addArmor("wizardBooties", "Lil' Booties", new ItemClassArmor(mod_Classes.getUniqueItemId(), armorGroup, EnumArmorType.Boots, 0));
			
			if (wizardHat != null) {
				lightningStaff = mod_Classes.addWeapon("lightningStaff", "Lightning Staff", new ItemLightningStaff()).dependsOn((ItemClassArmor)wizardHat);
				explosiveStaff = mod_Classes.addWeapon("explosiveStaff", "Explosive Staff", new ItemExplosiveStaff()).dependsOn((ItemClassArmor)wizardHat);
				fireStaff = mod_Classes.addWeapon("fireStaff", "Fire Staff", new ItemFireStaff()).dependsOn((ItemClassArmor)wizardHat);
				holyStaff = mod_Classes.addWeapon("holyStaff", "Staff of Holy Light", new ItemHolyStaff()).dependsOn((ItemClassArmor)wizardHat);
				teleportStaff = mod_Classes.addWeapon("teleportStaff", "Staff of Teleportation", new ItemTeleportationStaff()).dependsOn((ItemClassArmor)wizardHat);
			}
		}
	}
	
	public mod_Wizard()
	{
		/* Setting up item icons */
		// TODO: move the icon override into the ItemClassArmor and ItemClassWeapon constructors
		lightningStaff.iconIndex = ModLoader.addOverride("/gui/items.png", "/gui/wizardStaff.png");
		explosiveStaff.iconIndex = lightningStaff.iconIndex;
		fireStaff.iconIndex = lightningStaff.iconIndex;
		holyStaff.iconIndex = lightningStaff.iconIndex;
		teleportStaff.iconIndex = lightningStaff.iconIndex;
		wizardHat.iconIndex = ModLoader.addOverride("/gui/items.png", "/gui/wizardHat.png");
		
		
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
		
		
		/* Setting up entities */
		ModLoader.RegisterEntityID(EntityGlowingOrb.class, "glowingorb", mod_Classes.getUniqueEntityId());
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
