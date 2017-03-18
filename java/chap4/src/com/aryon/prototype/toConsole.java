package com.aryon.prototype;

/**
 * Created by Dai on 2016/11/28.
 */
public class toConsole {
    
    private toConsole() {
        //
    }
    
    /**
     * item + space should be even
     * @param item
     * @param space
     */
    public static void printBinaryTree(Iterable array, int item, int space) {
        int size = 0;
        for (Object t : array) size++;
        
        int layers = 0; int s = size-1;
        while(s != 0) {
            s = (s-1)/2;
            layers++;
        } // get number of layers
        
        System.out.println("\nnumber of layers: " + layers);
        int flag = 0; //the last reference number of each layer, 0, 2, 6 ...
        int curr = 0; //current number of layer
        System.out.print("layer:"+curr+" ");
        int squares = 0;
        for(int i = 0; i < layers; i++) squares = squares*2+(item+space)/2; // get space between two item of each layer
        
        char spc = ' '; char line = '-'; int i = 0;
        for(Object a : array) {
            if(i > flag) {
                flag += Math.pow(2,curr+1);
                curr++;
                squares = (squares-(item+space)/2)/2;
                System.out.println();
                System.out.print("layer:"+curr+" ");
            }
            for(int j = 0; j < squares; j++) System.out.print(i==0?spc:line);
            System.out.print(a.toString());
            for(int j = 0; j < squares+space; j++) System.out.print(spc);
            char t = spc; spc = line; line = t; // print line between two child with the same parent
            
            i++;
        }
        System.out.println();
    }
}
