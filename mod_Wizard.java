package net.minecraft.src;

import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;

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

	protected boolean _isPlayerTeleporting = false;
	
	
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
		
		
		ModLoader.SetInGameHook(this, true, true);
	}
	
	@Override
	public void AddRenderer(Map map)
	{
		map.put(EntityGlowingOrb.class, new RenderSlime(new ModelSlime(1), new ModelSlime(1), 0.5F));
	}
	
	@Override
	public boolean OnTickInGame(float f, Minecraft minecraft)
	{
		super.OnTickInGame(f, minecraft);
		
		EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
		if (mod_Classes.checkDependency(player, (ItemClassArmor)wizardRobe)) {
			if (!_isPlayerTeleporting && player.health <= 4) {
		        double d1 = (player.posX + (player.rand.nextDouble() - 0.5D) * 32D);
		        double d2 = (player.posY + (double)(player.rand.nextInt(16) - 8));
		        double d3 = (player.posZ + (player.rand.nextDouble() - 0.5D) * 32D);
		        _isPlayerTeleporting = _teleportPlayer(player, d1, d2, d3);
			} else if (_isPlayerTeleporting && player.health > 4) {
				_isPlayerTeleporting = false;
			}
		}
		return true;
	}

    protected boolean _teleportPlayer(EntityPlayer player, double d, double d1, double d2)
    {
    	World worldObj = ModLoader.getMinecraftInstance().theWorld;
        double d3 = player.posX;
        double d4 = player.posY;
        double d5 = player.posZ;
        player.posX = d;
        player.posY = d1;
        player.posZ = d2;
        boolean flag = false;
        int i = MathHelper.floor_double(player.posX);
        int j = MathHelper.floor_double(player.posY - 1);
        int k = MathHelper.floor_double(player.posZ);
        if(worldObj.blockExists(i, j, k))
        {
            boolean flag1;
            for(flag1 = false; !flag1 && j > 0;)
            {
                int i1 = worldObj.getBlockId(i, j - 1, k);
                if(i1 == 0 || !Block.blocksList[i1].blockMaterial.getIsSolid())
                {
                	player.posY--;
                    j--;
                } else
                {
                    flag1 = true;
                }
            }

            if(flag1)
            {
            	boolean flag2;
                for(flag2 = false; !flag2 && j > 0;)
                {
                	player.setPosition(player.posX, player.posY, player.posZ);
                    if(worldObj.getCollidingBoundingBoxes(player, player.boundingBox).size() == 0)
                    {
                        flag2 = true;
                    } else {
                    	player.posY++;
                        j++;
                    }
                }
                
                if (flag2) {
                	flag = true;
                }
            }
        }
        if(!flag)
        {
        	player.setPosition(d3, d4, d5);
            return false;
        }
        int l = 128;
        for(int j1 = 0; j1 < l; j1++)
        {
            double d6 = (double)j1 / ((double)l - 1.0D);
            float f = (player.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (player.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (player.rand.nextFloat() - 0.5F) * 0.2F;
            double d7 = d3 + (player.posX - d3) * d6 + (player.rand.nextDouble() - 0.5D) * (double)player.width * 2D;
            double d8 = d4 + (player.posY - d4) * d6 + player.rand.nextDouble() * (double)player.height - 1.0D;
            double d9 = d5 + (player.posZ - d5) * d6 + (player.rand.nextDouble() - 0.5D) * (double)player.width * 2D;
            worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
        }

        return true;
    }
	
	@Override
	public String Version()
	{
		return null;
	}
}
