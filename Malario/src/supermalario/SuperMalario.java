
package supermalario;

import com.sun.javafx.geom.Vec3f;
import engine.io.Window;
import engine.render.Renderer;
import engine.render.models.ModelEntitiy;
import engine.render.models.TexturedModel;
import org.lwjgl.glfw.GLFW;
import shaders.StaticShader;

public class SuperMalario {
    public static final int WIDTH=800, HEIGHT=600,FPS=60;
    public static Window window = new Window(WIDTH, HEIGHT, FPS, "Malario");
    public static StaticShader shader = new StaticShader();
    public static Renderer renderer = new Renderer(shader, window);
    
    public static void main(String[] args) {
        window.create();
        window.setBackgroundColor(0.0f, 0.0f, 0.0f);
        shader.create();
        renderer.create();
        
        TexturedModel model = new TexturedModel(new float[] {
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f
            
        }, new float[] {
            0, 0,
            1, 0,
            1, 1,
            0, 1
        }, new int[] {
            0, 1, 2,
            2, 3, 0
        }, "beautiful.png");
        // (0,0,0) za pravilno delovanje brez unga
        ModelEntitiy entity = new ModelEntitiy(model, new Vec3f(0, 0, 0), new Vec3f(0,0,0), new Vec3f(1,1,1));
        while (!window.closed()) {
            if (window.isUpdating()) {
                window.update();
                shader.bind();
                shader.useMatrices();
                renderer.renderModelEntitiy(entity);
                shader.unbind();
                window.swapBuffers();
            }
        }
        
        shader.remove();
        model.remove();
        window.stop();
    }    

}