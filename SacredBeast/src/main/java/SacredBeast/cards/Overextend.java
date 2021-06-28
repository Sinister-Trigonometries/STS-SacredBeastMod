package SacredBeast.cards;

import SacredBeast.SB_Mod;
import SacredBeast.actions.EasyXCostAction;
import SacredBeast.actions.ExhaustTopCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;

import static SacredBeast.SB_Mod.makeCardPath;


public class Overextend extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(Overextend.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = -1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 4;


    public Overextend() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        isMultiDamage=true;
        exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                addToBot(
                        new DamageAction(
                                m, new DamageInfo(p, damage, damageTypeForTurn),
                                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                addToBot(new ExhaustTopCardAction(p, 1));

            }
            return true;
        }));

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
