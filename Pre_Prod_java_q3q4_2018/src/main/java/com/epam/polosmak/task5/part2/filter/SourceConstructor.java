package com.epam.polosmak.task5.part2.filter;

import com.epam.polosmak.task5.part2.filter.extend.filter.Filter;
import com.epam.polosmak.task5.part2.messages.impl.PrintMessageImpl;
import com.epam.polosmak.task5.part2.util.InputHandler;

public abstract class SourceConstructor {

    protected final int AGREE = 1;
    protected InputHandler inputHandler;
    protected PrintMessageImpl printMessage;

    public SourceConstructor() {
        inputHandler = new InputHandler();
        printMessage = PrintMessageImpl.getInstance();
    }

    /**
     * Create object of filter
     *
     * @param filter the previous filter in the chain
     * @return object of filter
     */
    public abstract Filter construct(Filter filter);
}