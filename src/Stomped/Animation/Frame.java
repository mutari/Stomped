package Stomped.Animation;

import Toolbox.Position;

public class Frame {

    public Position moveTo;
    public double[] vars;

    public Frame(double[] v) {
        this.vars = v;
    }

    public Frame(Position moveTo) {
        this.moveTo = moveTo;
    }

}
