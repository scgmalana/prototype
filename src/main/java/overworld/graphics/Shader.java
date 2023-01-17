package overworld.graphics;

import overworld.utils.ShaderUtils;
import overworld.math.*;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

public class Shader {

    private final int ID;
    private boolean invalid = false;
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    public static Shader BG, BOX;

    private boolean enabled = false;
    
    public Shader(String vertex, String fragment){
        ID = ShaderUtils.load(vertex, fragment);

        if(ID < 0){
            invalid = true;
        }
    }

    public static void loadAll(){
        BG = new Shader("prototype/shaders/bg.vert", "prototype/shaders/bg.frag");
        BOX =  new Shader("prototype/shaders/box.vert", "prototype/shaders/box.frag");
    }

    public int getUniform(String name){
        if(locationCache.containsKey(name)){
            return locationCache.get(name);
        }
        int result = glGetUniformLocation(ID, name);
        if(result == -1){
            System.err.println("Could not find uniform variable '" + name + "'!");
        }
        else{
            locationCache.put(name, result);
        }
        return result;
    }

    public void setUniform1i(String name, int value){
        if(!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value){
        if(!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y){
        if(!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vector){
        if(!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix){
        if(!enabled) enable();
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable(){
        if(!invalid){
            glUseProgram(ID);
        }
        enabled = true;
    }

    public void disable(){
        if(!invalid){
            glUseProgram(0);
        }
        enabled = false;
    }
}
