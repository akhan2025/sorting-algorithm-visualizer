import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
import java.util.Scanner;

public class BarMaker extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int barNumber = 20;
    private bar bar[] = new bar[barNumber];
    private int totalMoves = 0;
    private int totalComparisons = 0;
    private long startTime = 0;

    public BarMaker() {
        for (int i = 0; i < 20; i++) {
            // Color myColor = new ColorUIResource(i, 0, 0);
            bar[i] = new bar(Color.red, i + 1);
        }
    }

    public BarMaker(int num) {

        this.barNumber = num;
        bar = new bar[num];
        int colorAlter = 255/barNumber;
        for (int i = 0; i < num; i++) {
            Color myColor = new Color(i*colorAlter, 0, 255-i*colorAlter);
            bar[i] = new bar(myColor, i + 1);
        }
    }

    public void paint(Graphics g) {
        // backrgound
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // displays bars
        for (int i = 0; i < barNumber; i++) {
            g.setColor(bar[i].getColor());
            g.fillRect(i * 590 / barNumber + 10, 400 - bar[i].getHeight()*3, 590 / barNumber, bar[i].getHeight() * 3);
            g.setColor(Color.black);
            g.drawRect(i * 590 / barNumber + 10, 400 - bar[i].getHeight()*3, 590 / barNumber, bar[i].getHeight() * 3);
        }

        // totalmoves
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("total moves: " + totalMoves, 200, 500);
        g.drawString("total comparisons: " + totalComparisons, 200, 530);


        //time
        if(startTime == 0){
            g.drawString("time elapsed: " + 0 + ":" + 00, 200, 550);
        }
        else{
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        g.drawString("time elapsed: " + elapsedMinutes + ":" + secondsDisplay, 200, 550);
        }
    }

    public void randomize() {
        for (int i = 0; i < barNumber; i++) {
            int random = (int) (Math.random() * barNumber);
            bar temp = bar[i];
            bar[i] = bar[random];
            bar[random] = temp;

            sleep(1 * ((590 / barNumber) * 10));
            repaint();
        }
        totalMoves = 0;
        totalComparisons = 0;
    }

    public void bubbleSort() {
        timerStart();
        for (int i = 0; i < barNumber; i++) {
            for (int j = 0; j < barNumber - i - 1; j++) {
                totalComparisons++;
                if (bar[j].getHeight() > bar[j + 1].getHeight()) {
                    bar temp = bar[j];
                    bar[j] = bar[j + 1];
                    bar[j + 1] = temp;
                    totalMoves++;
                }
                sleep(10);
            }
        }
        timerStop();
    }

    public void selectionSort() {
        timerStart();
        for (int i = 0; i < barNumber - 1; i++) {
            int smallestHeight = bar[i].getHeight();
            int index = i;
            for (int j = i + 1; j < barNumber; j++) {
                totalComparisons++;
                if (bar[j].getHeight() < smallestHeight) {
                    smallestHeight = bar[j].getHeight();
                    index = j;
                    totalMoves++;
                }
            }
            bar temp = bar[index];
            bar[index] = bar[i];
            bar[i] = temp;
            sleep(50);
            repaint();

        }
        timerStop();
    }

    public void insertionSort(){
        timerStart();
        for(int i = 0; i < barNumber; i++){
            int key = bar[i].getHeight();
            int j = i;
            totalComparisons++;
            while(j > 0 && bar[j - 1].getHeight() > key){ 
                int temp = bar[j].getHeight();
                Color temp1 = bar[j].getColor();
                bar[j].setHeight(bar[j-1].getHeight());
                bar[j].setColor(bar[j-1].getColor()); 
                bar[j-1].setHeight(temp);
                bar[j-1].setColor(temp1);
                j = j - 1;  
                totalMoves++;
            }  
            bar[j].setHeight(key);
            sleep(50);
            repaint();
        }
        timerStop();
    }

    public void merge(bar[] arr, int start, int mid, int end){
        bar temp[] = new bar[end - start + 1];
        int i = start, j = mid+1, k = 0;

        // traverse both arrays and in each iteration add smaller of both elements in temp 
        while(i <= mid && j <= end) {
            totalComparisons++;
            if(arr[i].getHeight() <= arr[j].getHeight()) {
                totalMoves++;
                temp[k] = arr[i];
                k += 1; i += 1;
            }
            else {
                temp[k] = arr[j];
                k += 1; j += 1;
            }
            sleep(10);
        }

        // add elements left in the first interval 
        while(i <= mid) {
            temp[k] = arr[i];
            k += 1; i += 1;
        }

        // add elements left in the second interval 
        while(j <= end) {
            temp[k] = arr[j];
            k += 1; j += 1;
        }

        // copy temp to original interval
        for(i = start; i <= end; i += 1) {
            arr[i] = temp[i - start];
            sleep(10);
        }
    }

    public void mergeSort(bar arr[], int start, int end) {

        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            sleep(10);
            mergeSort(arr, mid+1, end);
            sleep(10);
            merge(arr, start, mid, end);
            repaint();
        }
    }

    public void callMerge(){
        timerStart();
        mergeSort(bar, 0, bar.length - 1);
        timerStop();
    }

    public void quickSort(bar[] unsorted, int low, int high){
        if(low < high){

            int startMark = startMark(unsorted, low, high);
            sleep(10);
            quickSort(unsorted, low, startMark - 1);
            sleep(10);
            quickSort(unsorted, startMark + 1, high);
        }
    }

    public int startMark(bar[] unsorted, int low, int high){

        bar pivot = unsorted[high];

        int i = low - 1;

        for(int j = low; j <= high - 1; j++){
            if(unsorted[j].getHeight() <= pivot.getHeight()){
                i++;
                bar temp = unsorted[i];
                unsorted[i] = unsorted[j];
                unsorted[j] = temp;
                sleep(10);
            }
        }

        bar temp = unsorted[i+1];
        unsorted[i+1] = unsorted[high];
        unsorted[high] = temp;
        sleep(10);

        return (i+1);
    }

    public void callQuickSort(){
        timerStart();
        quickSort(bar, 0, bar.length - 1);
        timerStop();
    }

    public void sleep(int num){
        try {
            Thread.sleep(1 * num);
            repaint();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void timerStart(){
        startTime = System.currentTimeMillis();
    }

    public void timerStop(){
        startTime = 0;
    }

    public void inquiry(String s1){
        String s = s1.toLowerCase();
        switch(s){
            case "selection":
                randomize();
                sleep(1000);
                selectionSort();
                break;
            case "insertion":
                randomize();
                sleep(1000);
                insertionSort();
                break;
            case "bubble":
                randomize();
                sleep(1000);
                bubbleSort();
                break;
            case "merge":
                randomize();
                sleep(1000);
                callMerge();
                break;
            case "quick":
                randomize();
                sleep(1000);
                callQuickSort();
                break;
            case "done":
                return;
        }
    }
}