package com.projects;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine(400, 540, 120, 9, 550);

        while (machine.machineAction != Action.EXIT) {
            machine.machineAction(scanner);
        }
    }
}
