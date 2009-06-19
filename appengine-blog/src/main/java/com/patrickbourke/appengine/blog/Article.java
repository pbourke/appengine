package com.patrickbourke.appengine.blog;

import java.util.Date;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Text;

/**
 * An Article represents a written content item in the blog.
 * 
 * @author Patrick Bourke <pbourke@gmail.com>
 */
@Entity(name="Article")
public class Article {
    public static final Pattern VALID_ID_PATTERN = Pattern.compile("^[a-zA-Z/]{1}[a-zA-Z/_0-9]{0,499}$");
    
    @Id
    private String id;
    
    private Date created;
    
    private String title;
    
    private Text text;

    /**
     * articleId must be non-null and match the VALID_ID_PATTERN.
     */
    public Article(final String articleId) {
        if ( articleId == null || !VALID_ID_PATTERN.matcher(articleId).matches() ) {
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

    /**
     * Article's title.
     */
    public String getTitle() {
        return title;        
    }
    public void setTitle(String aTitle) {
        title = aTitle;
    }
 
    /**
     * The main contents / body copy of the article.
     */
    public Text getText() {
        return text;
    }
    public void setText(Text someText) {
        text = someText;
    }
    public void setText(String someText) {
        text = new Text(someText);
    }
}
