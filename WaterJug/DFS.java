package WaterJug;
import java.util.*;
class Statedfs{
    int x;
    int y;
    Statedfs parent;
    public Statedfs(int x,int y,Statedfs parent){
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
        Statedfs state=(Statedfs) obj;
        return x==state.x&&y==state.y;
    }
    public int hashCode(){
        return Objects.hash(x,y);
    }
    public ArrayList<Statedfs> getnextstates(int maxx,int maxy,int targetx,int targety){
       ArrayList<Statedfs> nextstate=new ArrayList<Statedfs>();
       //fill x;
       nextstate.add(new Statedfs(maxx,y,this));
       //fill y;
       nextstate.add(new Statedfs(x,maxy,this));
       //empty x;
       nextstate.add(new Statedfs(0,y,this));
       //empty y;
       nextstate.add(new Statedfs(y,0,this));
       //pour from x to y;
       int pourx=Math.min(x,maxy-y);
       nextstate.add(new Statedfs(x-pourx,y+pourx,this));
       //pour from y to x;
       int poury=Math.min(y,maxx-x);
       nextstate.add(new Statedfs(x+poury,y-poury,this));
       return nextstate;
    }
}
public class DFS {
    public static int targetx=4;
    public static int targety=0;
    public static void printsolution(Statedfs state){
        Stack<Statedfs> stack=new Stack<>();
        while(state!=null){
            stack.push(state);
            state=state.parent;
        }
        while(!stack.isEmpty()){
          Statedfs p=stack.pop();
          System.out.println("Jug1 "+p.x+" Jug2 "+p.y);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int jug1=sc.nextInt();
        int jug2=sc.nextInt();
        Statedfs initial=new Statedfs(0,0,null);
        Stack<Statedfs> open=new Stack<>();
        HashSet<Statedfs> closed=new HashSet<>();  
        open.push(initial);
        while(!open.isEmpty()){
           Statedfs currstate=open.pop();
           if(currstate.goalstate(targetx,targety)){
               printsolution(currstate);
               return;
           }
           closed.add(currstate);
           ArrayList<Statedfs> nextstates=currstate.getnextstates(jug1,jug2,targetx,targety);
           for(Statedfs nextstate:nextstates){
               if(!closed.contains(nextstate)){
                   open.push(nextstate);
               }
           }
        }
        System.out.println("No Solution");
   }
    
}
