package com.teamtreehouse.restaurant;

import com.teamtreehouse.restaurant.staff.Assistant;
import com.teamtreehouse.restaurant.staff.Server;
import com.teamtreehouse.restaurant.tables.Table;
import com.teamtreehouse.restaurant.tools.Dashboard;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.teamtreehouse.restaurant.core.Utilities.passTime;

public class Simulator {
    public static void main(String[] args) {
        Table table1 = new Table(1, 10);
        Table table2 = new Table(2, 10);
        Table table3 = new Table(3, 2);
        Table table4 = new Table(4, 10);
        Table table5 = new Table(5, 4);

        List<Table> tables = Arrays.asList(
                table1, table2, table3, table4, table5
        );
        Dashboard dashboard = new Dashboard();
        tables.forEach(dashboard::addTable);

        Server alice = new Server("Alice");
        Server bob = new Server("Bob");

        List<Server> servers = Arrays.asList(alice, bob);

        Assistant charlie = new Assistant("Charlie");
        Assistant darla = new Assistant("Darla");

        List<Assistant> assistants = Arrays.asList(charlie, darla);

        tables.forEach(table -> table.addObserver(dashboard));
        table1.addObserver(alice);
        table2.addObserver(charlie);
        table2.addObserver(alice);
        table2.addObserver(charlie);
        table3.addObserver(alice);
        table2.addObserver(charlie);
        table4.addObserver(bob);
        table2.addObserver(darla);
        table5.addObserver(bob);
        table2.addObserver(darla);

        int numberOfIterations = 30;
        for (int counter = 0; counter < numberOfIterations; counter++) {
            Optional<Server> server = servers.stream()
                    .filter(Server::isAvailable)
                    .findAny();
            Optional<Assistant> assistant = assistants.stream()
                    .filter(Assistant::isAvailable)
                    .findAny();
            for (Table table : tables) {

            }
            passTime(1);
        }
        System.out.println("Closing up shop");
        servers.forEach(Server::clockOut);
        assistants.forEach(Assistant::clockOut);
        dashboard.shutdown();
    }
}
