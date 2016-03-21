package com.epam.task1;

import com.epam.task1.util.BookUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 21-Mar-16.
 */
public class TestWordsSort {

    @Test
    public void testSort() {
        String textToSort = "Сортировка слов проходит автоматически";
        assertEquals("[слов, проходит, сортировка, автоматически]", Arrays.toString(BookUtils.sortWords(textToSort)));
    }
}
