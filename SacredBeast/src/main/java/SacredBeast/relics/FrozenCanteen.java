package SacredBeast.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import SacredBeast.SB_Mod;
import SacredBeast.util.TextureLoader;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

import static SacredBeast.SB_Mod.makeRelicOutlinePath;
import static SacredBeast.SB_Mod.makeRelicPath;

public class FrozenCanteen extends CustomRelic {

    public static final String ID = SB_Mod.makeID("FrozenCanteen");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FrozenCanteen.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FrozenCanteen.png"));

    public FrozenCanteen() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof RestRoom) {
            this.flash();
            this.addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
        }}

    @Override
    public String getUpdatedDescription() { return DESCRIPTIONS[0]; }

}

