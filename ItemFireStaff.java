// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPig, ItemStack, EntityLiving

public class ItemFireStaff extends ItemWizardStaff
{
	@Override
	public void onSuccessfulSpell(World world, EntityPlayer entityplayer, int blockX, int blockY, int blockZ)
    {
		int r = 2;
		
		world.playSoundEffect((double)blockX + 0.5D, (double)blockY + 0.5D, (double)blockZ + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		
		for (int x = blockX - r; x <= blockX + r; x++) {
			for (int z = blockZ - r; z <= blockZ + r; z++) {
				float inverseDistance = (float) Math.abs(Math.abs(blockX - x) - r) / (float) r; // max is 1.0
				if ((itemRand.nextFloat() + inverseDistance) >= 0.75) {
					int y = world.getTopSolidOrLiquidBlock(x, z);
					world.setBlockWithNotify(x, y, z, Block.fire.blockID);
				}
			}
		}
    }
}
