import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class World extends Pane {
    private AnimationTimer timer;
    private HashSet<KeyCode> codes;

    World() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                for (Node actor: getChildren()) {
                    ((Actor)actor).act(now);
                }
            }
        };
    }
    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }
    public void add(Actor actor) {
        getChildren().add(actor);
    }
    public void remove(Actor actor){
        getChildren().remove(actor);
    }
    
    public void addKeyCode(KeyCode code) {
    	codes.add(code);
    }
    
    public void removeKeyCode(KeyCode code) {
    	codes.remove(code);
    }
    
    public boolean isKeyDown(KeyCode code) {
    	//there is something wrong with this line of code, not sure what it is
    	return codes.contains(code);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls){
        ArrayList<A> list =  new ArrayList<A>();
        for (Node actor: getChildren()) {
            if (cls.isInstance(actor)) {
                list.add(cls.cast(actor));
            }
        }
        return list;
    }
    public abstract void act(long now);
}
