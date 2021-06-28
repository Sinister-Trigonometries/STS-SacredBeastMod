package SacredBeast.util;

import SacredBeast.patches.DefaultInsertPatch;
import SacredBeast.patches.RefundFieldPatch;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

//https://github.com/daviscook477/BaseMod/wiki/CardModifiers here's an explanation of modifiers

public class AbExtraResource {
    private static final Logger logger = LogManager.getLogger(AbExtraResource.class.getName()); // This is our logger! It prints stuff out in the console.



    public AbExtraResource() {
    }

    public static AbstractCard returnTrulyRandomXCardInCombat() {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var1 = AbstractDungeon.srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.cost==-1 && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        var1 =  AbstractDungeon.srcUncommonCardPool.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.cost==-1 && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        var1 =  AbstractDungeon.srcRareCardPool.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c.cost==-1 && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }
        logger.info(list.toString());
        return (AbstractCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }


    public static class XRefundModifier extends AbstractCardModifier {

        private String originaldescription;

        public XRefundModifier(){
        }
        @Override
        public boolean removeOnCardPlayed(AbstractCard card) { // purges effect when card is played
            return true;
        }
        @Override
        public boolean removeAtEndOfTurn(AbstractCard card) { // purges effect when turn ends.
            return true;
        }
        @Override
        public String identifier(AbstractCard card){
            return "XRefundModifier";
        }
        @Override
        public String modifyDescription(String rawDescription, AbstractCard card) { //adds text to the description
            originaldescription = card.rawDescription;
            return rawDescription+ " NL Refund X";
        }
        @Override
        public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) { // makes it give you energy when the card is played
            RefundFieldPatch.refund.set(card,card.energyOnUse);
        }
        @Override
        public void onRemove(AbstractCard card){
            card.rawDescription = originaldescription;
            RefundFieldPatch.refund.set(card,0);
        }

        @Override
        public AbstractCardModifier makeCopy() {
            return new XRefundModifier();
        }
    }
}

