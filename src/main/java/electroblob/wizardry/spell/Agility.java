package electroblob.wizardry.spell;

import electroblob.wizardry.EnumElement;
import electroblob.wizardry.EnumParticleType;
import electroblob.wizardry.EnumSpellType;
import electroblob.wizardry.EnumTier;
import electroblob.wizardry.Wizardry;
import electroblob.wizardry.WizardryUtilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Agility extends Spell {

	public Agility() {
		super(EnumTier.APPRENTICE, 50, EnumElement.SORCERY, "agility", EnumSpellType.UTILITY, 583, EnumAction.bow, false);
	}

	@Override
	public boolean cast(World world, EntityPlayer caster, int ticksInUse, float damageMultiplier, float rangeMultiplier, float durationMultiplier, float blastMultiplier) {

		caster.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, (int)(600*durationMultiplier), 1, true));
		caster.addPotionEffect(new PotionEffect(Potion.jump.id, (int)(600*durationMultiplier), 1, true));
		
		for(int i=0; i<10; i++){
			double x1 = (double)((float)caster.posX + world.rand.nextFloat()*2 - 1.0F);
			double y1 = (double)((float)WizardryUtilities.getPlayerEyesPos(caster) - 0.5F + world.rand.nextFloat());
			double z1 = (double)((float)caster.posZ + world.rand.nextFloat()*2 - 1.0F);
			if(world.isRemote){
				Wizardry.proxy.spawnParticle(EnumParticleType.SPARKLE, world, x1, y1, z1, 0, 0.1F, 0, 48 + world.rand.nextInt(12), 0.6f, 0.6f, 1.0f);
			}
		}
		
		world.playSoundAtEntity(caster, "wizardry:heal", 0.7F, world.rand.nextFloat() * 0.4F + 1.0F);
		return true;
	}


}
