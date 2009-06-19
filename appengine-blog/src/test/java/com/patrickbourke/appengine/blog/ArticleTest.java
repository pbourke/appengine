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
