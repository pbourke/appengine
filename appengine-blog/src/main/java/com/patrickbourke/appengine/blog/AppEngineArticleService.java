package com.patrickbourke.appengine.blog;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
        return forceInit(entityManager.find(Article.class, id));
    }

    public Article findLatestArticle() {
        try {
            return forceInit((Article) entityManager.createQuery("SELECT a FROM com.patrickbourke.appengine.blog.Article a ORDER BY a.created descending")
                .setMaxResults(1).getSingleResult());
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    /**
     * Force initialization of lazy-loaded fields before returning across
     * the Transaction boundary.
     */
    private Article forceInit(Article a) {
        if ( a != null ) {
            // force initialization of the article text before returning
            a.getText();
        }
        return a;
    }
}
