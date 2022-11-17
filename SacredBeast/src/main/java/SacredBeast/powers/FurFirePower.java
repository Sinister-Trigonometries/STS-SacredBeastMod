package SacredBeast.powers;

import SacredBeast.SB_Mod;
import SacredBeast.cards.AbstractSBCard;
import SacredBeast.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class FurFirePower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SB_Mod.makeID("FurFirePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("SacredBeastResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("SacredBeastResources/images/powers/placeholder_power32.png");

    private int PALoss;
    private AbstractCreature source;
    private int damageAmount;



    public FurFirePower(AbstractCreature owner, final AbstractCreature source, final int PALoss, final int damageAmount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.damageAmount = damageAmount;
        this.source = source;
        this.PALoss=PALoss;
        type = PowerType.BUFF;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (owner.hasPower(PlatedArmorPower.POWER_ID)){
                this.flash();
                this.addToBot(new ReducePowerAction(owner,source,PlatedArmorPower.POWER_ID,PALoss));
                this.addToBot(new DamageAllEnemiesAction((AbstractPlayer) source, damageAmount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.damageAmount += stackAmount;
        this.PALoss++;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.PALoss + DESCRIPTIONS[1] + this.damageAmount + DESCRIPTIONS[2];
    }


    @Override
    public AbstractPower makeCopy() {
        return new FurFirePower(owner,source,PALoss,damageAmount);
    }
}
