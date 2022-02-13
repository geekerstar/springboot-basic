package com.geekerstar.basic.module.observe;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author geekerstar
 * @date 2021/8/15 13:58
 * @description
 */
@Getter
public class ObserverEvent extends ApplicationEvent {

    public ObserverEvent(Object source) {
        super(source);
    }
}
