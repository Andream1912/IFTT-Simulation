/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.triggerPackage;

import java.time.LocalTime;

/**
 *
 * @author aweyx
 */
public class TimeTriggerCreator extends TriggerCreator {

    private LocalTime time;

    public TimeTriggerCreator(LocalTime time) {
        this.time = time;
    }

    @Override
    public Trigger createTrigger() {
        return new TimeTrigger(this.time);
    }

}
