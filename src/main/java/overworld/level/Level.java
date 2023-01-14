package overworld.level;

import overworld.graphics.*;

public class Level {
    
    private VertexArray background;
    private Texture bgTexture; 

    public Level(){
        float[] vertices = new float[]{
            //which corners does it reach essentially
            //0.0f is the middle of the screen
            -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
            -10.0f,  10.0f * 9.0f / 16.0f, 0.0f,
              10.0f,  10.0f * 9.0f / 16.0f, 0.0f,
              10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
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

        background = new VertexArray(vertices, indices, tcs);
        bgTexture = new Texture("prototype/res/bun_is_me_fr.jpg");
    }
    
    public void render(){
        bgTexture.bind();
        Shader.BG.enable();
        background.render();
        Shader.BG.disable();
        bgTexture.unbind();
    }
}
