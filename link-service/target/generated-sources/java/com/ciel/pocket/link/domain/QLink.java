package com.ciel.pocket.link.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLink is a Querydsl query type for Link
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLink extends EntityPathBase<Link> {

    private static final long serialVersionUID = 842917460L;

    public static final QLink link = new QLink("link");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath icon = createString("icon");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastSeen = createDateTime("lastSeen", java.util.Date.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sortIndex = createNumber("sortIndex", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Integer> visitedCount = createNumber("visitedCount", Integer.class);

    public QLink(String variable) {
        super(Link.class, forVariable(variable));
    }

    public QLink(Path<? extends Link> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLink(PathMetadata metadata) {
        super(Link.class, metadata);
    }

}

