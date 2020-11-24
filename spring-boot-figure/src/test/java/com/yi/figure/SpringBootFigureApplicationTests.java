package com.yi.figure;

import com.yi.figure.task.FigureTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootFigureApplicationTests {
    @Autowired
    FigureTask figureTask;

    @Test
    void contextLoads() {
        figureTask.runTask(200);
    }

}
