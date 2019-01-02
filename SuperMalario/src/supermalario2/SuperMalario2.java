/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermalario2;

import Models.ModelData;
import org.lwjgl.glfw.GLFW;
import renderEngine.Loader;
import Models.RawModel;
import Models.TexturedModel;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjglx.util.vector.Vector3f;
import toolbox.Generator;
import renderEngine.MasterRenderer;
import renderEngine.OBJFileLoader;
import renderEngine.EntityRenderer;
import renderEngine.io.Window;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

/**
 *
 * @author Domen Brunček
 */
public class SuperMalario2 {
    private static final int width = 800, height = 600;
    
    public static void main(String[] args) {
        Window window = new Window(width, height, "Jebeno");
        window.create();        
        Loader loader = new Loader();
                
        ModelData data = OBJFileLoader.loadOBJ("dragon");
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(10);
        
        Entity entity = new Entity(staticModel, new Vector3f(0, 0, -30), 0, 0, 0, 1f);
        Entity entity2 = Generator.generateObject("houseA", "houseTexture", loader, new Vector3f(5,0,5), 0.05f);
        
        
        Light light = new Light(new Vector3f(5, 5, 5), new Vector3f(1,1,1));
        Camera camera = new Camera(window);
        
        Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("white")));
        
        
        
        Player player = new Player(window, staticModel, new Vector3f(0, 0, -10), 0, 0, 0, 0.1f);
        
        
        
        MasterRenderer renderer = new MasterRenderer(window);
        while (!window.closed()) {
            entity.increaseRotation(0.01f, 0.03f, 0.5f);
            camera.move();
            player.move();
            
            renderer.processEntity(player);
            renderer.processTerrain(terrain);
            renderer.processEntity(entity);
            renderer.processEntity(entity2);
            renderer.render(light, camera);
            window.update();
            window.swapBuffers();
        }
        
        renderer.cleanUp();
        loader.CleanUp();
        window.closeWindow();
    }
}
