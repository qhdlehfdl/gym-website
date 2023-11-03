package com.study.shoppingmall.repository;

import static com.study.shoppingmall.entity.QMachine.machine;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.shoppingmall.entity.Machine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.crypto.Mac;
import java.util.List;

import static com.study.shoppingmall.entity.QMachine.machine;

@Repository
public class MachineRepositorySupport extends QuerydslRepositorySupport implements MachineRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public MachineRepositorySupport(JPAQueryFactory queryFactory) {
        super(Machine.class);
        this.queryFactory=queryFactory;
    }

    @Override
    public Page<Machine> findFilterMachine(Pageable pageable, Integer part, String brand, Integer minPrice, Integer maxPrice) {
        JPQLQuery<Machine> query = queryFactory.selectFrom(machine)
                .where(eqPart(part), eqBrand(brand), betweenPrice(minPrice, maxPrice));

        List<Machine> machineList=this.getQuerydsl().applyPagination(pageable,query).fetch();
        return new PageImpl<Machine>(machineList, pageable, query.fetchCount());
    }

    private BooleanExpression eqPart(Integer part) {
        if (part == null || part==-1) {
            return null;
        }
        return machine.part.eq(part);
    }

    private BooleanExpression eqBrand(String brand) {
        if (brand == null || brand.equals("")) {
            return null;
        }
        return machine.brand.eq(brand);
    }

    private BooleanExpression betweenPrice(Integer minPrice, Integer maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        } else if (minPrice != null && maxPrice == null)
            return machine.price.goe(minPrice);

        return machine.price.between(minPrice, maxPrice);
    }
}
