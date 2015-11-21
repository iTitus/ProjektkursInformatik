package projektkurs.simulation.tower;

import java.util.Comparator;

import projektkurs.simulation.tower.entity.Monster;
import projektkurs.util.MathUtil;

public enum TowerAttackMode implements Comparator<Monster> {

    FASTEST {
        @Override
        public int compare(Monster m1, Monster m2) {
            return Double.compare(m1.getSpeed(), m2.getSpeed());
        }
    },
    FIRST {
        @Override
        public int compare(Monster m1, Monster m2) {
            return Integer.compare(m1.getID(), m2.getID());
        }
    },
    LAST {
        @Override
        public int compare(Monster m1, Monster m2) {
            return Integer.compare(m2.getID(), m1.getID());
        }
    },
    RANDOM {
        @Override
        public int compare(Monster m1, Monster m2) {
            return MathUtil.randomInt(-1, 1);
        }
    },
    SLOWEST {
        @Override
        public int compare(Monster m1, Monster m2) {
            return Double.compare(m2.getSpeed(), m1.getSpeed());
        }
    },
    STRONGEST {
        @Override
        public int compare(Monster m1, Monster m2) {
            return Integer.compare(m1.getHealth(), m2.getHealth());
        }
    },
    WEAKEST {
        @Override
        public int compare(Monster m1, Monster m2) {
            return Integer.compare(m2.getHealth(), m1.getHealth());
        }
    };

    @Override
    public abstract int compare(Monster m1, Monster m2);

}
