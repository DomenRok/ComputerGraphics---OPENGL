/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermalario2;

import Models.ModelData;
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
import renderEngine.io.Window;
import terrains.Terrain;
import textures.ModelTexture;

/**
 *
 * @author Domen Brunček
 */
public class SuperMalario {
    private static final int width = 1024, height = 768;
   
    public static void main(String[] args) {
        Window window = new Window(width, height, "Malario");
        window.create();        
        Loader loader = new Loader();
        Generator generator = new Generator(window);
        
        
        
        
        
       
        ModelData data = OBJFileLoader.loadOBJ("dragon");
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel textureModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = textureModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(10);
        
        
        
        Entity tower = generator.generateObject("tower", "fire", loader, new Vector3f(0, 0, 45), 1f);
        Entity misc = generator.generateObject("tower", "white", loader, new Vector3f(-90, 0, 10), 0.4f);
        Entity entity = Generator.generateObject("houseA", "houseTexture", loader, new Vector3f(5,0,5), 0.05f);
        Enemy flag = generator.generateEnemy(window, "flag", "fire", loader, new Vector3f(180, 0, 45), 0.1f, 5);
        
        Player player = new Player(window, textureModel, new Vector3f(0, 0,45), 0, 270, 0, 0.2f);
        Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("bob")), 180);
        Terrain victoryTerrain = new Terrain(-3, 0, loader, new ModelTexture(loader.loadTexture("bulls")), 40);
        
        Light light = new Light(new Vector3f(90, 40, 45), new Vector3f(1,1,1));
        Camera camera = new Camera(window, player);
        window.linkCamera(camera);
        

        Random random = new Random();
        List<Enemy> enemies = new ArrayList<>();
        for (int i=0; i < 6; i++) generator.addWave(random, enemies);
        
        MasterRenderer renderer = new MasterRenderer(window);
        while (!window.closed()) {
            window.update();
            player.move();
            camera.move();
            tower.increaseRotation(0, 0.1f, 0);
            
            double unluckyCoin = Math.random();
            if (unluckyCoin <= 0.001) generator.addWave(random, enemies);
            for (Enemy enemy: enemies) {
                enemy.move();
                if (enemy.intersects(player)) player.setPosition(0, 0, 45);
                renderer.processEntity(enemy);
            }
        
            flag.increaseRotation(0, 0.5f, 0);
            if (flag.intersects(player)) player.setPosition(-80, 150, 0);
            
            
            renderer.processEntity(misc);
            renderer.processEntity(flag);
            renderer.processEntity(player);
            renderer.processTerrain(terrain);
            renderer.processEntity(tower);
            renderer.processTerrain(victoryTerrain);
            renderer.processEntity(entity);
            renderer.render(light, camera);
            window.swapBuffers();
        }
        
        renderer.cleanUp();
        loader.CleanUp();
        window.closeWindow();
    }   
}
