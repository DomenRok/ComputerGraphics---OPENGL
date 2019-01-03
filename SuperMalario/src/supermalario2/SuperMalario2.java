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
import entities.Enemy;
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
        Generator generator = new Generator(window);
        
        
        
        
        
       
        ModelData data = OBJFileLoader.loadOBJ("dragon");
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel textureModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = textureModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(10);
        
        Entity entity = new Entity(textureModel, new Vector3f(0, 0, -30), 0, 0, 0, 1f);
        Entity entity2 = Generator.generateObject("houseA", "houseTexture", loader, new Vector3f(5,0,5), 0.05f);
        Enemy flag = generator.generateEnemy(window, "flag", "fire", loader, new Vector3f(180, 0, 45), 0.1f, 5);
        Player player = new Player(window, textureModel, new Vector3f(0, 0, 0), 0, 0, 0, 0.2f);
        Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("bob")));
        
        
        Light light = new Light(new Vector3f(30, 20, 0), new Vector3f(1,1,1));
        Camera camera = new Camera(window, player);
        window.linkCamera(camera);
        

        
        Random random = new Random();
        List<Enemy> enemies = generator.generateWave(random);
        
        MasterRenderer renderer = new MasterRenderer(window);
        while (!window.closed()) {
            window.update();
            player.move();
            camera.move();
            
            double unluckyCoin = Math.random();
            if (unluckyCoin <= 0.1) enemies.add(toolbox.Generator.generateEnemy(window, "sphere", "fire", loader, new Vector3f(((float)Math.random() * 180 ) + 10, 2 + (float) Math.random() * 10, 5), 1f, 1.3f));
            
            for (Enemy enemy: enemies) {
                enemy.move();
                if (enemy.intersects(player)) {
                    player.setPosition(0, 0, 0);
                }
                renderer.processEntity(enemy);    
            }
            
            flag.increaseRotation(0, 0.5f, 0);
            if (flag.intersects(player)) player.setPosition(0, 50, 10);
            
                   
            renderer.processEntity(flag);
            renderer.processEntity(player);
            renderer.processTerrain(terrain);
            renderer.processEntity(entity);
            renderer.processEntity(entity2);
            renderer.render(light, camera);
            window.swapBuffers();
        }
        
        renderer.cleanUp();
        loader.CleanUp();
        window.closeWindow();
    }   
}
