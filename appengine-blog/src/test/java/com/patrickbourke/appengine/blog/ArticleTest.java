package com.patrickbourke.appengine.blog;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class ArticleTest {
    @Test(expected=IllegalArgumentException.class)
    public void testArticleConstructorRejectsNulls() {
        new Article(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testArticleConstructorRejectsWhitespaceInIds() {
        new Article(" ");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testArticleConstructorRejectsEmptyIds() {
        new Article("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testArticleConstructorRejectsInternalWhitespaceInIds() {
        new Article("a b");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testArticleConstructorRejectsIdsWhichStartWithADigit() {
        new Article("0abc");
    }

    @Test
    public void testArticleConstructorAcceptsSingleCharacterIds() {
        assertNotNull( new Article("a") );
    }

    @Test
    public void testCreatedDateIsNotNull() {
        assertNotNull( new Article("someId").getCreated() );
    }
    
    @Test
    public void testCreatedDateIsSetToNow() {
        Date now = new Date();
        Article a = new Article("some_id");
        assertTrue( a.getCreated().getTime() >= now.getTime() );
    }
}
