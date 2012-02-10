// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB, 
//            Vec3D, MovingObjectPosition

public class BlockHolyLight extends Block
{

    protected BlockHolyLight()
    {
        super(mod_Classes.getUniqueBlockId(), 0, Material.air);
        setTickOnLoad(true);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 100;
    }

    private boolean func_31032_h(World world, int i, int j, int k)
    {
        return world.isBlockNormalCube(i, j, k) || world.getBlockId(i, j, k) == Block.fence.blockID;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return true;
    }
}
