package com.patrickbourke.appengine.blog;

/**
 * Implementation of the BlogService which uses Google AppEngine storage service.
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class AppEngineArticleService implements ArticleService {

    public void addArticle(final Article a) {
        if ( a == null ) {
            throw new IllegalArgumentException("Article to be added cannot be null");
        }

    }

    public Article findArticleById(final String id) {
        return null;
    }
}
