package io.vlingo.nativeexample;

import io.vlingo.nativeexample.ping.Ping;
import io.vlingo.nativeexample.ping.PingActor;
import io.vlingo.nativeexample.pong.Pong;
import io.vlingo.actors.World;
import io.vlingo.nativeexample.pong.PongActor;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        World world = World.startWithDefaults("ping-pong-native");
        Ping ping = PingActor.instanceOn(world.stage());
        Pong pong = PongActor.instanceOn(world.stage());

        ping.ping(pong);
        Thread.sleep(1000);
        world.terminate();
    }
}
