// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPig, ItemStack, EntityLiving

public class ItemHolyStaff extends ItemWizardStaff
{
	@Override
	public void onSuccessfulSpell(World world, EntityPlayer entityplayer, int blockX, int blockY, int blockZ)
    {
		EntityGlowingOrb glowingOrb = new EntityGlowingOrb(world, (float)blockX + 0.5F, (float)blockY + 1.5F, (float)blockZ + 0.5F);
        world.entityJoinedWorld(glowingOrb);
    }
}
