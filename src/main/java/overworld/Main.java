package overworld;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import org.lwjgl.opengl.*;
import org.lwjgl.glfw.GLFWVidMode;

import overworld.Input.Input;
import overworld.graphics.Shader;
import overworld.math.Matrix4f;
import overworld.level.*;

public class Main implements Runnable{
    private int width = 1260;
    private int height = 700;

    private Thread thread;
    private boolean running = false;

    private long window;

    private Level level;

    public void start(){
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void init(){
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(width, height, "Test", NULL, NULL);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE0);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        Shader.loadAll();

        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
        Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BG.setUniform1f("tex", 0);

        Shader.BOX.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BOX.setUniform1f("tex", 0);

        level = new Level();
    }

    public void run(){
        init();

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1.0){
                update();                
                updates++;
                delta --;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }

            if(glfwWindowShouldClose(window)){
                running = false;
            }
        }
    }

    private void update(){
        glfwPollEvents();
        level.update();
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        level.render();
        int error = glGetError();
        if(error != GL_NO_ERROR){
            System.out.println(error);
        }
        glfwSwapBuffers(window);
    }

    public static void main(String[] args){
        new Main().start();
    }
}
