/**
 * CPCS 324 Project - Phase 3
 * Task 2
 * This program is designed to find the maximum bipartite matching algorithm
 * of a given graph represented 
 * ** The code is obtained from:
 * https://www.tutorialspoint.com/Maximum-Bipartite-Matching
 * 
 */
package cpcs324_phase3;

import java.util.ArrayList;

/**
 *
 * @author Zainab Aljifri
 * @author Lubna Alharthy
 * @author Rahaf Aljohani
 */
public class max_bipartite {

    //Constructing the applicants and the hospitals arrays 
    String[] ApplicantsList = {"Ahmed", "Mahmoud", "Eman", "Fatimah", "Kamel", "Nojood"};
    String[] HospitalaList = {"King Abdelaziz University", "King Fahd", "East Jeddah", "King Fahad Armed Forces", "King Faisal Specialist", "Ministry of National Guard"};
    ArrayList<Integer> set_M = new ArrayList<>();

    int M = ApplicantsList.length, N = HospitalaList.length;
    int bipartiteGraph[][] = new int[][]{ //A graph with M applicant and N hospitals
        {1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 1}
    };
    int applicants[] = new int[N];
    /**
     * function for checking if a matching for applicant is possible 
     * @param u
     * @param visited
     * @param assign
     * @return true if a match is found
     */
    boolean bipartiteMatch(int u, boolean visited[], int assign[]) {
        for (int v = 0; v < N; v++) {    //for all hospital 0 to N-1
            if (bipartiteGraph[u][v] == 1 && !visited[v]) {    //when hospital v is not visited and u is interested
                visited[v] = true;    //mark as hospital v is visited
                //when v is not assigned or previously assigned
                if (assign[v] < 0 || bipartiteMatch(assign[v], visited, assign)) {
                    assign[v] = u;    //assign hospital v to applicant u
                    System.out.println(ApplicantsList[u] + " is assigned to " + HospitalaList[v]+"\n");
                    set_M.set(u, v); // add the edge to matching set M
                    applicants[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * maximum matching method
     * @return number of matching found in the graph
     */
    int maxMatch() {
        int assign[] = new int[N];    //an array to track which jospital is assigned to which applicant
        for (int i = 0; i < N; i++) {
            assign[i] = -1;    //initially set all jobs are available
            set_M.add(-1);     //initialize the set M 
        }
        int matchCount = 0;

        for (int u = 0; u < M; u++) {    //for all applicants
            boolean visited[] = new boolean[N];
            if (bipartiteMatch(u, visited, assign)) //when u get a hospital
            {
                System.out.println("set M: \n{");
                for (int i = 0; i < set_M.size(); i++) {
                    if(set_M.get(i)>-1)
                    System.out.print("("+ApplicantsList[i]+" - "+HospitalaList[set_M.get(i)]+") \n");
                }
                System.out.println("}\n************************************************************");
                matchCount++;
            }

        }
        return matchCount;
    }
    /**
     * main method
     * @param args 
     */
    public static void main(String[] args) {
        max_bipartite g = new max_bipartite();
        System.out.println("The maximum possible number of assignments of hospitals to applicants: " + g.maxMatch()+"\n");
    }
}
