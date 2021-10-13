package Stomped.Animation;

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

        long compareTime = 1000000000/speed;
        long lastFrameTime = 0;

        while(true) {

            long now = System.nanoTime();

            if((now - lastFrameTime) >= compareTime) {

                System.out.println(now + "-" + lastFrameTime + "=" + (now + - + lastFrameTime));
                step(frames.get(index));
                if(++index >= frames.size())
                    break;

                lastFrameTime = now;
            }

        }

        index = 0;

    }

    public void loop() { }

    public abstract void step(Frame frame);


}
