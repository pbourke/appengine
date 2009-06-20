package com.patrickbourke.appengine.blog;

import javax.persistence.EntityManager;

/**
 * Implementation of the BlogService which uses Google AppEngine storage service.
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class AppEngineArticleService implements ArticleService {

    private EntityManager entityManager;
    
    public void setEntityManager(final EntityManager em) {
        entityManager = em;
    }
    
    public void addArticle(final Article a) {
        if ( a == null ) {
            throw new IllegalArgumentException("Article to be added cannot be null");
        }
        
        if ( findArticleById(a.getId()) != null ) {
            throw new IllegalArgumentException("An Article was already saved under ID '"+a.getId()+"'");
        }
        
        entityManager.persist(a);
    }

    public Article findArticleById(final String id) {
        if ( id == null ) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Article article = entityManager.find(Article.class, id);
        if ( article != null ) {
            // force initialization of the article text before returning
            article.getText();
        }
        return article;
    }
}
