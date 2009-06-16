package com.patrickbourke.appengine.blog;

/**
 * Public interface for the BlogService. Supports general operations
 * on blog-related objects.
 * 
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public interface BlogService {
    /**
     * Adds Article <tt>a</tt> to the blog's permanent storage.
     * 
     * @param a the Article object to be added
     * @throws IllegalArgumentException if a is null or not ready to be saved
     */
    void addArticle(Article a);
    
    /**
     * Returns a specific Article uniquely identified by <tt>id</tt>. 
     * 
     * @param id the Article's id
     * @return null if id does not refer to a valid article
     * @throws IllegalArgumentException if id is null
     */
    Article findArticleById(String id);
}
