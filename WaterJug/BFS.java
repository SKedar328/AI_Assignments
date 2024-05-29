package WaterJug;
import java.util.*;
class Statebfs{
    int x;
    int y;
    Statebfs parent;
    public Statebfs(int x,int y,Statebfs parent){
        this.x=x;
        this.y=y;
        this.parent=parent;
    }
    public boolean goalstate(int targetx,int targety){
        return x==targetx&y==targety;
    }
    public boolean equals(Object obj){
        if(this==obj)return true;
        if(obj==null||getClass()!=obj.getClass())return false;
        Statebfs state=(Statebfs) obj;
        return x==state.x&&y==state.y;
    }
    public int hashCode(){
        return Objects.hash(x,y);
    }
    public ArrayList<Statebfs> getnextstates(int maxx,int maxy,int targetx,int targety){
       ArrayList<Statebfs> nextstate=new ArrayList<Statebfs>();
       //fill x;
       nextstate.add(new Statebfs(maxx,y,this));
       //fill y;
       nextstate.add(new Statebfs(x,maxy,this));
       //empty x;
       nextstate.add(new Statebfs(0,y,this));
       //empty y;
       nextstate.add(new Statebfs(y,0,this));
       //pour from x to y;
       int pourx=Math.min(x,maxy-y);
       nextstate.add(new Statebfs(x-pourx,y+pourx,this));
       //pour from y to x;
       int poury=Math.min(y,maxx-x);
       nextstate.add(new Statebfs(x+poury,y-poury,this));
       return nextstate;
    }
}
public class BFS {
    public static int targetx=4;
    public static int targety=0;
    public static void printsolution(Statebfs state){
        Stack<Statebfs> stack=new Stack<>();
        while(state!=null){
            stack.push(state);
            state=state.parent;
        }
        while(!stack.isEmpty()){
          Statebfs p=stack.pop();
          System.out.println("Jug1 "+p.x+" Jug2 "+p.y);
        }
    }
    public static void main(String[] args) {
         Scanner sc=new Scanner(System.in);
         int jug1=sc.nextInt();
         int jug2=sc.nextInt();
         Statebfs initial=new Statebfs(0,0,null);
         Queue<Statebfs> open=new LinkedList<>();
         HashSet<Statebfs> closed=new HashSet<>();
         open.offer(initial);
         while(!open.isEmpty()){
            Statebfs currstate=open.poll();
            if(currstate.goalstate(targetx,targety)){
                printsolution(currstate);
                return;
            }
            closed.add(currstate);
            ArrayList<Statebfs> nextstates=currstate.getnextstates(jug1,jug2,targetx,targety);
            for(Statebfs nextstate:nextstates){
                if(!closed.contains(nextstate)){
                    open.offer(nextstate);
                }
            }
         }
         System.out.println("No Solution");
    }
}
