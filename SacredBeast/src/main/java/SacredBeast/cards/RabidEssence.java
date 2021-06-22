package SacredBeast.cards;

import SacredBeast.SB_Mod;
import SacredBeast.characters.SB_Character;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FirePotion;
import com.megacrit.cardcrawl.potions.LiquidMemories;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.List;

import static SacredBeast.SB_Mod.makeCardPath;


public class RabidEssence extends AbstractDynamicCard{


    public static final String ID = SB_Mod.makeID(RabidEssence.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    private static final int COST = 1;
    private static final int DAMAGE = 5; // Damage never upgrades so we only need damage here.

    private static final int BASE_POTION_NUMBER = 1;
    private static int POTION_NUMBER = BASE_POTION_NUMBER; //generates 1 (2) potions.
    private static final int UPGRADE_PLUS_POTION_NUMBER = 2;


    public RabidEssence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        purgeOnUse = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, DAMAGE, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE)); //change attack effect later
        for (int i =0; i<POTION_NUMBER;i++){
            AbstractDungeon.actionManager.addToBottom(
                new ObtainPotionAction(
                        new FirePotion()));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            POTION_NUMBER=UPGRADE_PLUS_POTION_NUMBER;
            initializeDescription();
        }
    }
    //List<TooltipInfo> customtips;


    //Come back here later.
    /*@Override
    public List<TooltipInfo> getCustomtips () {
        if (customtips==null){
            AbstractPotion pot = new FirePotion();
            pot.initializeData();
            customtips.add(
                    new TooltipInfo(pot.name, pot.description));
        }
        return customtips;
    }
    */
}

