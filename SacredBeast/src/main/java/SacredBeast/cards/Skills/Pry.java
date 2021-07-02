package SacredBeast.cards.Skills;

import SacredBeast.SB_Mod;
import SacredBeast.cards.AbstractDynamicCard;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static SacredBeast.SB_Mod.makeCardPath;


public class Pry extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(Pry.class.getSimpleName());
    public static final String IMG = makeCardPath("SB_UNCOMMON_SKILL.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 2;
    private static final int PLATED_ARMOR = 4;
    private static final int VULNERABLE = 2;
    private static final int UPGRADE_PLUS_VN = 2;

    public Pry() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = PLATED_ARMOR;
        baseSecondMagicNumber = secondMagicNumber = VULNERABLE;
        exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ApplyPowerAction(p,p, new PlatedArmorPower(p,magicNumber)));
        addToBot(
                new ApplyPowerAction(m,p, new VulnerablePower(m,secondMagicNumber,false)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeSecondMagicNumber(UPGRADE_PLUS_VN);
            initializeDescription();
        }
    }
}
