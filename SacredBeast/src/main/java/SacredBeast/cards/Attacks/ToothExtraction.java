package SacredBeast.cards.Attacks;

import SacredBeast.SB_Mod;
import SacredBeast.actions.ToothExtractionAction;
import SacredBeast.cards.AbstractDynamicCard;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.CunningPotion;

import java.util.ArrayList;
import java.util.List;

import static SacredBeast.SB_Mod.makeCardPath;


public class ToothExtraction extends AbstractDynamicCard {


    //TEXT DECLARATION 1
    public static final String ID = SB_Mod.makeID(ToothExtraction.class.getSimpleName());
    public static final String IMG = makeCardPath("SB_RARE_ATTACK.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    ArrayList<TooltipInfo> customtips = new ArrayList<>();


    //STATS DECLARATION 1
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    //STATS DECLARATION 2
    private static final int COST = 2;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;


    public ToothExtraction() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new ToothExtractionAction(
                        m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
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


    @Override
    public List<TooltipInfo> getCustomTooltips () {
        if (customtips.isEmpty()){
            AbstractPotion pot = new CunningPotion();
            pot.initializeData();
            customtips.add(
                    new TooltipInfo(pot.name, pot.description));
        }
        return customtips;
    }


}
