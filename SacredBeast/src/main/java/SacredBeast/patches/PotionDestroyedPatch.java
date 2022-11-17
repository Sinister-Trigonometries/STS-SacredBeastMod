
package SacredBeast.patches;

import SacredBeast.SB_Mod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;


@SpirePatch(
        clz = TopPanel.class,
        method = "destroyPotion"
)
public class PotionDestroyedPatch {

    //@SpirePrefixPatch
    public static SpireReturn<Void> Prefix(TopPanel __instance, int slot)
    {
        if(slot==-128) {
            return SpireReturn.Return();
        }
        else{
            return SpireReturn.Continue();
        }
    }


}
