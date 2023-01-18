package overworld.graphics;

public class Screen {
    
    private int width, height;
    public int[] pixels;

    private int xtime = 100, ytime = 50;
    private int counter = 0;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void render(){
        counter++;
    }
}
