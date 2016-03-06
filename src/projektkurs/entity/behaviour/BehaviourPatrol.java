package projektkurs.entity.behaviour;

import java.util.List;

import projektkurs.entity.Entity;
import projektkurs.util.Pair;

public class BehaviourPatrol extends BehaviourFollowPath {

    private boolean dir = true;

    public BehaviourPatrol(Entity entity, int ticksPerMove, List<Pair<Integer, Integer>> pathNodes) {
        super(entity, ticksPerMove, pathNodes);
    }

    public BehaviourPatrol(Entity entity, int ticksPerMove, Pair<Integer, Integer>[] pathNodes) {
        super(entity, ticksPerMove, pathNodes);
    }

    @Override
    protected void step() {
        if (currentIndex >= pathNodes.size() - 1) {
            dir = false;
            currentIndex = pathNodes.size() - 1;
        }
        if (currentIndex <= 0) {
            dir = true;
            currentIndex = 0;
        }

        if (dir) {
            currentIndex++;
        } else {
            currentIndex--;
        }
    }

}
