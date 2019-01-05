/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox;

import Models.ModelData;
import Models.RawModel;
import Models.TexturedModel;
import entities.Enemy;
import entities.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.OBJFileLoader;
import renderEngine.io.Window;
import textures.ModelTexture;

/**
 *
 * @author Domen Brunček
 */
public class Generator {
    private Window window;
    
    private static String[] textures = {"fire", "bob", "grass", "toxic"};

    public Generator(Window window) {
        this.window = window;
    }
    
    
    public static RawModel generateCube() {
        Loader loader = new Loader();
        float[] vertices = {            
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                0.5f,-0.5f,0,   
                0.5f,0.5f,0,        
                 
                -0.5f,0.5f,1,   
                -0.5f,-0.5f,1,  
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                0.5f,0.5f,0,    
                0.5f,-0.5f,0,   
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                -0.5f,-0.5f,1,  
                -0.5f,0.5f,1,
                 
                -0.5f,0.5f,1,
                -0.5f,0.5f,0,
                0.5f,0.5f,0,
                0.5f,0.5f,1,
                 
                -0.5f,-0.5f,1,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1
                 
        };
         
        float[] textureCoords = {
                 
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0
 
                 
        };
         
        int[] indices = {
                0,1,3,  
                3,1,2,  
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,   
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
 
        };
        
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        /*TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("bob")));
        
        Entity entity = new Entity(staticModel, new Vector3f(-2, 0, -1), 0, 0, 0, 1);
        */
        return model;
    }
    
    public static Entity generateKvadrat() {
        
        float[] vertices = {
            -0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0f
        };
        
        int[] indices = {
            0,1,3,
            3,1,2
        };
        
        float[] textureCoords = {
           0,0,
           0,1,
           1,1,
           1,0
        };
        Loader loader = new Loader();
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("bob")));
        
        Entity entity = new Entity(staticModel, new Vector3f(-2, 0, -1), 0, 0, 0, 1);
        return entity;
    }
    
    public static Entity generateObject(String objModel, String textureFile, Loader loader, Vector3f position, float scale) {
        ModelData data =  OBJFileLoader.loadOBJ(objModel);
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture(textureFile)));
        ModelTexture texture = staticModel.getTexture();
        //texture.setReflectivity(0.5f);
        //texture.setShineDamper(10);
        Entity entity = new Entity(staticModel, position , 0, 0, 0, scale);
        return entity;
    }
    
    public static Enemy generateEnemy(Window window, String objModel, String textureFile, Loader loader, Vector3f position, float scale, float hitBox) {
        ModelData data =  OBJFileLoader.loadOBJ(objModel);
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture(textureFile)));
        ModelTexture texture = staticModel.getTexture();
        Enemy enemy = new Enemy(window, staticModel, position , 0, 0, 0, scale);
        enemy.setHitBox(hitBox);
        return enemy;
    }
    
    public List<Enemy> generateWave(Random random) {
        Loader loader = new Loader();
        List<Enemy> enemies = new ArrayList<>();
        for (int i=0; i < 5; i++) {
            Enemy enemy = toolbox.Generator.generateEnemy(window, "sphere", "bob", loader, new Vector3f(180, (float)Math.random() * 5, i*4), 1f, 1.3f);
            //Enemy enemy = generateEnemy(window, "sphere", "bob",  loader, new Vector3f(90, (float)(Math.random() * 5), i * 12), x);
            enemies.add(enemy);
        }
        return enemies;
    }
    
    public void addWave(Random random, List<Enemy> enemies) {
        float scaleScalar = 1.3f;
        int randInt = random.nextInt(3);
        int randNumb = random.nextInt(10);
        Loader loader = new Loader();
        String randomString = textures[(int)random.nextInt(textures.length)];
        for (int i=0; i < randNumb; i++) {
            Enemy enemy = toolbox.Generator.generateEnemy(window, "sphere", randomString, loader, new Vector3f(100 + (float)Math.random() * 160, (float)Math.random() * 5 + 1, -40+(float)Math.random() * 75), randInt, randInt * scaleScalar);
            enemies.add(enemy);
        }
    }
    
    
    
   
    
   
}
