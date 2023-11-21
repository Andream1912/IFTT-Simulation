/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose;

import progettose.triggerPackage.Trigger;


/**
 *
 * @author manue
 */
public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;
    
    public Rule(String n, Action a, Trigger t){
        this.name=n;
        this.action=a;
        this.trigger=t;
    }
    
    public Action getAction(){
        return this.action;
    }
    
    public Trigger getTrigger(){
        return this.trigger;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setAction(Action a){
        this.action=a;
    }
    
    public void setTrigger(Trigger t) {
        this.trigger=t;
    }
    
    public void setName(String n){
        this.name=n;
    }
    
}
