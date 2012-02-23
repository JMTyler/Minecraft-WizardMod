// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

import paulscode.sound.Vector3D;

// Referenced classes of package net.minecraft.src:
//            EntityLiving, IMob, DataWatcher, NBTTagCompound, 
//            MathHelper, AxisAlignedBB, World, DamageSource, 
//            EntityPlayer, Item, Chunk

public class EntityGlowingOrb extends EntitySlime
    implements IMob
{
	protected Vector3D _lastLit = new Vector3D(0, 0, 0);
	
    public EntityGlowingOrb(World world)
    {
        super(world);
        preventEntitySpawning = true;
        //yOffset = height / 2.0F;
    }

    public EntityGlowingOrb(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1, d2);
        /*float f = (float)(Math.random() * 3.1415927410125732D * 2D);
        motionX = -MathHelper.sin((f * 3.141593F) / 180F) * 0.02F;
        motionY = 0.20000000298023224D;
        motionZ = -MathHelper.cos((f * 3.141593F) / 180F) * 0.02F;*/
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }
    
    public EntityGlowingOrb(World world, double d, double d1, double d2, boolean test)
    {
    	this(world, d, d1, d2);
    	this.displayMsg = test;
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	boolean hasMoved = (int)_lastLit.x != (int)posX && (int)_lastLit.y != (int)posY && (int)_lastLit.z != (int)posZ;
    	//boolean hasMoved = (int)prevPosX != (int)posX && (int)prevPosY != (int)posY && (int)prevPosZ != (int)posZ;
    	
    	if (!this.isDead && hasMoved) {
    		worldObj.func_35459_c(EnumSkyBlock.Block, (int)_lastLit.x, (int)_lastLit.y, (int)_lastLit.z);
    		worldObj.setLightValue(EnumSkyBlock.Block, (int)posX, (int)posY, (int)posZ, 15);
    		worldObj.func_35459_c(EnumSkyBlock.Block, (int)posX + 1, (int)posY, (int)posZ);

    		_lastLit = new Vector3D((float)posX, (float)posY, (float)posZ);
    		
    		if (this.displayMsg) {
        		//float myLight = worldObj.getLightBrightness(x, y, z);
        		//float theirLight = worldObj.getLightBrightness(x + 1, y, z);
        		//ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(myLight + " vs " + theirLight);
        	}
    	}
    }
    
    @Override
    public void onEntityDeath()
    {
    	super.onEntityDeath();
    	
    	worldObj.func_35459_c(EnumSkyBlock.Block, (int)posX, (int)posY, (int)posZ);
    }
    
    public void moveEntityWithHeading(float f, float f1)
    {
    	
    }
    
    protected boolean displayMsg = false;
}
