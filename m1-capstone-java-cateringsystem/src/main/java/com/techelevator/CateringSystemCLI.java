package com.techelevator;

import com.techelevator.view.Menu;

public class CateringSystemCLI {

    private Menu menu;

    public CateringSystemCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        CateringSystem cateringSystem = new CateringSystem(menu);
        cateringSystem.populateInventory();
        menu.showWelcomeMessage();
        while (true) {
            menu.showMainMenu();
            int userSelection = cateringSystem.checkForValidMenuSelection(menu.readUserInput("Please enter selection by number: "));
            if (userSelection == 1) {
                cateringSystem.displayCateringItems();
            } else if (userSelection == 2) {
                menu.showSubMenuHeading();
                while (true) {
                    cateringSystem.callSubMenu();
                    userSelection = cateringSystem.checkForValidMenuSelection(menu.readUserInput("Please enter selection by number: "));
                    if (userSelection == 1) {
                        menu.showCaseMessage(cateringSystem.checkForValidMoneyEntry(menu.readUserInput("Please enter dollar amount (no change) between 1-500: ")));
                    } else if (userSelection == 2) {
                        cateringSystem.displayCateringItems();
                        menu.showCaseMessage(cateringSystem.checkForValidAddToCart());
                    } else if (userSelection == 3) {
                        cateringSystem.completeTransaction();
                        break;
                    }
                }
            } else if (userSelection == 3) {
                break;
            }
        }
        menu.showCaseMessage("Thanks for shopping!");
    }


    public static void main(String[] args) {
        Menu menu = new Menu();
        CateringSystemCLI cli = new CateringSystemCLI(menu);
        cli.run();
    }
}
