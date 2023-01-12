package overworld;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

public class Main implements Runnable{
    private int width = 1280;
    private int height = 720;

    private Thread thread;
    private boolean running = false;

    private long window;

    public void start(){
        running = false;
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void init(){
        if(!glfwInit()){

        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(width, height, "Test", NULL, NULL);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    public void run(){
        init();
        while(running){
            update();
            render();
        }
    }

    private void update(){
        glfwPollEvents();
    }

    private void render(){
        glfwSwapBuffers(window);
    }
    public static void main(String[] args){

    }
}
