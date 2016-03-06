package projektkurs.entity.behaviour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projektkurs.entity.Entity;
import projektkurs.util.MathUtil;
import projektkurs.util.Pair;

public class BehaviourFollowPath extends Behaviour {

    protected final List<Pair<Integer, Integer>> pathNodes;
    protected int currentIndex;
    protected boolean didX;
    private final int ticksPerMove;
    private int counter;

    public BehaviourFollowPath(Entity entity, int ticksPerMove, List<Pair<Integer, Integer>> pathNodes) {
        super(entity);
        this.pathNodes = new ArrayList<Pair<Integer, Integer>>(pathNodes);
        currentIndex = 0;
        didX = false;
        this.ticksPerMove = ticksPerMove;
        counter = MathUtil.nextInt(ticksPerMove);
    }

    @SafeVarargs
    public BehaviourFollowPath(Entity entity, int ticksPerMove, Pair<Integer, Integer>... pathNodes) {
        this(entity, ticksPerMove, Arrays.asList(pathNodes));
    }

    @Override
    public boolean isCompatibleWith(Behaviour behaviour) {
        return false;
    }

    @Override
    public void update() {

        counter++;
        counter %= ticksPerMove;
        if (pathNodes.isEmpty() || counter % ticksPerMove != 0) {
            return;
        }

        Pair<Integer, Integer> destination = pathNodes.get(currentIndex);
        int destX = destination.getValueA();
        int destY = destination.getValueB();

        int x = entity.getPosX();
        int y = entity.getPosY();

        int difX = MathUtil.signum(destX - x);
        int difY = MathUtil.signum(destY - y);

        if (didX && difY != 0) {
            difX = 0;
        }
        if (!didX && difX != 0) {
            difY = 0;
        }

        if (difX != 0 || difY != 0) {
            entity.moveBy(difX, difY);
            didX = !didX;
        }

        if (entity.getPosX() == destX && entity.getPosY() == destY) {
            step();
        }
    }

    protected void step() {
        currentIndex++;
        currentIndex %= pathNodes.size();
    }

}
