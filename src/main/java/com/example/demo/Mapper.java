package com.example.demo;

public class Mapper {
    private  final   char[] ABCDARIJA =   {' ','z','y' ,'x' ,'w' ,'v' ,'u' ,'t' ,'s' ,'r' ,'q' ,'p' ,'o' ,'n' ,'m' ,'l' ,'k' ,'j' ,'i' ,'h' ,'g' ,'f' ,'e' ,'d' ,'c' ,'b', 'a','3','5','8','9','7','.','4','6','1','0','?'};
    private final char[] ABCARAB = 	 { ' ','ز','ي', 'ش' ,'و' ,'ف' ,'و' ,'ت', 'س' ,'ر' ,'ك','ب' ,'و','ن' ,'م' ,'ل' ,'ك' ,'ج' ,'ي' ,'ه' ,'ك' ,'ف' ,'ي' ,'د', 'ك','ب', 'ا','ع','خ','غ','ق','ح','.','4','6','1','0',};


    public  String translaterate(String message){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < ABCDARIJA.length-1; x++ ) {
                if (message.charAt(i) == ABCDARIJA[x]) {
                        builder.append(ABCARAB[x]);
                }
            }

        }
        return builder.toString();

    }


}
