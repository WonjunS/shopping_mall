package com.example.shopping_mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 771238537L;

    public static final QItem item = new QItem("item");

    public final QBaseTime _super = new QBaseTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemDetail = createString("itemDetail");

    public final StringPath itemName = createString("itemName");

    public final EnumPath<com.example.shopping_mall.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", com.example.shopping_mall.constant.ItemSellStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

