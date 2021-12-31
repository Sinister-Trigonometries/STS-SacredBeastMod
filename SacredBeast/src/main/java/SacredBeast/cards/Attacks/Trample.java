package SacredBeast.cards.Attacks;

import SacredBeast.SB_Mod;
import SacredBeast.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static SacredBeast.SB_Mod.makeCardPath;

public class Trample extends AbstractDynamicCard {

    //NOTE: TRAMPLE DOES NOT WORK
    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(Trample.class.getSimpleName());
    public static final String IMG = makeCardPath("SB_COMMON_ATTACK.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 2;
    private static final int DAMAGE = 16;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int PLATED_ARMOR_COST=3;

    public Trample() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber=baseMagicNumber=PLATED_ARMOR_COST;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new DamageAction(m,new DamageInfo(m,0,damageTypeForTurn)));
        if (PayPlatedArmor(p,PLATED_ARMOR_COST)) {
            addToBot(
                    new DamageAllEnemiesAction(
                            p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
        else{
            addToBot(
                    new DamageAction(m,new DamageInfo(m,damage,damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(PlatedArmorPower.POWER_ID) && AbstractDungeon.player.getPower(PlatedArmorPower.POWER_ID).amount > PLATED_ARMOR_COST) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);    // If there is damage to upgrade
            initializeDescription();
        }
    }
}
