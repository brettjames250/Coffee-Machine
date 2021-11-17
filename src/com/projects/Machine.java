package com.projects;

import java.util.Scanner;

public class Machine {
    int waterAmount;
    int milkAmount;
    int coffeeAmount;
    int disposableCupsAmount;
    int moneyAmount;
    Action machineAction;


    public Machine(int waterAmount, int milkAmount, int coffeeAmount, int disposableCupsAmount, int moneyAmount) {
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
        this.coffeeAmount = coffeeAmount;
        this.disposableCupsAmount = disposableCupsAmount;
        this.moneyAmount = moneyAmount;
        this.machineAction = Action.MENU;
    }

    void machineAction(Scanner scanner) {
        switch (machineAction) {
            case MENU:
                System.out.println(" ");
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String choice = scanner.next();
                setMachineAction(choice);
                break;
            case BUY:
                System.out.println(" ");
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                processDrinkOrder(scanner.next());
                setMachineAction(Action.MENU);
                break;
            case FILL:
                setMachineAction(Action.FILL);
                System.out.println(" ");
                System.out.println("Write how many ml of water you want to add:");
                waterAmount += scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add:");
                milkAmount += scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add:");
                coffeeAmount += scanner.nextInt();
                System.out.println("Write how many disposable cups of coffee you want to add:");
                disposableCupsAmount += scanner.nextInt();
                setMachineAction(Action.MENU);
                break;
            case TAKE:
                setMachineAction(Action.TAKE);
                System.out.println("I gave you $" + moneyAmount);
                moneyAmount = 0;
                setMachineAction(Action.MENU);
                break;
            case REMAINING:
                setMachineAction(Action.REMAINING);
                printRemaining(waterAmount, milkAmount, coffeeAmount, disposableCupsAmount, moneyAmount);
                setMachineAction(Action.MENU);
                break;
            case EXIT:
                setMachineAction(Action.EXIT);
                break;
            default:
                System.out.println("Pick a valid option");
        }
    }

    void processDrinkOrder(String drinkOption) {
        switch (drinkOption) {
            case "1":
                boolean enoughResourceForEspresso = checkResources(0, 250, 16, waterAmount, milkAmount, coffeeAmount, disposableCupsAmount);
                if (enoughResourceForEspresso) {
                    System.out.println("I have enough resources, making you a coffee!");
                    waterAmount = takeResource(250, waterAmount);
                    coffeeAmount = takeResource(16, coffeeAmount);
                    disposableCupsAmount = takeResource(1, disposableCupsAmount);
                    moneyAmount += 4;
                }
                break;
            case "2":
                boolean enoughResourcesForLatte = checkResources(75, 350, 20, waterAmount, milkAmount, coffeeAmount, disposableCupsAmount);
                if (enoughResourcesForLatte) {
                    System.out.println("I have enough resources, making you a coffee!");
                    waterAmount = takeResource(350, waterAmount);
                    coffeeAmount = takeResource(20, coffeeAmount);
                    milkAmount = takeResource(75, milkAmount);
                    disposableCupsAmount = takeResource(1, disposableCupsAmount);
                    moneyAmount += 7;
                }
                break;
            case "3":
                boolean enoughResourcesForCappucino = checkResources(75, 350, 20, waterAmount, milkAmount, coffeeAmount, disposableCupsAmount);
                if (enoughResourcesForCappucino) {
                    System.out.println("I have enough resources, making you a coffee!");
                    waterAmount = takeResource(200, waterAmount);
                    coffeeAmount = takeResource(12, coffeeAmount);
                    milkAmount = takeResource(100, milkAmount);
                    disposableCupsAmount = takeResource(1, disposableCupsAmount);
                    moneyAmount += 6;
                }
                break;
            case "back":
                break;
        }
    }

    static boolean checkResources(int requiredMilk, int requiredWater, int requiredCoffee, int stockWater, int stockMilk, int stockCoffee, int stockCups) {
        if (requiredMilk > stockMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (requiredWater > stockWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (requiredCoffee > stockCoffee) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        }
        if (stockCups <= 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        return true;
    }

    static Integer takeResource(int amountRequired, int stockAmount) {
        return stockAmount - amountRequired;
    }

    static void printRemaining(int waterAmount, int milkAmount, int coffeeAmount, int disposableCupsAmount, int moneyAmount) {
        System.out.println(" ");
        System.out.println("The coffee machine has:");
        System.out.println(waterAmount + " ml of water");
        System.out.println(milkAmount + " ml of milk");
        System.out.println(coffeeAmount + " g of coffee beans");
        System.out.println(disposableCupsAmount + " disposable cups");
        System.out.println("$" + moneyAmount + " of money");
    }

    void setMachineAction(String action) {
        switch (action) {
            case "buy":
                this.machineAction = Action.BUY;
                break;
            case "fill":
                this.machineAction = Action.FILL;
                break;
            case "take":
                this.machineAction = Action.TAKE;
                break;
            case "remaining":
                this.machineAction = Action.REMAINING;
                break;
            case "exit":
                this.machineAction = Action.EXIT;
                break;
            default:
                System.out.println("Pick a valid option");
        }
    }

    void setMachineAction(Action action) {
        this.machineAction = action;

    }
}
