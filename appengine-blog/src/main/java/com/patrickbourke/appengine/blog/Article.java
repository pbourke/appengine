package com.patrickbourke.appengine.blog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * An Article represents a written content item in the blog.
 * 
 * @author Patrick Bourke <pbourke@gmail.com>
 */
@Entity(name="Article")
public class Article {
    
    @Id
    private String id;
    
    private Date created;

    public Article(final String articleId) {
        if ( articleId == null || articleId.trim().length() == 0 ) {
            throw new IllegalArgumentException("ArticleId cannot be null");
        }
        id = articleId;
        created = new Date();
    }
    
    /**
     * The Id is a unique identifier for the Article.
     */
    public String getId() {
        return id;
    }

    /**
     * Date the Article was first published.
     */
    public Date getCreated() {
        return created;
    }
}
