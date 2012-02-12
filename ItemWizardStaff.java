// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPig, ItemStack, EntityLiving

public abstract class ItemWizardStaff extends ItemClassWeapon
{

    public ItemWizardStaff()
    {
        super(mod_Classes.getUniqueItemId());
    }
    
    public abstract void onSuccessfulSpell(World world, EntityPlayer entityplayer, int blockX, int blockY, int blockZ);
    
    protected boolean _attemptSpell(World world, EntityPlayer entityplayer, int blockX, int blockY, int blockZ)
    {
    	// check if we have enough hunger to cast a spell
		if (_canAffordSpell(entityplayer)) {
            //entityplayer.func_35191_at().func_35771_a(-5, -0.6F); // don't remember what this does
    		onSuccessfulSpell(world, entityplayer, blockX, blockY, blockZ);
    		return true;
		} else {
	        world.playSoundAtEntity(entityplayer, "random.fizz", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	        return false;
		}
    }
    
    protected boolean _canAffordSpell(EntityPlayer entityplayer)
    {
    	return entityplayer.func_35191_at().func_35765_a() >= 5;
    }
    
    @Override
    public boolean onSuccessfulUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
    	super.onSuccessfulUse(itemstack, entityplayer, world, i, j, k, l);

		entityplayer.swingItem();
		
    	return _attemptSpell(world, entityplayer, i, j, k);
    }
    
    @Override
    public void onSuccessfulRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	super.onSuccessfulRightClick(itemstack, world, entityplayer);
    	
    	double d = 64F;
    	MovingObjectPosition objectMouseOver = ModLoader.getMinecraftInstance().renderViewEntity.rayTrace(d, 1.0F);
    	if (objectMouseOver != null) {
    		entityplayer.swingItem();
    		
    		_attemptSpell(world, entityplayer, objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ);
    	}
    }
    
    @Override
    public void onFailedRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	super.onFailedRightClick(itemstack, world, entityplayer);
    	
    	entityplayer.attackEntityFrom(new EntityDamageSource("player", entityplayer), 6);
		entityplayer.damageEntity(6);
    }
}
