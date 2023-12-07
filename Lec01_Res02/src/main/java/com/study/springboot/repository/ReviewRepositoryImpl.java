package com.study.springboot.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.entity.ReviewEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

	@Override
	public Double getAvgScoreByRestaurantId(Long restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}


//    @Override
//    public Double getAvgScoreByRestaurantId(Long restaurantId) {
//        return queryFactory.select(QReviewEntity.reviewEntity.score.avg())
//                .from(QReviewEntity.reviewEntity)
//                .where(QReviewEntity.reviewEntity.restaurantId.eq(restaurantId))
//                .fetchFirst();
//    }
//
//    @Override
//    public Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page) {
//        List<ReviewEntity> reviews = queryFactory.select(QReviewEntity.reviewEntity)
//                .from(QReviewEntity.reviewEntity)
//                .where(QReviewEntity.reviewEntity.restaurantId.eq(restaurantId))
//                .offset((long) page.getPageNumber() * page.getPageSize())
//                .limit(page.getPageSize() + 1)
//                .fetch();
//
//        return new SliceImpl<>(
//                reviews.stream().limit(page.getPageSize()).toList(),
//                page,
//                reviews.size() > page.getPageSize()
//        );
//    }
}