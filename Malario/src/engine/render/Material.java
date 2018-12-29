/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.render;

import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Domen Brunček
 */
public class Material {
    private int TextureID;
    
    public Material(String file) {
        try {
            TextureID = TextureLoader.getTexture("png", new FileInputStream("res/textures/"+file)).getTextureID();
        } catch (IOException e) {
            System.err.println("Error: Couldn't load texture");
        }
    }
    
    public void remove() {
        GL11.glDeleteTextures(TextureID);
    }

    /**
     * @return the TextureID
     */
    public int getTextureID() {
        return TextureID;
    }
}
