package Backend;

import java.io.File;  // Import the File class // Import this class to handle errors
import java.util.Scanner;

public class LoadMap {
    int size_object = 48;
    int height_screen;
    int width_screen;
    File file_map;
    Scanner read_file;
    int level_number;
    int map_rows;
    int map_cols;
    char[][] map_char;
    LoadMap()
    {
        try
        {
            file_map = new File("Level1.txt");
            read_file = new Scanner(file_map);
            level_number = read_file.nextInt();
            map_rows = read_file.nextInt();
//            height_screen = map_rows * size_object;
//            System.out.println(height_screen);
            map_cols = read_file.nextInt();
//            width_screen = map_cols * size_object;
//            System.out.println(width_screen);
            map_char = new char[map_rows][map_cols];
            //int i = 0;
            String map_string = read_file.nextLine();
            for(int i = 0;i < map_rows;++i)
            {
                map_string = read_file.nextLine();
                //System.out.println(map_string.charAt(1));
                for(int j = 0;j < map_string.length();++j)
                {
                    map_char[i][j] = map_string.charAt(j);
                }
                //i++;
            }
            //print();
        }
        catch(Exception e)
        {
            System.out.println("File not found !");
        }
    }
    LoadMap(int width, int height)
    {
        width_screen  = width;
        height_screen = height;
    }
    public void readFile()
    {

    }
    public void print()
    {
        for(int i = 0;i < map_rows;++i)
        {
            for(int j = 0;j < map_cols;++j)
            {
                System.out.print(map_char[i][j]);
            }
            System.out.println("");
        }
    }
}