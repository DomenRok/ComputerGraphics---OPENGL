/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.render.models;

import com.sun.javafx.geom.Vec3f;
import org.joml.Matrix4f;

/**
 *
 * @author Domen Brunček
 */
public class ModelEntitiy {
    private TexturedModel model;
    private Vec3f position, rotation, scale;

    public ModelEntitiy(TexturedModel model, Vec3f position, Vec3f rotation, Vec3f scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
    
    public Matrix4f getTransformationMatrix4f() {
        return engine.math.MatrixMaths.createTransformationMatrix(position, rotation, scale);
    }
    
    public void addPositionX(float value) {
        position.x += value;
    }
    
    public void addPositionY(float value) {
        position.y += value;
    }
    
    public void addPositionZ(float value) {
        position.z += value;
    }
    
	public void addRotationX(float value) {
		rotation.x += value;
	}
	
	public void addRotationY(float value) {
		rotation.y += value;
	}

	public void addRotationZ(float value) {
		rotation.z += value;
	}
	
	public void addScaleX(float value) {
		scale.x += value;
	}
	
	public void addScaleY(float value) {
		scale.y += value;
	}
	
	public void addScaleZ(float value) {
		scale.z += value;
	}
	
	public TexturedModel getModel() {
		return model;
	}
	
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	
	public float getPositionX() {
		return position.x;
	}
	
	public float getPositionY() {
		return position.y;
	}
	
	public float getPositionZ() {
		return position.z;
	}
	
	public void setPositionX(float value) {
		this.position.x = value;
	}
	
	public void setPositionY(float value) {
		this.position.y = value;
	}
	
	public void setPositionZ(float value) {
		this.position.z = value;
	}
	
	public float getRotationX() {
		return position.x;
	}
	
	public float getRotationY() {
		return position.y;
	}
	
	public float getRotationZ() {
		return position.z;
	}
	
	public void setRotationX(float value) {
		this.rotation.x = value;
	}
	
	public void setRotationY(float value) {
		this.rotation.y = value;
	}
	
	public void setRotationZ(float value) {
		this.rotation.z = value;
	}
	
	public float getScaleX() {
		return scale.x;
	}
	
	public float getScaleY() {
		return scale.y;
	}
	
	public float getScaleZ() {
		return scale.z;
	}
	
	public void setScaleX(float value) {
		this.scale.x = value;
	}
	
	public void setScaleY(float value) {
		this.scale.y = value;
	}
	
	public void setScaleZ(float value) {
		this.scale.z = value;
	}
}
