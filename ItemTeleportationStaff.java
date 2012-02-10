// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPig, ItemStack, EntityLiving

public class ItemTeleportationStaff extends ItemWizardStaff
{
	public ItemTeleportationStaff()
	{
		super(mod_Classes.getUniqueItemId());
	}

	public void onSuccessfulSpell(World world, EntityPlayer entityplayer, int blockX, int blockY, int blockZ)
    {
		entityplayer.setPosition(blockX + 0.5F, blockY + 3.5F, blockZ + 0.5F);
    }
}
