package comparator;

import java.util.*;

class Player {

    String name;
    String team;
    int age;
    long matches;
    double score;

    public Player(String name, String team, int age, long matches, double score) {
        this.name = name;
        this.team = team;
        this.age = age;
        this.matches = matches;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getAge() {
        return age;
    }

    public long getMatches() {
        return matches;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %d, %d, %.1f]", name, team, age, matches, score);
    }
}

public class Example {

    public static void main(String[] args) {
        // Base setup
        List<Player> original = Arrays.asList(
                new Player("Alice", "Red", 25, 150L, 45.5),
                new Player("bob", "Blue", 22, 50L, 50.0),
                new Player("Charlie", null, 30, 200L, 35.0),
                new Player("alice", "Red", 20, 80L, 60.0)
        );

        testCompare(new ArrayList<>(original));
        testComparing(new ArrayList<>(original));
        testComparingWithSecondary(new ArrayList<>(original));
        testPrimitives(new ArrayList<>(original));
        testNaturalAndReverseOrder();
        testNullHandling(new ArrayList<>(original));
        testChaining(new ArrayList<>(original));
    }

    static void testCompare(List<Player> list) {
        System.out.println("--- 1. compare(o1, o2) ---");
        // OLD: Anonymous class
        /*
        list.sort(new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
         */
        // NEW: Lambda
        list.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        list.forEach(System.out::println);
    }

    static void testComparing(List<Player> list) {
        System.out.println("\n--- 2. comparing(Function) ---");
        // OLD: See above
        // NEW: Method reference extracting the key
        list.sort(Comparator.comparing(Player::getName));
        list.forEach(System.out::println);
    }

    static void testComparingWithSecondary(List<Player> list) {
        System.out.println("\n--- 3. comparing(Function, Comparator) (Case Insensitive) ---");
        // NEW:
        list.sort(Comparator.comparing(Player::getName, String.CASE_INSENSITIVE_ORDER));
        list.forEach(System.out::println);
    }

    static void testPrimitives(List<Player> list) {
        System.out.println("\n--- 4. comparingInt / comparingLong / comparingDouble ---");
        // OLD:
        /*
        list.sort(new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                return Integer.compare(p1.getAge(), p2.getAge()); // Avoids primitive casting issues
            }
        });
         */
        // NEW: Avoids autoboxing overhead
        list.sort(Comparator.comparingInt(Player::getAge));
        list.forEach(System.out::println);
    }

    static void testNaturalAndReverseOrder() {
        System.out.println("\n--- 5. naturalOrder() and reverseOrder() ---");
        List<String> names = Arrays.asList("Charlie", "Alice", "bob"); // Note: 'b' is lowercased
        // OLD:
        /*
        Collections.sort(names, new Comparator<String>() {
            public int compare(String s1, String s2) { return s2.compareTo(s1); }
        });
         */
        // NEW:
        names.sort(Comparator.reverseOrder());
        System.out.println(names);
    }

    static void testNullHandling(List<Player> list) {
        System.out.println("\n--- 6. nullsFirst() and nullsLast() ---");
        // OLD:
        /*
        list.sort(new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                if (p1.getTeam() == null && p2.getTeam() == null) return 0;
                if (p1.getTeam() == null) return 1; // Push to end
                if (p2.getTeam() == null) return -1;
                return p1.getTeam().compareTo(p2.getTeam());
            }
        });
         */
        // NEW: Wrap the team extractor with nullsLast
        list.sort(Comparator.comparing(Player::getTeam, Comparator.nullsLast(Comparator.naturalOrder())));
        list.forEach(System.out::println);
    }

    static void testChaining(List<Player> list) {
        System.out.println("\n--- 7. thenComparing / thenComparingInt / reversed() ---");
        // OLD:
        /*
        list.sort(new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                // Ignore nulls here for brevity of old example
                int teamCompare = p2.getTeam().compareTo(p1.getTeam()); // Reversed team
                if (teamCompare != 0) return teamCompare;
                return Integer.compare(p1.getAge(), p2.getAge()); // Then age ascending
            }
        });
         */
        // NEW: Sort by Team (nulls last) REVERSED, then by Age ASCENDING
        list.sort(
                Comparator.comparing(Player::getTeam, Comparator.nullsLast(Comparator.naturalOrder()))
                        .reversed()
                        .thenComparingInt(Player::getAge)
        );
        list.forEach(System.out::println);
    }
}
