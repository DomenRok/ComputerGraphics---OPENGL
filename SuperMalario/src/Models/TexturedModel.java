/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import textures.ModelTexture;

/**
 *
 * @author Domen Brunček
 */
public class TexturedModel {
    private RawModel rawModel;
    private ModelTexture texture;

    public TexturedModel(RawModel rawModel, ModelTexture texture) {
        this.rawModel = rawModel;
        this.texture = texture;
    }

    /**
     * @return the rawModel
     */
    public RawModel getRawModel() {
        return rawModel;
    }

    /**
     * @return the texture
     */
    public ModelTexture getTexture() {
        return texture;
    }
    
    
}

