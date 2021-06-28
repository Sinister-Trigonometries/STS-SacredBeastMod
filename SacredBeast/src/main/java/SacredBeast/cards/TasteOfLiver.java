package SacredBeast.cards;

import SacredBeast.SB_Mod;
import SacredBeast.actions.ExhaustTopCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static SacredBeast.SB_Mod.makeCardPath;


public class TasteOfLiver extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(TasteOfLiver.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 3;
    private static final int DAMAGE = 30;
    private static final int UPGRADE_PLUS_DMG = 10;


    public TasteOfLiver() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new DamageAction(
                        m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ExhaustTopCardAction(p, 5));
        if (p.hasPower(PlatedArmorPower.POWER_ID)) {
            int platedArmor = p.getPower(PlatedArmorPower.POWER_ID).amount;
            addToBot(new HealAction(p, p, platedArmor / 2));
            addToBot(new RemoveSpecificPowerAction(p, p, PlatedArmorPower.POWER_ID));
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
