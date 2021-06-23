package SacredBeast.cards;

import SacredBeast.SB_Mod;
import SacredBeast.actions.BrewColorPotionAction;
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
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.potions.LiquidMemories;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.List;

import static SacredBeast.SB_Mod.makeCardPath;


public class RabidEssence extends AbstractDynamicCard{

    // TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(RabidEssence.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // STAT DECLARATION 1
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    // STAT DECLARATION 2
    private static final int COST = 1;
    private static final int DAMAGE = 5; // Damage never upgrades so we only need damage here.
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MN = 1;


    public RabidEssence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber=MAGIC_NUMBER;
        purgeOnUse = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, DAMAGE, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE)); //change attack effect later
        for (int i =0; i<MAGIC_NUMBER;i++){
            AbstractDungeon.actionManager.addToBottom(
                new BrewColorPotionAction(
                        BrewColorPotionAction.PotionColor.RED));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MN);  // TODO: Upgrade does not work
            rawDescription=UPGRADE_DESCRIPTION;
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

