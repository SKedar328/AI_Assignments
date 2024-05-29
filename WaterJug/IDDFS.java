package WaterJug;
import java.util.*;
class Iddfs{
    int x;
    int y;
    Iddfs parent;
    public Iddfs(int x,int y,Iddfs parent){
        this.x=x;
        this.y=y;
        this.parent=parent;
    }
    public boolean goalstate(int targetx,int targety){
        return x==targetx&&y==targety;
    }
    public ArrayList<Iddfs> getnextstates(int maxx,int maxy,int targetx,int targety){
        ArrayList<Iddfs> nextstate=new ArrayList<>();
        //fillx;
        nextstate.add(new Iddfs(maxx,y,this));
        //filly;
        nextstate.add(new Iddfs(x,maxy,this));
        //emptyx;
        nextstate.add(new Iddfs(0,y,this));
        //emptyy;
        nextstate.add(new Iddfs(x,0,this));
        //pour x toy;
        int pourx=Math.min(x,maxy-y);
        nextstate.add(new Iddfs(x-pourx,y+pourx,this));
        //pour y to x;
        int poury=Math.min(y,maxx-x);
        nextstate.add(new Iddfs(x+poury,y-poury,this));
        return nextstate;

    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Iddfs iddfs = (Iddfs) obj;
        return x == iddfs.x && y == iddfs.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
public class IDDFS {
    static int targetx=4;
    static int targety=0;
    public static void printsolution(Iddfs state){
        Stack<Iddfs> stack=new Stack<>();
        while(state!=null){
            stack.push(state);
            state=state.parent;
        }
        while(!stack.isEmpty()){
            Iddfs p=stack.pop();
            System.out.println(" Jug1 "+p.x+" Jug2 "+p.y);
        }
    }
    public static boolean dls(Iddfs state,int depth,int jug1,int jug2,HashSet<Iddfs> visited){
       if(state.goalstate(targetx,targety)){
        printsolution(state);
        return true;
       }
       if(depth==0)return false;
       visited.add(state);
       ArrayList<Iddfs> nextstates=state.getnextstates(jug1,jug2,targetx,targety);
       for(Iddfs nextstate:nextstates){
        if(!visited.contains(nextstate)){
            if(dls(nextstate,depth-1,jug1,jug2,visited)){
                return true;
            }
        }
       }
       visited.remove(state);
       return false;
    }
    public static void iddf(Iddfs state,int jug1,int jug2){
        for(int depth=0;;depth++){
            HashSet<Iddfs> visited=new HashSet<>();
            if(dls(state, depth,jug1,jug2,visited)){
                return;
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int jug1=sc.nextInt();
        int jug2=sc.nextInt();
        Iddfs initial=new Iddfs(0,0,null);
        iddf(initial,jug1,jug2);
    }
}
