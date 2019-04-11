package com.rojek.kamil.primary_numbers;

/**
 * @author Kamil Rojek
 */
class Rekurencja {
    public static void main(String[] args) {
        System.out.println(countX("xxhixx"));
    }

    private static int countX(String str) {
        int size = str.length();
        if(size == 1){
            if(str.charAt(size-1) == 'x')
                return 1;
            return 0;
        }

        char letter = str.charAt(size-1);
        if (letter == 'x') {
            return 1;
        }

        return countX(str.substring(0, size-1));
        //return (int)str.chars().filter(c -> c == 'x').count();
    }

}
