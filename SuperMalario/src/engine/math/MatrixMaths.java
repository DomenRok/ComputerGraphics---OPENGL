/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.math;

import com.sun.javafx.geom.Vec3f;
import org.joml.Matrix4f;

/**
 *
 * @author Domen Brunček
 */
public class MatrixMaths {
    public static Matrix4f createTransformationMatrix(Vec3f translation, Vec3f rotation, Vec3f scale) {
        Matrix4f matrix = new Matrix4f().identity();
        matrix.translate(translation.x, translation.y, translation.z);
        matrix.rotateAffineXYZ(rotation.x, rotation.y, rotation.z);
        matrix.scale(scale.x, scale.y, scale.z);
        return matrix;
    }
}
