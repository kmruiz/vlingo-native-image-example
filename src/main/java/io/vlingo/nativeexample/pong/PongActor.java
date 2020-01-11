package io.vlingo.nativeexample.pong;

import io.vlingo.actors.Stage;
import io.vlingo.nativeexample.ping.Ping;
import io.vlingo.actors.Actor;

public class PongActor extends Actor implements Pong {
    private int times;
    private Pong self;

    public PongActor() {
        this.times = 0;
        this.self = selfAs(Pong.class);
    }

    public static Pong instanceOn(final Stage stage) {
        return stage.actorFor(Pong.class, PongActor.class, PongActor::new);
    }

    public void pong(final Ping ping) {
        System.out.println("Pong");
        if (this.times > 10) {
            this.stop();
        } else {
            this.times++;
            ping.ping(self);
        }
    }
}
