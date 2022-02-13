package com.geekerstar.basic.module.observe;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author geekerstar
 * @date 2021/8/15 13:58
 * @description
 */
@RequiredArgsConstructor
public class ReaderListener implements ApplicationListener<ObserverEvent> {

    @NonNull
    private String name;

    private String article;

    @Async
    @Override
    public void onApplicationEvent(ObserverEvent event) {
        doThings(event);
    }

    private void doThings(ObserverEvent event) {
        this.article = (String) event.getSource();
        System.out.printf("我是读者：%s，文章已更新为：%s\n", this.name, this.article);
    }

}
