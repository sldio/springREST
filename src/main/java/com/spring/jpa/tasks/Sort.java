package com.spring.jpa.tasks;

import java.util.Comparator;

public class Sort implements Comparable
{

    private static int[] massForSort = makeNewMas();

    private static int[] makeNewMas(){
        int[] mas = new int[100000];
        for (int i = 0; i < mas.length; i++){
            mas[i] = (int) (Math.random()*1000);
        }
        return mas;
    }

    private static void printMas(int[] mas){
        for (int i = 0; i < mas.length; i++){
            System.out.println(mas[i]);
        }
    }

    private static void sortBuble()
    {
        int [] bubleMas = massForSort;
        for (int i = bubleMas.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (bubleMas[j] > bubleMas[j + 1]) {
                    int t = bubleMas[j];
                    bubleMas[j] = massForSort[j + 1];
                    bubleMas[j + 1] = t;
                }
            }
        }
    }

    private static void sortMas()
    {

    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    private static void sortInsert()
    {
        int[] sortInsertMas = massForSort;
        int temp, j;
        for(int i = 0; i < sortInsertMas.length - 1; i++){
            if (sortInsertMas[i] > sortInsertMas[i + 1]) {
                temp = sortInsertMas[i + 1];
                sortInsertMas[i + 1] = sortInsertMas[i];
                j = i;
                while (j > 0 && temp < sortInsertMas[j - 1]) {
                    sortInsertMas[j] = sortInsertMas[j - 1];
                    j--;
                }
                sortInsertMas[j] = temp;
            }
        }
    }

    private static void qsort(int start, int end)
    {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (massForSort[i] <= massForSort[cur])) {
                i++;
            }
            while (j > cur && (massForSort[cur] <= massForSort[j])) {
                j--;
            }
            if (i < j) {
                int temp = massForSort[i];
                massForSort[i] = massForSort[j];
                massForSort[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        qsort(start, cur);
        qsort(cur+1, end);
    }

    public static void main(String[] args)
    {
        makeNewMas();
        //printMas(massForSort);
        long time = System.nanoTime();
        sortBuble();
        System.out.println("time buble - " + (System.nanoTime()-time)/1000000 + " ms");
        System.out.println("---------------------------");


        time = System.nanoTime();
        sortInsert();
        System.out.println("time insert - " + (System.nanoTime()-time)/1000000 + " ms");
        System.out.println("---------------------------");

        time = System.nanoTime();
        qsort(0, massForSort.length-1);
        System.out.println("time qsort - " + (System.nanoTime()-time)/1000000 + " ms");
        //printMas(massForSort);
    }
}
