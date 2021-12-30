package SacredBeast.cards.Skills;

import SacredBeast.cards.AbstractDynamicCard;
import SacredBeast.characters.SB_Character;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static SacredBeast.SB_Mod.makeCardPath;
import static SacredBeast.SB_Mod.makeID;

public class SB_Defend extends AbstractDynamicCard {

    //TEXT DECLARATION 1
    public static final String ID = makeID(SB_Defend.class.getSimpleName());
    public static final String IMG = makeCardPath("SB_BASIC_SKILL.png");

    // TEXT DECLARATION 2
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // STAT DECLARATION 1
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = SB_Character.Enums.COLOR_WHITE;

    // STAT DECLARATION 2
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    public SB_Defend() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock= block = BLOCK;
        this.tags.add(CardTags.STARTER_DEFEND);
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }
}
