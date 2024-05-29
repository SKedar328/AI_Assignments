// package WaterJug;

// import java.util.*;

// class State {
//     int x, y; // Current water levels in jugs
//     State parent; // Parent state
//     int heuristic; // Heuristic value

//     public State(int x, int y, State parent, int heuristic) {
//         this.x = x;
//         this.y = y;
//         this.parent = parent;
//         this.heuristic = heuristic;
//     }

//     // Check if the state is the goal state
//     public boolean isGoalState(int targetX, int targetY) {
//         return x == targetX && y == targetY;
//     }

//     // Generate possible next states from this state
//     public ArrayList<State> getNextStates(int maxX, int maxY, int targetX, int targetY) {
//         ArrayList<State> nextStates = new ArrayList<>();
//         // Fill jug x
//         nextStates.add(new State(maxX, y, this, getHeuristic(maxX, y, targetX, targetY)));
//         // Fill jug y
//         nextStates.add(new State(x, maxY, this, getHeuristic(x, maxY, targetX, targetY)));
//         // Empty jug x
//         nextStates.add(new State(0, y, this, getHeuristic(0, y, targetX, targetY)));
//         // Empty jug y
//         nextStates.add(new State(x, 0, this, getHeuristic(x, 0, targetX, targetY)));
//         // Pour from x to y
//         int pourXY = Math.min(x, maxY - y);
//         nextStates.add(new State(x - pourXY, y + pourXY, this, getHeuristic(x - pourXY, y + pourXY, targetX, targetY)));
//         // Pour from y to x
//         int pourYX = Math.min(y, maxX - x);
//         nextStates.add(new State(x + pourYX, y - pourYX, this, getHeuristic(x + pourYX, y - pourYX, targetX, targetY)));

//         return nextStates;
//     }

//     // Simple heuristic: sum of the differences between current state and goal state
//     private int getHeuristic(int x, int y, int targetX, int targetY) {
//         return Math.abs(x - targetX) + Math.abs(y - targetY); // Example heuristic function
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj) return true;
//         if (obj == null || getClass() != obj.getClass()) return false;
//         State state = (State) obj;
//         return x == state.x && y == state.y;
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(x, y);
//     }
// }

// public class BestFirstSearch {
//     static int targetX = 4;
//     static int targetY = 0;

//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         int jug1Capacity = sc.nextInt(); // Capacity of jug 1
//         int jug2Capacity = sc.nextInt(); // Capacity of jug 2

//         State initialState = new State(0, 0, null, Math.abs(targetX - 0) + Math.abs(targetY - 0)); // Initial state

//         // Best First Search Algorithm
//         PriorityQueue<State> open = new PriorityQueue<>(Comparator.comparingInt(a -> a.heuristic));
//         HashSet<State> closed = new HashSet<>();
//         open.add(initialState);

//         while (!open.isEmpty()) {
//             State currentState = open.poll();
//             if (currentState.isGoalState(targetX, targetY)) {
//                 // Print solution path
//                 printSolution(currentState);
//                 return;
//             }
//             closed.add(currentState);

//             ArrayList<State> nextStates = currentState.getNextStates(jug1Capacity, jug2Capacity, targetX, targetY);
//             for (State nextState : nextStates) {
//                 if (!closed.contains(nextState)) {
//                     open.add(nextState);
//                 }
//             }
//         }
//         System.out.println("No solution found.");
//     }

//     // Print the solution path
//     public static void printSolution(State state) {
//         Stack<State> stack = new Stack<>();
//         while (state != null) {
//             stack.push(state);
//             state = state.parent;
//         }
//         while (!stack.isEmpty()) {
//             State currentState = stack.pop();
//             System.out.println("Jug 1: " + currentState.x + ", Jug 2: " + currentState.y);
//         }
//     }
// }
