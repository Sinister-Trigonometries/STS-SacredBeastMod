package SacredBeast.cards;

import SacredBeast.SB_Mod;
import SacredBeast.actions.EasyXCostAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;

import static SacredBeast.SB_Mod.makeCardPath;


public class Dissect extends AbstractDynamicCard {

    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(Dissect.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 1;
    private static final int UPGRADED_COST=-1;
    private static final int DAMAGE = 5;


    public Dissect() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        isMultiDamage = true;
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // may be fussy with ChemX

        if(upgraded){
            addToBot(new EasyXCostAction(this, (effect, params) -> {
                for (int i = 0; i < effect + 1; i++) {
                    addToBot(new DamageRandomEnemyAction
                            (new DamageInfo(p, damage, damageTypeForTurn),
                                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }
                return true;
            }));
        }
        else {
            for (int i = 0; i < 2; i++)
                addToBot(new DamageRandomEnemyAction
                        (new DamageInfo(p, damage, damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            rawDescription=UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
    @Override
    public void upgradeName(){
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = "Exsect+";
        this.initializeTitle();
    }
}
