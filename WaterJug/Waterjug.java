package HillClimbing;

import java.util.*;
public class Waterjug{
  static int targetx=4;
  static int targety=0;
  public static void printsolution(State state){
    Stack<State> stack=new Stack<>();
    while(state!=null){
      stack.push(state);
      state=state.parent;
    }
    while(!stack.isEmpty()){
      State p=stack.pop();
      System.out.println("JUG1 "+p.x+" JUG2 "+p.y);
    }
}
  public static void main (String[] args) {
    /* code */
    Scanner sc=new Scanner(System.in);
    int jug1=sc.nextInt();
    int jug2=sc.nextInt();
    State initial=new State(0,0,targetx+targety,null);
    PriorityQueue<State> open=new PriorityQueue<>(Comparator.comparingInt(a->a.h));
    HashSet<State> closed=new HashSet<>();
    open.offer(initial);
    while(!open.isEmpty()){
      State currstate=open.poll();
      if(currstate.goalstate(targetx,targety)){
        printsolution(currstate);
        return;
      }
      closed.add(currstate);
      ArrayList<State> nextstates=currstate.getnextstates(jug1,jug2,targetx,targety);
      for(State nextstate:nextstates){
        if(nextstate.getheuristic(nextstate.x,nextstate.y,targetx,targety)<currstate.getheuristic(currstate.x,currstate.y,targetx,targety)){
          if(!closed.contains(nextstate)){
            open.offer(nextstate);
          }
        }
      }
    }
    
  }
}
class State{
  int x;
  int y;
  int h;
  State parent;
  public State(int x,int y,int h,State parent){
    this.x=x;
    this.y=y;
    this.h=h;
    this.parent=parent;
  }
  public boolean goalstate(int targetx,int targety){
    return x==targetx&&y==targety;
  }
  public int getheuristic(int x,int y,int targetx,int targety){
    return Math.abs(x-targetx)+Math.abs(y-targety);
  }
  public ArrayList<State> getnextstates(int maxx,int maxy,int targetx,int targety){
    ArrayList<State> nextstate=new ArrayList<State>();
    nextstate.add(new State(maxx,y,getheuristic(maxx,y,targetx,targety),this));
    nextstate.add(new State(x,maxy,getheuristic(x,maxy,targetx,targety),this));
    nextstate.add(new State(0,y,getheuristic(0,y,targetx,targety),this));
    nextstate.add(new State(x,0,getheuristic(x,0,targetx,targety),this));
    int pourx=Math.min(x,maxy-y);
    nextstate.add(new State(x-pourx,y+pourx,getheuristic(x-pourx,y+pourx,targetx,targety),this));
    int  poury=Math.min(maxx-x,y);
    nextstate.add(new State(x+poury,y-poury,getheuristic(x+poury,y-poury,targetx,targety),this));
    return nextstate;
  }
  public boolean eqauls(Object obj){
    if(this==obj){
      return true;
    }
    if(obj==null||getClass()!=obj.getClass()){
      return false;
    }
    State state=(State) obj;
    return x==state.x&&y==state.y;
  }
  public int hashCode(){
    return Objects.hash(x,y);
  }
}

