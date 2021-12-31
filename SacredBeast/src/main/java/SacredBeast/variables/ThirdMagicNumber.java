package SacredBeast.variables;

import SacredBeast.cards.AbstractSBCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static SacredBeast.SB_Mod.makeID;

public class ThirdMagicNumber extends DynamicVariable {


    @Override
    public String key() {
        return makeID("3M");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractSBCard) card).isThirdMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractSBCard) card).thirdMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractSBCard) card).baseThirdMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractSBCard) card).upgradedThirdMagicNumber;
    }

}
