package com.company;
import java.util.Scanner;
import java.util.stream.IntStream;

class CircularQueue{
    int front;
    int rear;
    int qSize;
    int[] array;

    CircularQueue(int qSize){
        this.front = 0;
        this.rear = 0;
        this.qSize = qSize;
        array = new int[qSize];
    }

    public int getAvailableSpace() {
        return qSize - (rear - front);
    }

    public boolean isEmpty(){
        return getAvailableSpace() == qSize;
    }

    public boolean isFull(){
        return getAvailableSpace() == 0;
    }

    public void enQueue(int element){
        if(isFull()){
            throw new RuntimeException("Queue is FULL\n");
        }else {
            rear++;

            array[convertToRealIndex(rear)] = element;
        }
    }

    public int deQueue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is EMPTY\n");
        }else{
            front++;
            if(front > qSize) {
                front -= qSize;
                rear -= qSize;
            }

            return array[convertToRealIndex(front)];
        }
    }

    private int convertToRealIndex(int currentPosition) {
        return currentPosition % qSize;
    }

    public void print(){
        System.out.println(front + " ~ " + rear);

        IntStream.range(front + 1, rear +1)
                .forEach(i -> System.out.println(array[convertToRealIndex(i)] + " "));
        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 큐의 크기
        System.out.print("Max queueSize: " );
        int qSize = input.nextInt();

        // 큐 생성 & 초기화
        CircularQueue cqueue = new CircularQueue(qSize+1);

        // 큐 정보 입력
        System.out.println("\nMENU (1: enQueue | 2: deQueue | 3: print queue | -1: quit)\n");
        int check;
        do{
            System.out.print("what do you want? ");
            check = input.nextInt();

            // queue 삽입
            if(check == 1) {
                System.out.print("Input queue element: ");
                int tmp = input.nextInt();
                cqueue.enQueue(tmp);
                if(cqueue.isEmpty()){
                    System.out.println("empty");
                }
            } // queue 삭제
            else if(check == 2){
                System.out.println("Get element from queue: " + cqueue.deQueue());
            } // 출력
            else if(check == 3){
                cqueue.print();
            } // 종료
            else if(check == -1){
                System.out.println("exit...");
            } // 예외
            else{
                System.out.println("Command not found.\n");
            }

        } while(check != -1);
    }
}
