package overworld.level;

import org.lwjgl.glfw.*;
import overworld.graphics.VertexArray;
import overworld.math.Matrix4f;
import overworld.math.Vector3f;
import overworld.graphics.*;
import overworld.Input.*;

public class Box {
    private float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;

    private Vector3f position = new Vector3f();
    private float rot;
    private float delta = 0.0f;

    public Box() {
        float[] vertices = new float[]{
            //which corners does it reach essentially
            //0.0f is the middle of the screen
            -SIZE / 2.0f, -SIZE /2.0f, 0.1f,
            -SIZE / 2.0f, SIZE /2.0f, 0.1f,
            SIZE / 2.0f, SIZE /2.0f, 0.1f,
            SIZE / 2.0f, -SIZE /2.0f, 0.1f,
        };

        byte[] indices = new byte[]{
            0, 1, 2,
            2, 3, 0
        };

        float[] tcs = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1,
        };

        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("prototype/res/box.png");
    }

    public void update(){
        if (Input.keys[GLFW.GLFW_KEY_W]){
            position.y += 0.1f;
        }
        if (Input.keys[GLFW.GLFW_KEY_A]){
            position.x -= 0.1f;
        }
        if (Input.keys[GLFW.GLFW_KEY_S]){
            position.y -= 0.1f;
        }
        if (Input.keys[GLFW.GLFW_KEY_D]){
            position.x += 0.1f;
        }

    }

    public void render(){
        Shader.BOX.enable();
        Shader.BOX.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BOX.disable();
    }
}