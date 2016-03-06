package projektkurs.entity;

import java.util.ArrayList;
import java.util.List;

import projektkurs.entity.behaviour.BehaviourFollowPath;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.Sprites;
import projektkurs.render.Sprite;
import projektkurs.util.Pair;
import projektkurs.world.Spielfeld;

public class EntityPathFollower extends Entity {

    private int spriteIndex;

    public EntityPathFollower(Spielfeld map) {
        super(map);
    }

    public EntityPathFollower(Spielfeld map, int posX, int posY, int spriteIndex, int ticksPerMove, int... is) {
        super(map, posX, posY, new Sprite[1]);
        if (is == null || is.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < is.length - 1; i += 2) {
            list.add(new Pair<Integer, Integer>(is[i], is[i + 1]));
        }
        addBehaviour(new BehaviourFollowPath(this, ticksPerMove, list));
    }

    @Override
    public Sprite getSprite() {
        // int facing = MathUtil.floorDiv(getFacing().getIndex(), 2);
        switch (spriteIndex) {
            case 0:
                return Sprites.boy;
            case 1:
                return Sprites.fisher;
            case 2:
                return Sprites.lordvO_N;
            case 3:
                return Sprites.teddydefault;
            case 4:
                return Sprites.women0_N;
            case 5:
                return Sprites.women0_N;
            case 6:
                return Sprites.frauV_N;
            case 7:
                return Sprites.car_lord_frauV_N;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        spriteIndex = data.getInteger("spriteIndex");
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set("spriteIndex", spriteIndex);
    }

}
