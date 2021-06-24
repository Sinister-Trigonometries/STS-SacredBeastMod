
package SacredBeast.patches;

import SacredBeast.SB_Mod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import javassist.CtBehavior;

//credit to JohnnyBazooka89. i borrowed code from his TheDrunkenSailorPatch

@SpirePatch(clz = PotionPopUp.class, method = "updateInput")
@SpirePatch(clz = PotionPopUp.class, method = "updateTargetMode")
public class PotionUsedPatch {

    @SpireInsertPatch(locator = Locator.class, localvars = {"potion"})
    public static void Insert(PotionPopUp potionPopUp, AbstractPotion potion) {
        SB_Mod.potionsUsed++;
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior method) throws Exception {
            Matcher matcher = new Matcher.MethodCallMatcher(AbstractPotion.class, "use");
            return LineFinder.findInOrder(method, matcher);
        }
    }

}
