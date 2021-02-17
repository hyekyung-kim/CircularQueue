package com.company;
import java.util.Scanner;

class CircularQueue{
    int front;
    int rear;
    int qSize;
    int[] array;

    CircularQueue(int front, int rear, int qSize){
        this.front = front;
        this.rear = rear;
        this.qSize = qSize;
        array = new int[qSize];
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return (rear + 1) % array.length == front;
    }

    public void enQueue(int element){
        if(isFull()){
            System.out.println("Queue is FULL\n");
        }else {
            rear++;
            if(rear / array.length > 0) {
                rear = rear % array.length;
            }
            array[rear] = element;
        }
    }

    public int deQueue(){
        if(isEmpty()){
            System.out.println("Queue is EMPTY\n");
            return -1;
        }else{
            front++;
            if(front / array.length > 0){
                front = front % array.length;
            }

            return array[front];
        }

    }

    public void printQueue(){
        System.out.println("front: " + front + " | rear: " + rear);
        if(front < rear){
            for(int i = front+1; i <= rear; i++){
                System.out.print(array[i] + " ");
            }
        } else if(front > rear){
            for(int i = front+1; i < array.length; i++){
                System.out.print(array[i] + " ");
            }
            for(int i = 0; i <= rear; i++){
                System.out.print(array[i] + " ");
            }
        }

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
        CircularQueue cqueue = new CircularQueue(0, 0, qSize+1);

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
            } // queue 삭제
            else if(check == 2){
                System.out.println("Get element from queue: " + cqueue.deQueue());
            } // 출력
            else if(check == 3){
                cqueue.printQueue();
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
