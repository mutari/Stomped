package Stomped.Animation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Animation {

    private ArrayList<Frame> frames;
    private int speed = 60; //times sec
    private int index = 0;

    public Animation(ArrayList<Frame> f) {
        this.frames = f;
    }

    public Animation(ArrayList<Frame> f, int speed) {
        this(f);
        this.speed = speed;
    }

    public void run() {

        step(frames.get(index));

        if (index != frames.size() - 1)
            index++;

        new Timer(speed, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                step(frames.get(index));

                if (index != frames.size() - 1)
                    index++;
                else
                    ((Timer)e.getSource()).stop();
            }
        }).start();

        index = 0;

    }

    public void loop() { }

    public abstract void step(Frame frame);


}
