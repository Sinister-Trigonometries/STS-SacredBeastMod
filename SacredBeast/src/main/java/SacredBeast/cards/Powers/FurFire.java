package SacredBeast.cards.Powers;

import SacredBeast.SB_Mod;
import SacredBeast.cards.AbstractDynamicCard;
import SacredBeast.powers.FurFirePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static SacredBeast.SB_Mod.makeCardPath;


public class FurFire extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(FurFire.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int PLATED_ARMOR=2;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int PLATED_ARMOR_COST = 1;


    public FurFire() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage=baseDamage=DAMAGE;
        magicNumber=baseMagicNumber=PLATED_ARMOR_COST;
        secondMagicNumber = baseSecondMagicNumber = PLATED_ARMOR;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new PlatedArmorPower(p,secondMagicNumber)));
        addToBot(new ApplyPowerAction(p,p,new FurFirePower(p,p,magicNumber,damage)));

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
